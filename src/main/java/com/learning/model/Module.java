package com.learning.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "module")
public class Module extends Historized {

	private static final long serialVersionUID = -8858004000210805400L;

	@Column(name = "name", length = 100)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "professor_id")
	private User professor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "level_id")
	private Level level;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id")
	private Branch branch;

	

	@OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Cour> cours;
	
	@OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Exam> exams;
	
	public Module() {
		super();
	}

	public Module(String name) {
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

	

	public User getProfessor() {
		return professor;
	}

	public void setProfessor(User professor) {
		this.professor = professor;
	}

	public List<Cour> getCours() {
		return cours;
	}

	public void setCours(List<Cour> cours) {
		this.cours = cours;
	}
	

	public List<Exam> getExams() {
		return exams;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}
	

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "Module [name=" + name + ", user=" + professor + ", level=" + level + ", branch=" + branch + ", cours="
				+ cours + ", exams=" + exams + "]";
	}

	



}
