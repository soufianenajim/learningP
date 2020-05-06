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
@Table(name = "question")
public class Question extends Historized {

	private static final long serialVersionUID = -8858004000210805400L;

	@Column(name = "name", length = 512)
	private String name;

	@Column(name = "code", length = 100)
	private String code;

	@Lob
	private String correctComment;

	private double note;

	private int indexNumerator;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH })
	@JoinColumn(name = "exercices_id")
	private Exercices exercices;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH })
	@JoinColumn(name = "exam_id")
	private Exam exam;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	private List<Suggestion> suggestions;

	public Question() {
		super();
	}

	public Question(String name) {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCorrectComment() {
		return correctComment;
	}

	public void setCorrectComment(String correctComment) {
		this.correctComment = correctComment;
	}

	public Exercices getExercices() {
		return exercices;
	}

	public void setExercices(Exercices exercices) {
		this.exercices = exercices;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public List<Suggestion> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}
       


	public int getIndexNumerator() {
		return indexNumerator;
	}

	public void setIndexNumerator(int indexNumerator) {
		this.indexNumerator = indexNumerator;
	}

	@Override
	public String toString() {
		return "Question [name=" + name + ", code=" + code + ", correctComment=" + correctComment + ", exercices="
				+ exercices + ", exam=" + exam + "]";
	}

}
