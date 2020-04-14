
package com.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "suggestion")
public class Suggestion extends Historized {

	private static final long serialVersionUID = -8858004000210805400L;

	@Column(name = "name", length = 512)
	private String name;

	@Column(name = "correct", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean correct;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;

	public Suggestion() {
		super();
	}

	public Suggestion(String name) {
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

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Suggestion [name=" + name + ", correct=" + correct + ", question=" + question + "]";
	}

	
	
}
