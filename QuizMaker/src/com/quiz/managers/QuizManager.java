package com.quiz.managers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.quiz.beans.Question;
import com.quiz.beans.QuizBean;
import com.quiz.commons.CommonFuncs;
import com.quiz.commons.DbResultset;
import com.quiz.commons.Query;
import com.quiz.commons.RandomNumber;
public class QuizManager {

	public static QuizBean createEntity(HttpServletRequest request) {
		QuizBean quiz = new QuizBean();
		quiz.setCourseId(Long.parseLong(request.getParameter("courseId")));
		quiz.setTitle(request.getParameter("title"));
		quiz.setDescription(request.getParameter("description"));
		return quiz;
	}

	public static void saveQuiz(QuizBean quiz) throws ClassNotFoundException, SQLException, Exception {
		StringBuilder sb = new StringBuilder("");
		sb.append("insert into quiztable (CourseId, Title, Description) values (")
				.append(quiz.getCourseId()).append(", ")
				.append(CommonFuncs.dbStringValue(quiz.getTitle())).append(", ")
				.append(CommonFuncs.dbStringValue(quiz.getDescription()))
				.append( ")");
		
		Query.executeInsert(sb.toString());
	}

	public static List<Map<String,Object>> getQuizesForCourse(long courseId) throws ClassNotFoundException, SQLException, Exception {
		String query = "select QuizId, Title from quiztable where CourseId = "+courseId;
		return Query.list(query);
	}

	public static List<Map<String,Object>> getSearchResult(HttpServletRequest request) throws ClassNotFoundException, SQLException, Exception {
		String query = "";
		if(request.getParameter("quizId") != null){
			query = "Select QuizId, CourseId, Title, Description from quiztable where QuizId="+request.getParameter("quizId");
			
		}else{
			query = "Select QuizId, CourseId, Title, Description from quiztable";
		}
		return Query.list(query);
	}

	public static void generateRandomQuiz(String courseId, String quizId, int noOfCopy) throws ClassNotFoundException, SQLException, Exception {
		DbResultset que = Query.executeSelect("select * from questiontable where QuizId = "+quizId);
		int rowCount = que.getRowCount();
		
		deleteQuizTransactionForQuizId(quizId);
		
		while(noOfCopy-- > 0){
			RandomNumber rn = new RandomNumber(rowCount);
			Map<Long, List<Question>> groupMap = new HashMap<Long, List<Question>>();
			int i=0;
			while(!rn.isEmpty()){
				Question q = new Question();
				int randomNo = rn.getNextRandomNumber();
				q.setQuestionId(Long.valueOf(que.get(randomNo, "QuestionId")));
				q.setQuizId(Long.valueOf(que.get(randomNo, "QuizId")));
				q.setQuestionNo(Long.valueOf(que.get(randomNo, "QuestionNo")));
				q.setQuestion(CommonFuncs.dbStringValue(que.get(randomNo, "Question")));
				q.setQuestionType(CommonFuncs.dbStringValue(que.get(randomNo, "QuestionType")));
				q.setQuestionTypeForDisplay(CommonFuncs.dbStringValue(que.get(randomNo, "QuestionTypeForDisplay")));
				q.setOption1(CommonFuncs.dbStringValue(que.get(randomNo, "Option1")));
				q.setOption2(CommonFuncs.dbStringValue(que.get(randomNo, "Option2")));
				q.setOption3(CommonFuncs.dbStringValue(que.get(randomNo, "Option3")));
				q.setOption4(CommonFuncs.dbStringValue(que.get(randomNo, "Option4")));
				q.setOption5(CommonFuncs.dbStringValue(que.get(randomNo, "Option5")));
				q.setOption6(CommonFuncs.dbStringValue(que.get(randomNo, "Option6")));
				q.setOption7(CommonFuncs.dbStringValue(que.get(randomNo, "Option7")));
				q.setOption8(CommonFuncs.dbStringValue(que.get(randomNo, "Option8")));
				q.setAnswer(CommonFuncs.dbStringValue(que.get(randomNo, "Answer")));
				q.setDoNotShuffuleOption(CommonFuncs.dbStringValue(que.get(randomNo, "DoNotShuffuleOption")));
				q.setGroupedId("".equals(que.get(randomNo, "GroupedWith"))?null:que.get(randomNo, "GroupedWith"));
				q.setIsGrouped(que.get(randomNo, "IsGrouped"));
				
				//logic to insert only one from grouped question
				if(!q.getIsGrouped().equals("1")){
					insertQuizTransaction(q, ++i, noOfCopy, groupMap);
				}else{
					long questionNo = q.getGroupedId() != null ? Long.valueOf(q.getGroupedId()) : q.getQuestionNo();
					if(groupMap.get(questionNo) != null){
						groupMap.get(questionNo).add(q);
						groupMap.put(new Long(questionNo), groupMap.get(questionNo));
					}else{
						List<Question> l = new ArrayList<Question>();
						l.add(q);
						groupMap.put(new Long(questionNo), l);
					}
				}
			}
			
			//insert grouped question now to db
			for (Map.Entry<Long, List<Question>> entry : groupMap.entrySet()) {
			    Long key = entry.getKey();
			    List<Question> questions = entry.getValue();
			    Question q = questions.get(new Random().nextInt(questions.size()));
			    q.setIsGrouped("0");
			    insertQuizTransaction(q, ++i,noOfCopy, null);
			}
		}
		
		
	}
	
