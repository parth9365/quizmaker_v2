package com.quiz.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

import com.google.gson.Gson;
import com.quiz.beans.QuizBean;
import com.quiz.commons.ProjectConfig;
import com.quiz.commons.Query;
import com.quiz.managers.CourseManager;
import com.quiz.managers.QuizManager;

/**
 * Servlet implementation class CourseController
 */
public class QuizController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public QuizController() throws Exception {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			String action = request.getParameter("action");	// get the action from request
		
			//Now differentiate on basis of action
			if(action.equalsIgnoreCase("addForm"))				addFormHandler(request, response);
			if(action.equalsIgnoreCase("addEditSubmit"))		addEditSubmitHandler(request, response);
			if(action.equalsIgnoreCase("getQuizForCourse"))		getQuizForCourseHandler(request, response);
			if(action.equalsIgnoreCase("searchResult"))			searchResultHandler(request, response);
			if(action.equalsIgnoreCase("generateQuiz"))			generateQuizHandler(request, response);
			if(action.equalsIgnoreCase("generateQuizSubmit"))	generateQuizSubmitHandler(request, response);
			if(action.equalsIgnoreCase("delete"))				deleteHandler(request, response);
			
		}catch(Exception e){
			handleError(e);
		}
	}
	
	private void deleteHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		String quizId = request.getParameter("quizId");
		QuizManager.deleteQuiz(quizId);
		PrintWriter out = response.getWriter();
		out.write("Success");
	}

	private void generateQuizSubmitHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		String courseId = request.getParameter("courseId");
		String quizId = request.getParameter("quizId");
		int noOfCopy = Integer.valueOf(request.getParameter("noOfCopy"));
		
		String printAnswers = request.getParameter("printAnswers");
		
		QuizManager.generateRandomQuiz(courseId, quizId, noOfCopy);
		
		Connection con = null;
		OutputStream outputstream = null;
		try{
			JasperReport report = null;
			JasperPrint jasperprint=null;
			response.setContentType("application/msword");
			outputstream=response.getOutputStream();
			con = Query.getDbcpConnection();
			
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("quizId", quizId);
			
			System.out.println("Time before generating quiz: "+new Date());
			if(!printAnswers.equals("1") && request.getParameter("type").equals("mixed")){
				
				System.out.println("Path to template: "+ProjectConfig.get("projectbase")+"/QuizTemplate.jrxml");
				report=JasperCompileManager.compileReport(ProjectConfig.get("projectbase")+"/QuizTemplate.jrxml");
				
			} else if(!printAnswers.equals("1") && request.getParameter("type").equals("section")){
				
				report=JasperCompileManager.compileReport(ProjectConfig.get("projectbase")+"/QuizTemplate_Section.jrxml");
				System.out.println("Path to template2: "+ProjectConfig.get("projectbase")+"/QuizTemplate_Section.jrxml");
				
			} else if(printAnswers.equals("1") && request.getParameter("type").equals("mixed")){
				
				System.out.println("Path to template: "+ProjectConfig.get("projectbase")+"/QuizTemplateWithAnswers.jrxml");
				report=JasperCompileManager.compileReport(ProjectConfig.get("projectbase")+"/QuizTemplateWithAnswers.jrxml");
				
			} else {
				System.out.println("Path to template: "+ProjectConfig.get("projectbase")+"/QuizTemplate_Section_with_answers.jrxml");
				report=JasperCompileManager.compileReport(ProjectConfig.get("projectbase")+"/QuizTemplate_Section_with_answers.jrxml");
			}
			System.out.println("before fill report");
			try{
				jasperprint=JasperFillManager.fillReport(report, parameterMap, con);
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("after fill report");
			response.setHeader("Content-Disposition","attachment;filename=\"Quiz.docx\"");
			
			JRDocxExporter docx = new JRDocxExporter();
			System.out.println("Time after generating quiz: "+new Date());
			
			String reportName = Math.random()+".docx";
			
			docx.setParameter(JRExporterParameter.JASPER_PRINT, jasperprint);
			//docx.setParameter(JRExporterParameter.OUTPUT_STREAM, outputstream);
			System.out.println("File saved to: "+ProjectConfig.get("projectbase")+"/ExportedQuizes/"+reportName);
			docx.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, ProjectConfig.get("projectbase")+"/ExportedQuizes/"+reportName);
			
			docx.exportReport();
			
			InputStream fis = new FileInputStream(new File(ProjectConfig.get("projectbase")+"/ExportedQuizes/"+reportName));
			byte[] bufferData = new byte[1024];
			int read=0;
			while((read = fis.read(bufferData))!= -1){
				outputstream.write(bufferData, 0, read);
			}
			
			outputstream.flush();
			outputstream.close();
			fis.close();
			
		}finally{
			outputstream.close();
			con.close();
		}
	}

	private void generateQuizHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		List<Map<String,Object>> courses = CourseManager.getAllCourses();
		request.setAttribute("courses", courses);
		forward("/jsp/QuizGeneration.jsp", request, response);
	}

	private void searchResultHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		List<Map<String,Object>> quizes = QuizManager.getSearchResult(request);
		request.setAttribute("quizes", quizes);
		forward("/jsp/QuizSearch.jsp", request, response);
	}

	private void getQuizForCourseHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		long courseId = Long.valueOf(request.getParameter("courseId"));
		List<Map<String,Object>> quizes = QuizManager.getQuizesForCourse(courseId);
		Gson gson = new Gson();
		String json = gson.toJson(quizes);
		PrintWriter out = response.getWriter();
		out.write(json);
	}

	private void addEditSubmitHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		QuizBean quiz = QuizManager.createEntity(request);
		QuizManager.saveQuiz(quiz);
		response.sendRedirect("QuizController?action=searchResult");
	}

	private void addFormHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		List<Map<String,Object>> courses = CourseManager.getAllCourses();
		request.setAttribute("courses", courses);
		forward("/jsp/QuizAddEditForm.jsp", request, response);
	}

	private void handleError(Exception e) {
		e.printStackTrace();
	}
	
	private void forward(String url, HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
		RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
