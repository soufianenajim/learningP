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
@Table(name = "cour")
public class Cour extends Historized {

	private static final long serialVersionUID = -8858004000210805400L;

	@Column(name = "name", length = 100)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id")
	private Module module;
	
	@OneToMany(mappedBy = "cour", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Chapitre> chapitres;
	
	@OneToMany(mappedBy = "cour", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Quiz> quizs;

	public Cour() {
		super();
	}

	public Cour(String name) {
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

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	

	public List<Chapitre> getChapitres() {
		return chapitres;
	}

	public void setChapitres(List<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}

	public List<Quiz> getQuizs() {
		return quizs;
	}

	public void setQuizs(List<Quiz> quizs) {
		this.quizs = quizs;
	}
	
	

	@Override
	public String toString() {
		return "Cour [name=" + name + ", module=" + module + "]";
	}

	

}
