package com.cashPlus.dto;

import java.util.Date;


public   class OutDTO extends TransactionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private double frais;
	
	

	public OutDTO(Long id, Date createdAt, Date updatedAt, String borderaux, String date, double montantTransfer,
			UserDTO refUser, double frais) {
		super(id, createdAt, updatedAt, borderaux, date, montantTransfer, refUser);
		this.frais = frais;
	}
	public double getFrais() {
		return frais;
	}
	public void setFrais(double frais) {
		this.frais = frais;
	}
	@Override
	public String toString() {
		return "Out [frais=" + frais + ", toString()=" + super.toString() + "]";
	}
	



}
