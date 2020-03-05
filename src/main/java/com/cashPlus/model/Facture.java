package com.cashPlus.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@DiscriminatorValue("F")
@Entity
public class Facture extends Telephone{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7747674835418302263L;

	
	public Facture() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Facture(String borderaux, String date, double montantTransfer, @NotNull User refUser, double frais,
			String numTelephone) {
		super(borderaux, date, montantTransfer, refUser, frais, numTelephone);
		// TODO Auto-generated constructor stub
	}

	

}
