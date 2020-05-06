package com.learning.dto;

import java.util.List;

public class QuestionDTO extends HistorizedDTO {

	private String name;

	private String code;

	private String correctComment;

	private ExercicesDTO exercices;

	private ExamDTO exam;

	private double note;
	
	private int indexNumerator;

	private List<SuggestionDTO> suggestions;

	public QuestionDTO() {
		super();
	}

	public QuestionDTO(String name) {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCorrectComment() {
		return correctComment;
	}

	public void setCorrectComment(String correctComment) {
		this.correctComment = correctComment;
	}

	public ExercicesDTO getExercices() {
		return exercices;
	}

	public void setExercices(ExercicesDTO exercices) {
		this.exercices = exercices;
	}

	public ExamDTO getExam() {
		return exam;
	}

	public void setExam(ExamDTO exam) {
		this.exam = exam;
	}

	public List<SuggestionDTO> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<SuggestionDTO> suggestions) {
		this.suggestions = suggestions;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}

	public int getIndexNumerator() {
		return indexNumerator;
	}

	public void setIndexNumerator(int indexNumerator) {
		this.indexNumerator = indexNumerator;
	}
	
	

}
