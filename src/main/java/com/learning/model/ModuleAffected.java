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
@Table(name = "module_affected")
public class ModuleAffected extends Historized {

	private static final long serialVersionUID = -8858004000210805400L;

	@Column(name = "name", length = 100)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "professor_id")
	private User professor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private Group group;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id")
	private Module module;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id")
	private Session session;

	@Column(name = "is_launched", columnDefinition = "boolean default false", nullable = false)

	private boolean isLaunched;

	private double coefficient;

	private double percentageExam;

	private double percentageQuiz;

	private double percentageCour;

	private double percentageAbsence;
	
	private double scale;

	@OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Cour> cours;

	@OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Exam> exams;

	@OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<ProgressionModule> progressionModules;

	public ModuleAffected() {
		super();
	}

	public ModuleAffected(String name) {
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public List<ProgressionModule> getProgressionModules() {
		return progressionModules;
	}

	public void setProgressionModules(List<ProgressionModule> progressionModules) {
		this.progressionModules = progressionModules;
	}

	public boolean isLaunched() {
		return isLaunched;
	}

	public void setLaunched(boolean isLaunched) {
		this.isLaunched = isLaunched;
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

	
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public String toString() {
		return "ModuleAffected [name=" + name + ", professor=" + professor + ", group=" + group + ", module=" + module
				+ ", session=" + session + ", isLaunched=" + isLaunched + ", coefficient=" + coefficient
				+ ", percentageExam=" + percentageExam + ", percentageQuiz=" + percentageQuiz + ", percentageCour="
				+ percentageCour + ", percentageAbsence=" + percentageAbsence + ", scale=" + scale + ", cours=" + cours
				+ ", exams=" + exams + ", progressionModules=" + progressionModules + "]";
	}


}
