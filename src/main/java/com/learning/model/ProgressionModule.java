package com.learning.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "progression_module")
public class ProgressionModule extends Historized {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7442768829884969763L;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private User student;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id")
	private Module module;
	private Double progressionCour;
	private Double progressionExam;
	private Double NoteFinal;

	@OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<ProgressionModule> progressionModules;
	public ProgressionModule() {
		super();
		
	}

	

	public User getStudent() {
		return student;
	}



	public void setStudent(User student) {
		this.student = student;
	}



	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
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
		return NoteFinal;
	}

	public void setNoteFinal(Double noteFinal) {
		NoteFinal = noteFinal;
	}

}