	private static void deleteQuizTransactionForQuizId(String quizId) throws ClassNotFoundException, SQLException, Exception {
		Query.executeDelete("delete from quiztransactiontable where QuizId = "+quizId);
	}

	public static void insertQuizTransaction(Question q, int rowNum, int copyNo, Map<Long, List<Question>> groupMap) throws ClassNotFoundException, SQLException, Exception{
		if(!q.getIsGrouped().equals("1")){
			RandomNumber r1 = new RandomNumber(4);
			RandomNumber r2 = new RandomNumber(4);
			
			StringBuilder query = new StringBuilder("insert into quiztransactiontable (QuestionId, CopyNo, RowNumPerCopy, QuizId, QuestionNo, Question, QuestionType, QuestionTypeForDisplay, Option1, Option2, Option3, Option4, Option5, Option6, Option7, Option8, Answer, DoNotShuffuleOption, GroupedWith, DateCreated ) values (");
					query.append(q.getQuestionId()+", ");
					query.append(copyNo +", ");
					query.append(rowNum +", ");
					query.append(q.getQuizId()+", ");
					query.append(q.getQuestionNo()+", ");
					query.append(q.getQuestion()+", ");
					query.append(q.getQuestionType()+", ");
					query.append(q.getQuestionTypeForDisplay()+", ");
					
					if(q.getDoNotShuffuleOption() == null){
						while(!r1.isEmpty()){
							switch(r1.getNextRandomNumber()){
								case 0:
									query.append(q.getOption1()+", ");
									break;
								case 1:
									query.append(q.getOption2()+", ");
									break;
								case 2:
									query.append(q.getOption3()+", ");
									break;
								case 3:
									query.append(q.getOption4()+", ");
									break;
							}
						}
						
						while(!r2.isEmpty()){
							switch(r2.getNextRandomNumber()+4){
								case 4:
									query.append(q.getOption5()+", ");
									break;
								case 5:
									query.append(q.getOption6()+", ");
									break;
								case 6:
									query.append(q.getOption7()+", ");
									break;
								case 7:
									query.append(q.getOption8()+", ");
									break;
							}
						}
					}else{
						
						query.append(q.getOption1()+", ");
						query.append(q.getOption2()+", ");
						query.append(q.getOption3()+", ");
						query.append(q.getOption4()+", ");
						query.append(q.getOption5()+", ");
						query.append(q.getOption6()+", ");
						query.append(q.getOption7()+", ");
						query.append(q.getOption8()+", ");
						
					}
					
					query.append(q.getAnswer()+", ");
					query.append(q.getDoNotShuffuleOption()+", ");
					query.append(!"".equals(q.getGroupedId()) ? q.getGroupedId()+", " : "'', ");
					query.append("now())");
		
			Query.executeInsert(query.toString());
		}else{
			
			
		}
	}

	public static void deleteQuiz(String quizId) throws ClassNotFoundException, SQLException, Exception {
		String query = "delete from quiztable where QuizId = "+quizId;
		Query.executeDelete(query);
		
		query = "delete from questiontable where quizid = "+quizId;
		Query.executeDelete(query);
		
	}

	
}
