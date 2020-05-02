package com.learning.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ExamDTO extends HistorizedDTO{
	
	
	

	
	private String name;

	private LocalDateTime startDateTime;
	
	private LocalDateTime endDateTime;
	
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

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}


}
