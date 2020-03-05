package com.cashPlus.dto;

import java.util.Date;

public class SpeedBoxdDTO extends TransactionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private String numColis;

	public SpeedBoxdDTO(Long id, Date createdAt, Date updatedAt, String borderaux, String date, double montantTransfer,
			UserDTO refUser, String numColis) {
		super(id, createdAt, updatedAt, borderaux, date, montantTransfer, refUser);
		this.numColis = numColis;
	}

	public String getNumColis() {
		return numColis;
	}

	public void setNumColis(String numColis) {
		this.numColis = numColis;
	}

	@Override
	public String toString() {
		return "SpeedBox [numColis=" + numColis + ", toString()=" + super.toString() + "]";
	}

}
