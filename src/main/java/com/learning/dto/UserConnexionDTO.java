package com.learning.dto;

public class UserConnexionDTO {

	private Long id;
	
	
	private UserDTO user;
	
	private String dateConnexion;
	
	private int numberConnexion;

	public UserConnexionDTO() {
		super();
		
	}

	public UserConnexionDTO(UserDTO user, String dateConnexion, int numberConnexion) {
		super();
		this.user = user;
		this.dateConnexion = dateConnexion;
		this.numberConnexion = numberConnexion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getDateConnexion() {
		return dateConnexion;
	}

	public void setDateConnexion(String dateConnexion) {
		this.dateConnexion = dateConnexion;
	}

	public int getNumberConnexion() {
		return numberConnexion;
	}

	public void setNumberConnexion(int numberConnexion) {
		this.numberConnexion = numberConnexion;
	}

	

}