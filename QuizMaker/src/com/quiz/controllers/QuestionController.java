package com.quiz.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

import com.quiz.beans.Question;
import com.quiz.managers.CourseManager;
import com.quiz.managers.QuestionManager;
import com.quiz.managers.QuizManager;

/**
 * Servlet implementation class QuestionController
 */
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_PATH = "/home/newuser/QuizMaker/ImportedFiles/";

	// private final String UPLOAD_PATH = ProjectConfig.get("uploadPath");

	public QuestionController() throws Exception {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			
			String action = request.getParameter("action");	// get the action from request
		
			//Now differentiate on basis of action
			if(action.equalsIgnoreCase("importForm"))				importFormHandler(request, response);
			if(action.equalsIgnoreCase("importSubmit"))				importSubmitHandler(request, response);
			if(action.equalsIgnoreCase("addEditForm"))				addEditFormHandler(request, response);
			if(action.equalsIgnoreCase("addEditSubmit"))			addEditSubmitHandler(request, response);
			
			if(action.equalsIgnoreCase("searchForm"))				searchFormHandler(request, response);
			if(action.equalsIgnoreCase("searchResult"))				searchResultHandler(request, response);
			if(action.equalsIgnoreCase("searchResultForAddQue"))	searchResultForAddQueHandler(request, response);
			
			if(action.equalsIgnoreCase("delete"))					deleteHandler(request, response);
			if(action.equalsIgnoreCase("edit"))						editHandler(request, response);
			
			if(action.equalsIgnoreCase("getMaxQuestionNo"))			getMaxQuestionNoHandler(request, response);
			if(action.equalsIgnoreCase("addQuestionToQuiz"))		addQuestionToQuizHandler(request, response);
			if(action.equalsIgnoreCase("addQuestionToQuizPopup"))	addQuestionToQuizPopupHandler(request, response);
			if(action.equalsIgnoreCase("addQuestionToQuizSubmit"))	addQuestionToQuizSubmitHandler(request, response);
			
			
		}catch(Exception e){
			handleError(e);
		}
	}

	private void addQuestionToQuizPopupHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		/*String questionId = request.getParameter("questionId");
		String quizId = request.getParameter("quizId");
		
		Question question = QuestionManager.createEntity(questionId);*/
		
		List<Map<String, Object>> courses = CourseManager.getSearchResult(request);
		request.setAttribute("courses", courses);
		
		forward("/jsp/AddQuestionToQuizPopup.jsp", request, response);
	}

	private void addQuestionToQuizSubmitHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		String questionIds = request.getParameter("questionId");
		
		String[] questionIdArr = questionIds.split(",");
		
		String quizId = request.getParameter("quizId");
		//QuestionManager.copyQuestionToQuiz(questionId, quizId);
		
		for(String questionId : questionIdArr){
			boolean ifExists = QuestionManager.ifQuestionAlreadyInQuiz(questionId, quizId);
			PrintWriter out = response.getWriter();
			
			if(!ifExists){
				QuestionManager.addQuestionToQuiz(questionId, quizId);
				out.print("Success");
			}else{
				out.print("Duplicate");
			}
		}
	}

	private void searchResultForAddQueHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		List<Map<String, Object>> questions = QuestionManager.getSearchResult(request);
		request.setAttribute("questions", questions);
		forward("/jsp/QuestionSearchResult.jsp", request, response);
	}

	private void addQuestionToQuizHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		List<Map<String, Object>> quizes = QuizManager.getSearchResult(request);
		request.setAttribute("quizes", quizes);
		forward("/jsp/QuestionSearchForm.jsp?from=addQueToQuiz", request, response);
	}

	private void getMaxQuestionNoHandler(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ClassNotFoundException, SQLException, Exception {
		String quizId = request.getParameter("quizId");
		int maxQuizNo = QuestionManager.getMaxQuestionNo(quizId) + 1;
		
		PrintWriter out = response.getWriter();
		out.print(maxQuizNo);
	}

	private void editHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		String questionId = request.getParameter("questionId");
		Question question = QuestionManager.createEntity(questionId);
		
		List<Map<String, Object>> quizes = QuizManager.getSearchResult(request);
		request.setAttribute("quizes", quizes);
		
		request.setAttribute("question", question);
		request.setAttribute("isEdit", "1");
		forward("/jsp/QuestionAddEditForm.jsp", request, response);
	}

	private void deleteHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		String questionId = request.getParameter("questionId");
		String quizId = request.getParameter("quizId");
		Question question = QuestionManager.createEntity(questionId);
		PrintWriter out = response.getWriter();
		
		if("".equals(quizId)){
			if(!question.getIsGrouped().equals("1")){
				QuestionManager.deleteQuestion(questionId);
				out.write("Success");
			}else{
				//QuestionManager.deleteGroupedQuestion(question);
				out.write("Success");
			}	
		}else{
			QuestionManager.deleteQuestionFromQuiz(questionId, quizId);
			out.write("Success");
		}
		
	}

	private void addEditSubmitHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		Question q = QuestionManager.createEntity(request);
		long questionId = QuestionManager.saveQuestion(q);
		
		String quizId = request.getParameter("quizId");
		if(StringUtils.isNotEmpty(quizId)){
			QuestionManager.addQuestionToQuiz(String.valueOf(questionId), quizId);
		}
		response.sendRedirect("UserController?action=dashboard");
	}

	private void searchResultHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		List<Map<String, Object>> questions = QuestionManager.getSearchResult(request);
		request.setAttribute("questions", questions);
		forward("/jsp/QuestionSearchResult.jsp", request, response);
	}

	private void addEditFormHandler(HttpServletRequest request, HttpServletResponse response)  throws ClassNotFoundException, SQLException, Exception {
		List<Map<String, Object>> quizes = QuizManager.getSearchResult(request);
		request.setAttribute("quizes", quizes);
		String quizId = request.getParameter("quizId"); 
		if(quizId != null){
			//int maxQuizNo = 0;
			//maxQuizNo = QuestionManager.getMaxQuestionNo(quizId) + 1;
			List<Map<String, Object>> questions = QuestionManager.getSearchResult(request);

			//request.setAttribute("maxQuizNo", maxQuizNo);
			request.setAttribute("questions", questions);
		}
		forward("/jsp/QuestionAddEditForm.jsp", request, response);
	}

	private void searchFormHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		List<Map<String, Object>> courses = CourseManager.getSearchResult(request);
		//List<Map<String, Object>> quizes = QuizManager.getSearchResult(request);
		
		request.setAttribute("courses", courses);
		//request.setAttribute("quizes", quizes);
		
		forward("/jsp/QuestionSearchForm.jsp", request, response);
	}

	private void importSubmitHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String uploadFilename = null;
		long courseId = -1;
		long quizId = -1;

		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			  
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// maximum size that will be stored in memory
			//factory.setSizeThreshold(maxMemSize);
			// Location to save data that is larger than maxMemSize.
			File file = new File(UPLOAD_PATH);
			if(!file.exists()){
				file.mkdirs();
			}
			factory.setRepository(file);
			
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// maximum file size to be uploaded.
			//upload.setSizeMax( maxFileSize );

			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);
			// Process the uploaded file items
			Iterator i = fileItems.iterator();
			
			while ( i.hasNext () ) 
		      {
		         FileItem fi = (FileItem)i.next();
		         if ( !fi.isFormField () )	
		         {
		            // Get the uploaded file parameters
//		            String fieldName = fi.getFieldName();
		            String fileName = fi.getName();
		            /*String contentType = fi.getContentType();
		            boolean isInMemory = fi.isInMemory();
		            long sizeInBytes = fi.getSize();*/
		            // Write the file
		            if( fileName.lastIndexOf("\\") >= 0 ){
		               file = new File( file.getAbsolutePath() + 
		               fileName.substring( fileName.lastIndexOf("\\"))) ;
		            }else{
		               file = new File( file.getAbsolutePath() + 
		               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		            }
		            
		            uploadFilename = UPLOAD_PATH + fileName.substring(0, fileName.lastIndexOf(".")) +"_"+ new java.util.Date().toString().replace(' ','_').replace(':','-')+".xls";
		            System.out.println("DEBUG____File Upload Path: "+uploadFilename);
		            fi.write( new File(uploadFilename) ) ;
		            System.out.println("Uploaded Filename: " + fileName );
		            
		         }else{
		        	 if(fi.getFieldName().equals("courseId")){
		        		 courseId = Long.valueOf(fi.getString());
		        	 }
		        	 
		        	 if(fi.getFieldName().equals("quizId")){
		        		 quizId = Long.valueOf(fi.getString());
		        	 }
		         }
		      }
		      
		}else{
			System.out.println("No multipart content");
		}
		
		QuestionManager.importQuestionsFromFile(courseId, quizId, uploadFilename);
		response.sendRedirect("QuestionController?action=importForm");
	}

	private void importFormHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		
		List<Map<String,Object>> courses = CourseManager.getAllCourses();
		request.setAttribute("courses", courses);
		forward("/jsp/ImportQuestions.jsp", request, response);
	}

	private void handleError(Exception e) {
		e.printStackTrace();
	}
	
	private void forward(String url, HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
		RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
