package com.learning.dto;

public class ProgressionModuleDTO extends HistorizedDTO {

	/**
	 * 
	 */

	private UserDTO student;
	
	private ModuleDTO module;
	private Double progressionCour;

	private Double progressionExam;
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

	
	public Double getProgressionExam() {
		return progressionExam;
	}

	public void setProgressionExam(Double progressionExam) {
		this.progressionExam = progressionExam;
	}

	public Double getNoteFinal() {
		return noteFinal;
	}

	public void setNoteFinal(Double noteFinal) {
		this.noteFinal = noteFinal;
	}

	


}
