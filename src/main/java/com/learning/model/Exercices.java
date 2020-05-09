package com.learning.model;

import java.time.LocalDateTime;
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
@Table(name = "exercices")
public class Exercices extends Historized {

	private static final long serialVersionUID = -8858004000210805400L;

	@Column(name = "name", length = 100)
	private String name;

	private TypeEnum type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cour_id")
	private Cour cour;
	

	@Column(name="start_date_time")
	private LocalDateTime startDateTime;
	
	@Column(name="end_date_time")
	private LocalDateTime endDateTime;
	
	private double scale;
	
	@OneToMany(mappedBy = "exercices", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE )
	private List<Question> questions;

	public Exercices() {
		super();
	}

	public Exercices(String name) {
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

	

	public Cour getCour() {
		return cour;
	}

	public void setCour(Cour cour) {
		this.cour = cour;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public TypeEnum getType() {
		return type;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}
	

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	@Override
	public String toString() {
		return "Exercices [name=" + name + ", type=" + type + ", cour=" + cour + ", startDateTime=" + startDateTime
				+ ", endDateTime=" + endDateTime + ", questions=" + questions + "]";
	}



}
