package com.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "progression_cour")
public class ProgressionCour extends Historized {

	/**
	 * 
	 */
	private static final long serialVersionUID = -17347479060374405L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private User student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cour_id")
	private Cour cour;

	@Column(name = "cour_finished", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean courFinished;

	
	@Column(name = "td_finished", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean tdFinished;
	
	

	private Double progression;
	
	

	public ProgressionCour() {
		super();

	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Cour getCour() {
		return cour;
	}

	public void setCour(Cour cour) {
		this.cour = cour;
	}

	



	public boolean isCourFinished() {
		return courFinished;
	}

	public void setCourFinished(boolean courFinished) {
		this.courFinished = courFinished;
	}

	public boolean isTdFinished() {
		return tdFinished;
	}

	public void setTdFinished(boolean tdFinished) {
		this.tdFinished = tdFinished;
	}

	public Double getProgression() {
		return progression;
	}

	public void setProgression(Double progression) {
		this.progression = progression;
	}

	

	
	
	

}
