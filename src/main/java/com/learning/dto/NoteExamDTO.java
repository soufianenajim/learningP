package com.learning.dto;

import java.util.List;

public class NoteExamDTO extends HistorizedDTO {

	private UserDTO user;

	private ExamDTO exam;

	private ModuleDTO module;

	private Double score;

	private boolean finished;

	private boolean showScore;

	private String type;

	private List<SuggestionDTO> answers;

	public NoteExamDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public ExamDTO getExam() {
		return exam;
	}

	public void setExam(ExamDTO exam) {
		this.exam = exam;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public ModuleDTO getModule() {
		return module;
	}

	public void setModule(ModuleDTO module) {
		this.module = module;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isShowScore() {
		return showScore;
	}

	public void setShowScore(boolean showScore) {
		this.showScore = showScore;
	}

	public List<SuggestionDTO> getAnswers() {
		return answers;
	}

	public void setAnswers(List<SuggestionDTO> answers) {
		this.answers = answers;
	}
	

}
