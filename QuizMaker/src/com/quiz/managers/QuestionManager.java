package com.quiz.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.quiz.beans.Question;
import com.quiz.commons.CommonFuncs;
import com.quiz.commons.DbResultset;
import com.quiz.commons.Query;

public class QuestionManager {
	
	public static Question createEntity(HttpServletRequest request){
		Question q = new Question();
		
		//q.setQuizId(Long.valueOf(request.getParameter("quizId")));
		//q.setQuestionNo(Long.valueOf(request.getParameter("questionNo")));
		q.setQuestion(request.getParameter("question"));
		q.setQuestionType(request.getParameter("questionType"));
		q.setQuestionTypeForDisplay(request.getParameter("questionTypeForDisplay"));
		
		q.setOption1(request.getParameter("option1"));
		q.setOption2(request.getParameter("option2"));
		q.setOption3(request.getParameter("option3"));
		q.setOption4(request.getParameter("option4"));
		q.setOption5(request.getParameter("option5"));
		q.setOption6(request.getParameter("option6"));
		q.setOption7(request.getParameter("option7"));
		q.setOption8(request.getParameter("option8"));
		
		q.setAnswer(request.getParameter("answer"));
		
		q.setGroupedId(request.getParameter("groupedWith"));
		if(request.getParameter("groupedWith")!=null && !request.getParameter("groupedWith").equals("")){
			q.setIsGrouped("1");
		}else{
			q.setIsGrouped("0");
		}
		q.setCopyQuestionId(null);
		
		return q;
	}
		
