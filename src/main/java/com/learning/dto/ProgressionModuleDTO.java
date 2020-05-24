package com.learning.dto;

public class ProgressionModuleDTO extends HistorizedDTO {

	/**
	 * 
	 */

	private UserDTO student;

	private ModuleDTO module;
	private Double progressionCour;
	
	private Double progressionExamQuiz;
	
	private Double progressionAbsence;

	private Double noteFinal;

	private boolean teacher;

	public ProgressionModuleDTO() {
		super();

	}

	public UserDTO getStudent() {
		return student;
	}

	public void setStudent(UserDTO student) {
		this.student = student;
	}

	public ModuleDTO getModule() {
		return module;
	}

	public void setModule(ModuleDTO module) {
		this.module = module;
	}

	public Double getProgressionCour() {
		return progressionCour;
	}

	public void setProgressionCour(Double progressionCour) {
		this.progressionCour = progressionCour;
	}

	public Double getNoteFinal() {
		return noteFinal;
	}

	public void setNoteFinal(Double noteFinal) {
		this.noteFinal = noteFinal;
	}

	public boolean isTeacher() {
		return teacher;
	}

	public void setTeacher(boolean teacher) {
		this.teacher = teacher;
	}

	public Double getProgressionExamQuiz() {
		return progressionExamQuiz;
	}

	public void setProgressionExamQuiz(Double progressionExamQuiz) {
		this.progressionExamQuiz = progressionExamQuiz;
	}

	public Double getProgressionAbsence() {
		return progressionAbsence;
	}

	public void setProgressionAbsence(Double progressionAbsence) {
		this.progressionAbsence = progressionAbsence;
	}

	

	

}
