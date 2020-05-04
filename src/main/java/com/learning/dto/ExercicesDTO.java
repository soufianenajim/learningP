package com.learning.dto;

import java.util.List;


public class ExercicesDTO extends HistorizedDTO {

	private String name;

	private String type;
	
     private CourDTO cour;
	private List<QuestionDTO> questions;

	public ExercicesDTO() {
		super();
	}

	public ExercicesDTO(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
