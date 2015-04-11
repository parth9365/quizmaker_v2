package com.quiz.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.quiz.beans.UserBean;
import com.quiz.commons.Query;
import com.quiz.managers.UserManager;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserController() throws Exception {
        super();
        Query dbbean = new Query();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String action = request.getParameter("action");	// get the action from request
		
			//Now differentiate on basis of action
			if(action.equalsIgnoreCase("loginSubmit"))				loginSubmitHandler(request, response);
			if(action.equalsIgnoreCase("dashboard"))				dashboardHandler(request, response);
			
		}catch(Exception e){
			handleError(e);
		}
	}

	private void dashboardHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forward("/jsp/Dashboard.jsp", request, response);
	}

	private void loginSubmitHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		
		boolean isValid = UserManager.verifyUser(uname, password);
		HttpSession session = request.getSession();
		
		if(isValid){
			UserBean userBean = UserManager.createEntity(uname);
			session.setAttribute("userBean", userBean);
			response.sendRedirect("../servlet/UserController?action=dashboard");
		}
		else
			response.sendRedirect("../jsp/Login.jsp");
	}

	private void handleError(Exception e) {
		e.printStackTrace();
	}
	
	private void forward(String url, HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
		RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
