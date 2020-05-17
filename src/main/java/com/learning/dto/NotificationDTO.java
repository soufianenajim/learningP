package com.learning.dto;

import java.util.List;

public class NotificationDTO{
	
	
	

	
	private List<ExercicesDTO> quizList;

	private List<ExamDTO> examList;

	

	public List<ExercicesDTO> getQuizList() {
		return quizList;
	}

	public void setQuizList(List<ExercicesDTO> quizList) {
		this.quizList = quizList;
	}

	public List<ExamDTO> getExamList() {
		return examList;
	}

	public void setExamList(List<ExamDTO> examList) {
		this.examList = examList;
	}

	
	



}
