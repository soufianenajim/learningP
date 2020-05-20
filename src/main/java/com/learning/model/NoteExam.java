package com.learning.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "note_exam")
public class NoteExam extends Historized {

	private static final long serialVersionUID = -8858004000210805400L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exam_id")
	private Exam exam;

	private Double score;

	private boolean finished;
	
	private boolean showScore;
    
	@ManyToMany
	@JoinTable(
	  name = "note_exam_suggestion", 
	  joinColumns = @JoinColumn(name = "note_exam_id"), 
	  inverseJoinColumns = @JoinColumn(name = "suggestion_id"))
	List<Suggestion> answers;
	
	public NoteExam() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean isShowScore() {
		return showScore;
	}

	public void setShowScore(boolean showScore) {
		this.showScore = showScore;
	}

	
	public List<Suggestion> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Suggestion> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "NoteExam [user=" + user + ", exam=" + exam + ", score=" + score + "]";
	}

}
