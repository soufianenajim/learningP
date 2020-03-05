package com.cashPlus.dto;

import java.util.Date;


public class OutInternationalDTO extends OutDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7747674835418302263L;

	private double commission;

	


	public OutInternationalDTO(Long id, Date createdAt, Date updatedAt, String borderaux, String date,
			double montantTransfer, UserDTO refUser, double frais, double commission) {
		super(id, createdAt, updatedAt, borderaux, date, montantTransfer, refUser, frais);
		this.commission = commission;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	

}
