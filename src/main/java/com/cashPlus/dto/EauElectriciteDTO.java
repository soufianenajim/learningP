package com.cashPlus.dto;

import java.util.Date;


public class EauElectriciteDTO extends FawatirDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	

	public EauElectriciteDTO(Long id, Date createdAt, Date updatedAt, String borderaux, String date,
			double montantTransfer, UserDTO refUser, double frais, String numFacture) {
		super(id, createdAt, updatedAt, borderaux, date, montantTransfer, refUser, frais);
		this.numFacture = numFacture;
	}

	private String numFacture;

	public String getNumFacture() {
		return numFacture;
	}

	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}

}