	public static List<Map<Integer, String>> readExcel(File inputFile){
		List<Map<Integer,String>> rowList = null;
		try {
		     
		    FileInputStream file = new FileInputStream(inputFile);
		     
		    //Get the workbook instance for XLS file 
		    HSSFWorkbook workbook = new HSSFWorkbook(file);
		 
		    //Get first sheet from the workbook
		    HSSFSheet sheet = workbook.getSheetAt(0);
		    
		    rowList = new ArrayList<Map<Integer,String>>();
		    for(Row row : sheet) {
		    
		    	Map<Integer, String> rowVal = new HashMap<Integer, String>(); 
	    	   for(int cn=0; cn<row.getLastCellNum(); cn++) {
	    	       // If the cell is missing from the file, generate a blank one
	    	       // (Works by specifying a MissingCellPolicy)
	    	       Cell cell = row.getCell(cn, Row.CREATE_NULL_AS_BLANK);
	    	       // Print the cell for debugging
	    	       //System.out.println("CELL: " + cn + " --> " + cell.toString());
	    	       rowVal.put(cn, cell.toString());
	    	   }
	    	   rowList.add(rowVal);
	    	}
		    
	        file.close();
		    
	        //iterate over list of rows from excel
		    /*for (Map<Integer, String> rows : rowList) {
		    	//iterate over a row (get value of each column by its index starting from 0)
		    	
				System.out.println();
			}*/
		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return rowList;
	}
	
	public static void main(String[] args) {
		//readExcel(new File("C:/Users/Yash/Desktop/fiverr/mac101/Sample_Import_Format.xls"));
	}

	public static void importQuestionsFromFile(long courseId, long quizId, String uploadFilename) throws ClassNotFoundException, SQLException, Exception {
		List<Map<Integer,String>> rows = readExcel(new File(uploadFilename));
		
		List<Question> questions = new ArrayList<Question>();
		//iterate over list of rows from excel
		boolean isHeader = true;
	    for (Map<Integer, String> row : rows) {
	    	if(isHeader){
	    		isHeader = false;
	    		continue;
	    	}
	    	//iterate over a row (get value of each column by its index starting from 0)
	    	Question que = new Question();
	    	//que.setQuizId(quizId);
	    	que.setQuestionNo((long)Double.parseDouble(row.get(0)));
	    	que.setQuestion(row.get(1));
	    	que.setQuestionType(row.get(2));
	    	que.setQuestionTypeForDisplay(row.get(3));
	    	que.setOption1(row.get(4));
	    	que.setOption2(row.get(5));
	    	que.setOption3(row.get(6));
	    	que.setOption4(row.get(7));
	    	que.setOption5(row.get(8));
	    	que.setOption6(row.get(9));
	    	que.setOption7(row.get(10));
	    	que.setOption8(row.get(11));
	    	que.setAnswer(row.get(12));
	    	que.setDoNotShuffuleOption(row.get(13));
	    	que.setGroupedId(row.get(14));
	    	que.setIsGrouped(row.get(15).equals("0.0")?"0":"1");
	    	questions.add(que);
		}
	    deletePreviousQuestions(quizId);
	    List<Long> questionIds = saveQuestions(questions);
	    
	    for(long questionId : questionIds){
	    	addQuestionToQuiz(String.valueOf(questionId), String.valueOf(quizId));
	    }
	}

	private static void deletePreviousQuestions(long quizId) throws ClassNotFoundException, SQLException, Exception {
		Query.executeDelete("delete from questionquiztable where QuizId = "+quizId);
	}

	private static List<Long> saveQuestions(List<Question> questions) throws ClassNotFoundException, SQLException, Exception {
		List<Long> questionIds = new ArrayList<Long>();
		for(Question q : questions){
			questionIds.add(saveQuestion(q));
		}
		return questionIds;
	}

	public static long saveQuestion(Question q) throws ClassNotFoundException, SQLException, Exception {
		StringBuilder sb = new StringBuilder("");
		sb.append(" insert into questiontable ( /*QuestionNo, */Question, QuestionType, QuestionTypeForDisplay, ");
		sb.append(" Option1, Option2, Option3, Option4, Option5, Option6, Option7, Option8, DoNotShuffuleOption, ");
		sb.append(" Answer, GroupedWith, IsGrouped) values (");
		//sb.append(q.getQuizId()).append(", ");
		/*sb.append(q.getQuestionNo()).append(", ");*/
		sb.append(CommonFuncs.dbStringValue(q.getQuestion())).append(", ");
		sb.append(CommonFuncs.dbStringValue(q.getQuestionType())).append(", ");
		sb.append(CommonFuncs.dbStringValue(q.getQuestionTypeForDisplay())).append(", ");
		sb.append(CommonFuncs.dbStringValue(q.getOption1())).append(", ");
		sb.append(CommonFuncs.dbStringValue(q.getOption2())).append(", ");
		sb.append(CommonFuncs.dbStringValue(q.getOption3())).append(", ");
		sb.append(CommonFuncs.dbStringValue(q.getOption4())).append(", ");
		sb.append(CommonFuncs.dbStringValue(q.getOption5())).append(", ");
		sb.append(CommonFuncs.dbStringValue(q.getOption6())).append(", ");
		sb.append(CommonFuncs.dbStringValue(q.getOption7())).append(", ");
		sb.append(CommonFuncs.dbStringValue(q.getOption8())).append(", ");
		sb.append(CommonFuncs.dbStringValue(q.getDoNotShuffuleOption())).append(", ");
		sb.append(CommonFuncs.dbStringValue(q.getAnswer())).append(", ");
		sb.append(CommonFuncs.dbStringValue(q.getGroupedId())).append(", ");
		sb.append(CommonFuncs.dbStringValue(q.getIsGrouped()));
		sb.append(")");
		
		return Query.executeInsert(sb.toString(), true);
	}

	public static List<Map<String, Object>> getSearchResult(HttpServletRequest request) throws ClassNotFoundException, SQLException, Exception {
		String query = "";
		String whereClause = buildWhereClause(request);
		/*if(request.getParameter("from") != null && !request.getParameter("from").equals("")){
			//query = "select * from questiontable where QuizId <> "+request.getParameter("quizId") +" and QuestionId not in (select copyQuestionId from questiontable where QuizId = "+request.getParameter("quizId")+"  and copyQuestionId is not null)";
		}else{
			//query = "select * from questiontable where QuizId = "+request.getParameter("quizId");
		}*/
		query = " select distinct qt.* from questiontable qt left join questionquiztable qqt on qqt.QuestionId = qt.QuestionId left join quiztable qzt on qzt.QuizId = qqt.QuizId where 1=1 "+whereClause;
		
		return Query.list(query);
	}

	private static String buildWhereClause(HttpServletRequest request) {
		String where = "";
		if(!StringUtils.isEmpty(request.getParameter("courseId"))){
			where += " and qzt.CourseId = "+request.getParameter("courseId");
		}
		
		if(!StringUtils.isEmpty(request.getParameter("quizId"))){
			where += " and qzt.QuizId = "+request.getParameter("quizId");
		}
		
		if(!StringUtils.isEmpty(request.getParameter("questionText"))){
			where += " and qt.Question like '%"+request.getParameter("questionText")+"%'";
			
		}
		
		return where;
	}

	public static int getMaxQuestionNo(String quizId) throws NumberFormatException, ClassNotFoundException, SQLException, Exception {
		DbResultset dbRes = Query.executeSelect("select max(QuestionId) as maxQueNo from questiontable where QuizId = "+quizId);
		try{
			return Integer.valueOf(dbRes.getRowCount() > 0?dbRes.get(0, "maxQueNo"):"0");
		}catch(NumberFormatException ne){
			return 0;
		}
	}
	
	public static int getMinQuestionNo(String quizId) throws NumberFormatException, ClassNotFoundException, SQLException, Exception {
		DbResultset dbRes = Query.executeSelect("select min(QuestionNo) as minQueNo from questiontable where QuizId = "+quizId);
		return Integer.valueOf(dbRes.getRowCount() > 0?dbRes.get(0, "minQueNo"):"1");
	}

	public static void deleteQuestion(String questionId) throws ClassNotFoundException, SQLException, Exception {
		Query.executeDelete("delete from questionquiztable where QuestionId = "+questionId);
		Query.executeDelete("delete from questiontable where QuestionId = "+questionId);
	}

	public static Question createEntity(String questionId) throws ClassNotFoundException, SQLException, Exception {
		String query = "select * from questiontable where QuestionId = "+questionId;
		
		DbResultset rs = Query.executeSelect(query);
		Question question = new Question();
		
		question.setQuestionId(Long.valueOf(questionId));
		//question.setQuizId(Long.valueOf(rs.get(0, "QuizId")));
		question.setQuestionNo(Long.valueOf(rs.get(0, "QuestionNo")));
		question.setQuestion(rs.get(0, "Question"));
		question.setQuestionType(rs.get(0, "QuestionType"));
		question.setQuestionTypeForDisplay(rs.get(0, "QuestionTypeForDisplay"));		
		question.setOption1(rs.get(0, "Option1"));
		question.setOption2(rs.get(0, "Option2"));
		question.setOption3(rs.get(0, "Option3"));
		question.setOption4(rs.get(0, "Option4"));
		question.setOption5(rs.get(0, "Option5"));
		question.setOption6(rs.get(0, "Option6"));
		question.setOption7(rs.get(0, "Option7"));
		question.setOption8(rs.get(0, "Option8"));
		question.setAnswer(rs.get(0, "Answer"));
		question.setDoNotShuffuleOption(rs.get(0, "DoNotShuffuleOption"));
		question.setGroupedId(rs.get(0, "GroupedWith"));
		question.setIsGrouped(rs.get(0, "IsGrouped"));
		question.setCopyQuestionId(rs.get(0, "copyQuestionId"));
		
		return question;
	}

	/*public static void deleteGroupedQuestion(Question question) throws ClassNotFoundException, SQLException, Exception {
		String query = "";
		if(question.getGroupedId() != null && !question.getGroupedId().equals("")){
			query = "delete from questiontable where QuizId = "+question.getQuizId()+" and GroupedWith = "+question.getGroupedId()+" or questionno = "+question.getQuestionId();
		}else{
			query = "delete from questiontable where QuizId = "+question.getQuizId()+" and GroupedWith = "+question.getQuestionId()+" or questionno = "+question.getQuestionId();
		}
		Query.executeDelete(query);
	}*/
	/*
	public static void copyQuestionToQuiz(String questionId, String quizId) throws ClassNotFoundException, SQLException, Exception {
		Question que = new Question();
		que = QuestionManager.createEntity(questionId);
		que.setQuizId(Long.valueOf(quizId));
		que.setQuestionNo(QuestionManager.getMaxQuestionNo(quizId) + 1);
		que.setCopyQuestionId(questionId);
		QuestionManager.saveQuestion(que);
	}*/

	public static void addQuestionToQuiz(String questionId, String quizId) throws ClassNotFoundException, SQLException, Exception {
		String query = "insert into questionquiztable (QuestionId, QuizId, DateCreated) values ("+questionId+", "+quizId+", now());";
		Query.executeInsert(query);
	}

	public static boolean ifQuestionAlreadyInQuiz(String questionId, String quizId) throws ClassNotFoundException, SQLException, Exception {
		String query = "select * from questionquiztable where QuestionId = "+questionId+" and QuizId = "+quizId;
		
		DbResultset rs = Query.executeSelect(query);
		if(rs.getRowCount() > 0){
			return true;
		}
		return false;
	}

	public static void deleteQuestionFromQuiz(String questionId, String quizId) throws ClassNotFoundException, SQLException, Exception {
		String query = "delete from questionquiztable where QuestionId = "+questionId+" and QuizId = "+quizId;
		Query.executeDelete(query);
	}

}
