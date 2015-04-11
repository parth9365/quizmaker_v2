package com.quiz.beans;

public class Question {
	private long questionId;
//	private long quizId;
	private long questionNo;
	private String question;
	private String questionType;
	private String questionTypeForDisplay;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String option5;
	private String option6;
	private String option7;
	private String option8;	
	private String answer;
	private String doNotShuffuleOption;
	private String groupedId;
	private String isGrouped;
	private String copyQuestionId;
	
	public String getCopyQuestionId() {
		return copyQuestionId;
	}
	public void setCopyQuestionId(String copyQuestionId) {
		this.copyQuestionId = copyQuestionId;
	}
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	
	public long getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(long questionNo) {
		this.questionNo = questionNo;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getQuestionTypeForDisplay() {
		return questionTypeForDisplay;
	}
	public void setQuestionTypeForDisplay(String questionTypeForDisplay) {
		this.questionTypeForDisplay = questionTypeForDisplay;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getOption5() {
		return option5;
	}
	public void setOption5(String option5) {
		this.option5 = option5;
	}
	public String getOption6() {
		return option6;
	}
	public void setOption6(String option6) {
		this.option6 = option6;
	}
	public String getOption7() {
		return option7;
	}
	public void setOption7(String option7) {
		this.option7 = option7;
	}
	public String getOption8() {
		return option8;
	}
	public void setOption8(String option8) {
		this.option8 = option8;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getDoNotShuffuleOption() {
		return doNotShuffuleOption;
	}
	public void setDoNotShuffuleOption(String doNotShuffuleOption) {
		this.doNotShuffuleOption = doNotShuffuleOption;
	}
	public String getGroupedId() {
		return groupedId;
	}
	public void setGroupedId(String groupedId) {
		this.groupedId = groupedId;
	}
	public String getIsGrouped() {
		return isGrouped;
	}
	public void setIsGrouped(String isGrouped) {
		this.isGrouped = isGrouped;
	}
}
