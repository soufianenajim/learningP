package com.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "paragraphe")
public class Paragraphe extends Historized {

	private static final long serialVersionUID = -8858004000210805400L;

	@Column(name = "name", length = 100)
	private String name;

	@Lob
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chapitre_id")
	private Chapitre chapitre;

	public Paragraphe() {
		super();
	}

	public Paragraphe(String name) {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Chapitre getChapitre() {
		return chapitre;
	}

	public void setChapitre(Chapitre chapitre) {
		this.chapitre = chapitre;
	}

	@Override
	public String toString() {
		return "Paragraphe [name=" + name + ", content=" + content + ", chapitre=" + chapitre + "]";
	}

	

}
