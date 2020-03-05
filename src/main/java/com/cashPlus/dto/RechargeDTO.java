package com.cashPlus.dto;

import java.util.Date;


public class RechargeDTO extends TelephoneDTO{


	private static final long serialVersionUID = -7747674835418302263L;
	
	



	public RechargeDTO(Long id, Date createdAt, Date updatedAt, String borderaux, String date, double montantTransfer,
			UserDTO refUser, double frais, String numTelephone) {
		super(id, createdAt, updatedAt, borderaux, date, montantTransfer, refUser, frais, numTelephone);
		// TODO Auto-generated constructor stub
	}





	public RechargeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}	

}
