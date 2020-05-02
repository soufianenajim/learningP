package com.learning.dto;

import java.util.List;

public class ModuleDTO extends HistorizedDTO {

	private String name;

	private UserDTO professor;

	private GroupDTO group;
	
	private List<CourDTO> cours;

	private List<ExamDTO> exams;

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

	
}
