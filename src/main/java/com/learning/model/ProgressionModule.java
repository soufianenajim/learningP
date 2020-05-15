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
	private Module module;

	private Double progressionCour;

	@Column(name = "exam_finished", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean examFinished;

	private Double noteExam;
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


	public boolean isExamFinished() {
		return examFinished;
	}

	public void setExamFinished(boolean examFinished) {
		this.examFinished = examFinished;
	}

	public Double getNoteExam() {
		return noteExam;
	}

	public void setNoteExam(Double noteExam) {
		this.noteExam = noteExam;
	}

	public Double getNoteFinal() {
		return noteFinal;
	}

	public void setNoteFinal(Double noteFinal) {
		this.noteFinal = noteFinal;
	}

	

}
