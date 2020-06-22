package com.learning.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user_attempt", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserAttempt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String email;
	private int attempts;
	private LocalDateTime dateFirstAttempt;

	public UserAttempt() {
		super();
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "email", nullable = false, length = 255)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "attempts")
	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	
	@Column(name = "date_first_attempt")
	public LocalDateTime getDateFirstAttempt() {
		return dateFirstAttempt;
	}

	public void setDateFirstAttempt(LocalDateTime dateFirstAttempt) {
		this.dateFirstAttempt = dateFirstAttempt;
	}


	public UserAttempt( String email, int attempts,
			LocalDateTime dateFirstAttempt) {
		super();
		
		this.email = email;
		this.attempts = attempts;
		this.dateFirstAttempt = dateFirstAttempt;
	}
	
}