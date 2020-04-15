package com.learning.dto;

public class ProgressionCourDTO extends HistorizedDTO {

	private UserDTO student;

	private CourDTO cour;

	private boolean courFinished;

	private boolean tdFinished;

	private boolean quizFinished;

	private Double progression;

	private Double scoreQuiz;

	private Long moduleId;
	
	public ProgressionCourDTO() {
		super();

	}

	public UserDTO getStudent() {
		return student;
	}

	public void setStudent(UserDTO student) {
		this.student = student;
	}

	public CourDTO getCour() {
		return cour;
	}

	public void setCour(CourDTO cour) {
		this.cour = cour;
	}

	public boolean isCourFinished() {
		return courFinished;
	}

	public void setCourFinished(boolean courFinished) {
		this.courFinished = courFinished;
	}

	public boolean isTdFinished() {
		return tdFinished;
	}

	public void setTdFinished(boolean tdFinished) {
		this.tdFinished = tdFinished;
	}

	public Double getProgression() {
		return progression;
	}

	public void setProgression(Double progression) {
		this.progression = progression;
	}

	public boolean isQuizFinished() {
		return quizFinished;
	}

	public void setQuizFinished(boolean quizFinished) {
		this.quizFinished = quizFinished;
	}

	public Double getScoreQuiz() {
		return scoreQuiz;
	}

	public void setScoreQuiz(Double scoreQuiz) {
		this.scoreQuiz = scoreQuiz;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	

}
