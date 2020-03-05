package com.learning.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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



	@Override
	public String toString() {
		return "NoteExam [user=" + user + ", exam=" + exam + ", score=" + score + "]";
	}

	

	

}
