package com.learning.dto;

public class NoteExamDTO extends HistorizedDTO {
	

	private UserDTO user;

	
	private ExamDTO exam;

	private Double score;

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

}
