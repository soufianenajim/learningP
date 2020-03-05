package com.cashPlus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "ctm_transaction")
public class Ctm extends Transaction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8820217018926605807L;

	/**
	 * 
	 */
	
	
	
	public Ctm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ctm(String borderaux, String date, double montantTransfer, @NotNull User refUser, String numCtm) {
		super(borderaux, date, montantTransfer, refUser);
		this.numCtm = numCtm;
	}

	@Column(name = "numCtm")
	private String numCtm;

	public String getNumCtm() {
		return numCtm;
	}

	public void setNumCtm(String numCtm) {
		this.numCtm = numCtm;
	}

	@Override
	public String toString() {
		return "Ctm [numCtm=" + numCtm + ", toString()=" + super.toString() + "]";
	}
	

}
