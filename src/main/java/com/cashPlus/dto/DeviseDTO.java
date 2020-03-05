package com.cashPlus.dto;

import java.util.Date;


public class DeviseDTO extends TransactionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String qualiteClient;

	
	
	public DeviseDTO(Long id, Date createdAt, Date updatedAt, String borderaux, String date, double montantTransfer,
			UserDTO refUser, String qualiteClient) {
		super(id, createdAt, updatedAt, borderaux, date, montantTransfer, refUser);
		this.qualiteClient = qualiteClient;
	}

	

	public String getQualiteClient() {
		return qualiteClient;
	}

	public void setQualiteClient(String qualiteClient) {
		this.qualiteClient = qualiteClient;
	}

	@Override
	public String toString() {
		return "Devise [qualiteClient=" + qualiteClient + ", toString()=" + super.toString() + "]";
	}
	

}
