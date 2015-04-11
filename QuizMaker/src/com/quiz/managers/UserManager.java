package com.quiz.managers;

import java.sql.SQLException;

import com.quiz.beans.UserBean;
import com.quiz.commons.CommonFuncs;
import com.quiz.commons.DbResultset;
import com.quiz.commons.Query;

public class UserManager {

	public static boolean verifyUser(String uname, String password) {
		String query = "SELECT Password FROM usertable WHERE UserName = '"+uname+"'";
		System.out.println("Query::: "+query);
		String dbPassword = "";
		try {
			System.out.println("Before getting connection");
			DbResultset rs = Query.executeSelect(query);
			System.out.println("Connection successful");
			if(rs.getRowCount()>0)
				dbPassword = rs.get(0,"Password");
			if(password.equals(dbPassword))
				return true;
		} catch (Exception e) {
			System.out.println("Could not connect!! Exception:: "+e);
			System.out.println("Exception message: "+e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public static UserBean createEntity(String userName) throws ClassNotFoundException, SQLException, Exception {
		UserBean userBean = null;
		String query = "select UserId, UserName, Password, FirstName, LastName, Email from usertable where UserName = "+CommonFuncs.dbStringValue(userName);
		DbResultset rs = Query.executeSelect(query);
		if(rs.getRowCount() > 0){
			userBean = new UserBean();
			userBean.setUserName(rs.get(0, "UserName"));
			userBean.setPassword(rs.get(0, "Password"));
			userBean.setFirstName(rs.get(0, "FirstName"));
			userBean.setLastName(rs.get(0, "LastName"));
			userBean.setEmail(rs.get(0, "Email"));
		}
		return userBean;
	}

}
