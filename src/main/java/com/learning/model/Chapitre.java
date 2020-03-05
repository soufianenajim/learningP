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
@Table(name = "chapitre")
public class Chapitre extends Historized {

	private static final long serialVersionUID = -8858004000210805400L;

	@Column(name = "name", length = 100)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cour_id")
	private Cour cour;

	
	@OneToMany(mappedBy = "chapitre", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Paragraphe> paragraphes; 
	
	public Chapitre() {
		super();
	}

	public Chapitre(String name) {
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

	public List<Paragraphe> getParagraphes() {
		return paragraphes;
	}

	public void setParagraphes(List<Paragraphe> paragraphes) {
		this.paragraphes = paragraphes;
	}

	@Override
	public String toString() {
		return "Chapitre [name=" + name + ", cour=" + cour + "]";
	}

	

	

}
