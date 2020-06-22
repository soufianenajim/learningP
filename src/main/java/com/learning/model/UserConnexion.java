package com.learning.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_connexion")
public class UserConnexion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(name="date_connexion")
	private LocalDate dateConnexion;
	
	@Column(name="number_connexion")
	private int numberConnexion;

	public UserConnexion() {
		super();
	
	}


	public UserConnexion(User user, LocalDate dateConnexion, int numberConnexion) {
		super();
		this.user = user;
		this.dateConnexion = dateConnexion;
		this.numberConnexion = numberConnexion;
	}
	

	public UserConnexion( User user, LocalDate dateConnexion) {
		super();

		this.user = user;
		this.dateConnexion = dateConnexion;
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

	public LocalDate getDateConnexion() {
		return dateConnexion;
	}

	public void setDateConnexion(LocalDate dateConnexion) {
		this.dateConnexion = dateConnexion;
	}

	public int getNumberConnexion() {
		return numberConnexion;
	}

	public void setNumberConnexion(int numberConnexion) {
		this.numberConnexion = numberConnexion;
	}

	
	

}