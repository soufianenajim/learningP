package com.learning.dto;

public class StatistiqueOrgConnexionDTO {
	private Long id;
	private String name;
	private Long numberConnexion;
	public StatistiqueOrgConnexionDTO() {
		super();
	
	}
	
	public StatistiqueOrgConnexionDTO(Long id, String name, Long numberConnexion) {
		super();
		this.id = id;
		this.name = name;
		this.numberConnexion = numberConnexion;
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
	public Long getNumberConnexion() {
		return numberConnexion;
	}
	public void setNumberConnexion(Long numberConnexion) {
		this.numberConnexion = numberConnexion;
	}

	@Override
	public String toString() {
		return "StatistiqueOrgConnexionDTO [id=" + id + ", name=" + name + ", numberConnexion=" + numberConnexion + "]";
	}
	
	

}
