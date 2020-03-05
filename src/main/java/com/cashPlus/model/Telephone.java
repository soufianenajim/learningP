package com.cashPlus.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING, length = 1)
public class Telephone extends Fawatir {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Column(name = "num_telephone")
	private String numTelephone;

	public Telephone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Telephone(String borderaux, String date, double montantTransfer, @NotNull User refUser, double frais,
			String numTelephone) {
		super(borderaux, date, montantTransfer, refUser, frais);
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
