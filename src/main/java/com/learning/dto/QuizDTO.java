package com.learning.dto;

import java.util.List;

public class QuizDTO extends HistorizedDTO {

	private String name;

	private CourDTO cour;

	private List<QuestionDTO> questions;

	public QuizDTO() {
		super();
	}

	public QuizDTO(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CourDTO getCour() {
		return cour;
	}

	public void setCour(CourDTO cour) {
		this.cour = cour;
	}

	public List<QuestionDTO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}

}
