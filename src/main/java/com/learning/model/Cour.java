package com.learning.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cour")
public class Cour extends Historized {

	private static final long serialVersionUID = -8858004000210805400L;

	@Column(name = "name", length = 100)
	private String name;
	@Lob
	private String introduction;
	@Lob
	private String resume;
	@Lob
	private String conclusion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id")
	private Module module;


	@OneToMany(mappedBy = "cour", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Quiz> quizs;

	@OneToMany(mappedBy = "cour", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Td> tds;

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

	

	public List<Quiz> getQuizs() {
		return quizs;
	}

	public void setQuizs(List<Quiz> quizs) {
		this.quizs = quizs;
	}

	public List<Td> getTds() {
		return tds;
	}

	public void setTds(List<Td> tds) {
		this.tds = tds;
	}
	

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	@Override
	public String toString() {
		return "Cour [name=" + name + ", introduction=" + introduction + ", resume=" + resume + ", conclusion="
				+ conclusion + ", module=" + module + ", quizs=" + quizs + ", tds=" + tds + "]";
	}

	

}
