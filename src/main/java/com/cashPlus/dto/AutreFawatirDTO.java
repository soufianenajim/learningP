package com.cashPlus.dto;

import java.util.Date;


public class AutreFawatirDTO extends FawatirDTO {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	
	private String refPaiement;
	
	
	public AutreFawatirDTO(Long id, Date createdAt, Date updatedAt, String borderaux, String date,
			double montantTransfer, UserDTO refUser, double frais, String refPaiement) {
		super(id, createdAt, updatedAt, borderaux, date, montantTransfer, refUser, frais);
		this.refPaiement = refPaiement;
	}
	public String getRefPaiement() {
		return refPaiement;
	}
	public void setRefPaiement(String refPaiement) {
		this.refPaiement = refPaiement;
	}
	@Override
	public String toString() {
		return "AutreFawatir [refPaiement=" + refPaiement + ", toString()=" + super.toString() + "]";
	}
	

	
	
	

}
