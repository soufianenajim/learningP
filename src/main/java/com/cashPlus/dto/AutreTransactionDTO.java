package com.cashPlus.dto;

import java.util.Date;

public class AutreTransactionDTO extends TransactionDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private String info ;
	
	public AutreTransactionDTO(Long id, Date createdAt, Date updatedAt, String borderaux, String date,
			double montantTransfer, UserDTO refUser, String info) {
		super(id, createdAt, updatedAt, borderaux, date, montantTransfer, refUser);
		this.info = info;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public String toString() {
		return "AutreTransaction [info=" + info + ", toString()=" + super.toString() + "]";
	}


	

}
