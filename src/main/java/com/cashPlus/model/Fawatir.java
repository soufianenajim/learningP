package com.cashPlus.model;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;


public abstract class Fawatir extends Transaction {

	private static final long serialVersionUID = 1L;

	public Fawatir() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Fawatir( String borderaux, String date, double montantTransfer, @NotNull User refUser,
			double frais) {
		super( borderaux, date, montantTransfer, refUser);
		this.frais = frais;
	}

	@Column(name = "frais")
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
