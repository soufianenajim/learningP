package com.cashPlus.model;

import javax.persistence.DiscriminatorValue;
import javax.validation.constraints.NotNull;

@DiscriminatorValue("NAT")
public class OutNational extends Out{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7747674835418302263L;

	public OutNational(String borderaux, String date, double montantTransfer, @NotNull User refUser, double frais) {
		super(borderaux, date, montantTransfer, refUser, frais);
		// TODO Auto-generated constructor stub
	}

	public OutNational() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	

	

	

}
