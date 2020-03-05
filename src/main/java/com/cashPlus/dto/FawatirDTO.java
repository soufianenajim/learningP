package com.cashPlus.dto;

import java.util.Date;


public  class FawatirDTO extends TransactionDTO {

	private static final long serialVersionUID = 1L;

	public FawatirDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	public FawatirDTO(Long id, Date createdAt, Date updatedAt, String borderaux, String date, double montantTransfer,
			UserDTO refUser, double frais) {
		super(id, createdAt, updatedAt, borderaux, date, montantTransfer, refUser);
		this.frais = frais;
	}




	protected double frais;

	public double getFrais() {
		return frais;
	}

	public void setFrais(double frais) {
		this.frais = frais;
	}

	@Override
	public String toString() {
		return "Fawatir [frais=" + frais + ", toString()=" + super.toString() + "]";
	}

}
