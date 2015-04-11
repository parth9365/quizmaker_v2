package com.quiz.managers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.quiz.beans.Course;
import com.quiz.beans.QuizBean;
import com.quiz.commons.CommonFuncs;
import com.quiz.commons.Query;

public class CourseManager {

	public static Course createEntity(HttpServletRequest request) {
		Course course = new Course();
		course.setCourseName(request.getParameter("courseName"));
		course.setDescription(request.getParameter("description"));
		return course;
	}

	public static void saveCourse(Course course) throws ClassNotFoundException, SQLException, Exception {
		StringBuilder sb = new StringBuilder("");
		sb.append("insert into coursetable (CourseName, Description) values (").append(CommonFuncs.dbStringValue(course.getCourseName())).append(", ").append(CommonFuncs.dbStringValue(course.getDescription())+")");
		Query.executeInsert(sb.toString());
	}

	public static List<Map<String,Object>> getAllCourses() throws ClassNotFoundException, SQLException, Exception {
		String query = "select CourseId, CourseName, Description from coursetable ";
		return Query.list(query);
	}

	public static List<Map<String, Object>> getSearchResult( HttpServletRequest request) throws ClassNotFoundException, SQLException, Exception {
		String query = "Select CourseId, CourseName, Description from coursetable;";
		return Query.list(query);
	}


}
