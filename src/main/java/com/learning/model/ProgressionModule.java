package com.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private ModuleAffected module;

	private Double progressionCour;

    private Double progressionExamQuiz;
    @Column(columnDefinition = "double default 100.0")
    private Double progressionAbsence;

	private Double noteFinal;

	public ProgressionModule() {
		super();

	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public ModuleAffected getModule() {
		return module;
	}

	public void setModule(ModuleAffected module) {
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
