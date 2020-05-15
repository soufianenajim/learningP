package com.learning.dto;

public class ProgressionModuleDTO extends HistorizedDTO {

	/**
	 * 
	 */

	private UserDTO student;
	
	private ModuleDTO module;
	private Double progressionCour;

	private boolean examFinished;
	private Double noteExam;
	private Double noteFinal;

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

	
	

	public boolean isExamFinished() {
		return examFinished;
	}

	public void setExamFinished(boolean examFinished) {
		this.examFinished = examFinished;
	}

	public Double getNoteFinal() {
		return noteFinal;
	}

	public void setNoteFinal(Double noteFinal) {
		this.noteFinal = noteFinal;
	}

	public Double getNoteExam() {
		return noteExam;
	}

	public void setNoteExam(Double noteExam) {
		this.noteExam = noteExam;
	}

	
	


}
