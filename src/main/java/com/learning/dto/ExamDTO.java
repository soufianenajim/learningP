package com.learning.dto;

import java.util.List;

public class ExamDTO extends HistorizedDTO{
	
	
	

	
	private String name;

	
	private ModuleDTO module;

	
	
	private List<QuestionDTO> questions;
	
	public ExamDTO() {
		super();
	}

	public ExamDTO(String name) {
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

	public ModuleDTO getModule() {
		return module;
	}

	public void setModule(ModuleDTO module) {
		this.module = module;
	}
	
	

	public List<QuestionDTO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionDTO> questions) {
		this.questions = questions;
	}


}
