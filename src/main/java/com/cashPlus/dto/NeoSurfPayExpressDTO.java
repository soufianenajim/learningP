package com.cashPlus.dto;

import java.util.Date;

public class NeoSurfPayExpressDTO extends TransactionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numCommande;


	public NeoSurfPayExpressDTO(Long id, Date createdAt, Date updatedAt, String borderaux, String date,
			double montantTransfer, UserDTO refUser, String numCommande) {
		super(id, createdAt, updatedAt, borderaux, date, montantTransfer, refUser);
		this.numCommande = numCommande;
	}

	public String getNumCommande() {
		return numCommande;
	}

	public void setNumCommande(String numCommande) {
		this.numCommande = numCommande;
	}

	@Override
	public String toString() {
		return "NeoSurfPayExpress [numCommande=" + numCommande + ", toString()=" + super.toString() + "]";
	}

}
