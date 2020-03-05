package com.cashPlus.dto;

import java.util.Date;

public class CtmDTO extends TransactionDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	

	
	public CtmDTO(Long id, Date createdAt, Date updatedAt, String borderaux, String date, double montantTransfer,
			UserDTO refUser, String numCtm) {
		super(id, createdAt, updatedAt, borderaux, date, montantTransfer, refUser);
		this.numCtm = numCtm;
	}

	private String numCtm;

	public String getNumCtm() {
		return numCtm;
	}

	public void setNumCtm(String numCtm) {
		this.numCtm = numCtm;
	}

	@Override
	public String toString() {
		return "Ctm [numCtm=" + numCtm + ", toString()=" + super.toString() + "]";
	}
	

}
