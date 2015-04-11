/*
*
*	Copyright (c) 1999-2000 ISM All Rights Reserved.
*
*	This software is the confidential and proprietary information of
*	ISM . ("Confidential Information").  You shall not
*	disclose such Confidential Information and shall use it only in
*	accordance with the terms of the license agreement you entered into
*
*/

package com.quiz.commons;

import java.io.*;
import java.util.*;


public class ProjectConfig
{
   static Properties properties = null;

	public ProjectConfig(){
		try
		{
			InputStream  inputStream = getClass().getResourceAsStream("server.config"); //new FileInputStream(projectBase+"/server.config");
			properties = new Properties();
			properties.load(inputStream);
		}catch(IOException ex){}
	}

	public static String get(String variableString)
	{
		return (String)properties.get(variableString);
	}
}
