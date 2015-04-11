package com.quiz.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quiz.beans.Course;
import com.quiz.commons.Query;
import com.quiz.managers.CourseManager;
import com.quiz.managers.QuizManager;

/**
 * Servlet implementation class CourseController
 */
public class CourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CourseController() throws Exception {
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
			if(action.equalsIgnoreCase("search"))				searchHandler(request, response);
			
		}catch(Exception e){
			handleError(e);
		}
	}
	
	private void searchHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String,Object>> courses = CourseManager.getSearchResult(request);
		request.setAttribute("courses", courses);
		forward("/jsp/CourseSearch.jsp", request, response);
	}

	private void addEditSubmitHandler(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, Exception {
		Course course = CourseManager.createEntity(request);
		CourseManager.saveCourse(course);
		response.sendRedirect("CourseController?action=search");
	}

	private void addFormHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forward("/jsp/CourseAddForm.jsp", request, response);
	}

	private void handleError(Exception e) {
		e.printStackTrace();
	}
	
	private void forward(String url, HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
		RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
