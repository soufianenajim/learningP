package com.cashPlus.dto;

import java.util.Date;

public   class TelephoneDTO extends FawatirDTO {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	
	public TelephoneDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	private String numTelephone;
	
	public TelephoneDTO(Long id, Date createdAt, Date updatedAt, String borderaux, String date, double montantTransfer,
			UserDTO refUser, double frais, String numTelephone) {
		super(id, createdAt, updatedAt, borderaux, date, montantTransfer, refUser, frais);
		this.numTelephone = numTelephone;
	}
	public String getNumTelephone() {
		return numTelephone;
	}
	public void setNumTelephone(String numTelephone) {
		this.numTelephone = numTelephone;
	}
	@Override
	public String toString() {
		return "Telephone [numTelephone=" + numTelephone + ", toString()=" + super.toString() + "]";
	}
	



}
