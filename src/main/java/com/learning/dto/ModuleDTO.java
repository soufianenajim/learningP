package com.learning.dto;

import java.util.List;

public class ModuleDTO extends HistorizedDTO {

	private String name;

	private UserDTO professor;

	private GroupDTO group;
	
	private boolean launched;
	
	private List<CourDTO> cours;

	private List<ExamDTO> exams;
	
	private Long idOrganization;
	
	private boolean hasExam;

	private double coefficient;

	private double percentageExam;

	private double percentageQuiz;

	private double percentageCour;

	private double percentageAbsence;
	
	private double scale;
	public ModuleDTO() {
		super();
	}

	public ModuleDTO(String name) {
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

	
	public UserDTO getProfessor() {
		return professor;
	}

	public void setProfessor(UserDTO professor) {
		this.professor = professor;
	}

	public List<CourDTO> getCours() {
		return cours;
	}

	public void setCours(List<CourDTO> cours) {
		this.cours = cours;
	}

	public List<ExamDTO> getExams() {
		return exams;
	}

	public void setExams(List<ExamDTO> exams) {
		this.exams = exams;
	}

	public GroupDTO getGroup() {
		return group;
	}

	public void setGroup(GroupDTO group) {
		this.group = group;
	}

	public boolean isLaunched() {
		return launched;
	}

	public void setLaunched(boolean isLaunched) {
		this.launched = isLaunched;
	}

	public Long getIdOrganization() {
		return idOrganization;
	}

	public void setIdOrganization(Long idOrganization) {
		this.idOrganization = idOrganization;
	}

	public boolean isHasExam() {
		return hasExam;
	}

	public void setHasExam(boolean hasExam) {
		this.hasExam = hasExam;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	public double getPercentageExam() {
		return percentageExam;
	}

	public void setPercentageExam(double percentageExam) {
		this.percentageExam = percentageExam;
	}

	public double getPercentageQuiz() {
		return percentageQuiz;
	}

	public void setPercentageQuiz(double percentageQuiz) {
		this.percentageQuiz = percentageQuiz;
	}

	public double getPercentageCour() {
		return percentageCour;
	}

	public void setPercentageCour(double percentageCour) {
		this.percentageCour = percentageCour;
	}

	public double getPercentageAbsence() {
		return percentageAbsence;
	}

	public void setPercentageAbsence(double percentageAbsence) {
		this.percentageAbsence = percentageAbsence;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}
	

	
	
}
