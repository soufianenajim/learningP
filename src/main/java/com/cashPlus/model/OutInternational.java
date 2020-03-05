package com.cashPlus.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.validation.constraints.NotNull;

@DiscriminatorValue("INT")
public class OutInternational extends Out{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7747674835418302263L;

	@Column(name = "commission")
	private double commission;
	
	

	public OutInternational(String borderaux, String date, double montantTransfer, @NotNull User refUser, double frais,
			double commission) {
		super(borderaux, date, montantTransfer, refUser, frais);
		this.commission = commission;
	}

	public OutInternational() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}
	
	
	

}
