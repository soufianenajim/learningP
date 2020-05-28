package com.learning.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ExamDTO extends HistorizedDTO {

	private String name;

	private LocalDateTime startDateTime;

	private LocalDateTime endDateTime;

	private ModuleAffectedDTO module;
	private String type;
	private double scale;

	private boolean launched;
	private List<QuestionDTO> questions;

	private boolean afterCurrentDate;

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

	public ModuleAffectedDTO getModule() {
		return module;
	}

	public void setModule(ModuleAffectedDTO module) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public boolean isLaunched() {
		return launched;
	}

	public void setLaunched(boolean launched) {
		this.launched = launched;
	}

	public boolean isAfterCurrentDate() {
		return afterCurrentDate;
	}

	public void setAfterCurrentDate(boolean afterCurrentDate) {
		this.afterCurrentDate = afterCurrentDate;
	}
	

}
