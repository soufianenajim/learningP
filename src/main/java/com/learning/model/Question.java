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

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "code", length = 100)
	private String code;

	@Lob
	private String correctComment;

	@ManyToOne(fetch=FetchType.LAZY,cascade = {CascadeType.DETACH})
	@JoinColumn(name = "td_id")
	private Td td;

	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH})
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;

	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH})
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

	public Td getTd() {
		return td;
	}

	public void setTd(Td td) {
		this.td = td;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
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

	@Override
	public String toString() {
		return "Question [name=" + name + ", code=" + code + ", correctComment=" + correctComment + ", td=" + td
				+ ", quiz=" + quiz + ", exam=" + exam + "]";
	}

}
