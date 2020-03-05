package com.cashPlus.dto;

import java.util.Date;


public class VignetteDTO extends FawatirDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	
	private String matriculeVehicule;
	

	public VignetteDTO(Long id, Date createdAt, Date updatedAt, String borderaux, String date, double montantTransfer,
			UserDTO refUser, double frais, String matriculeVehicule) {
		super(id, createdAt, updatedAt, borderaux, date, montantTransfer, refUser, frais);
		this.matriculeVehicule = matriculeVehicule;
	}

	public String getMatriculeVehicule() {
		return matriculeVehicule;
	}

	public void setMatriculeVehicule(String matriculeVehicule) {
		this.matriculeVehicule = matriculeVehicule;
	}

	@Override
	public String toString() {
		return "Vignette [matriculeVehicule=" + matriculeVehicule + ", toString()=" + super.toString() + "]";
	}
	

}
