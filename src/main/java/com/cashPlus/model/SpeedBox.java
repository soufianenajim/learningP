package com.cashPlus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "speedbox")
public class SpeedBox extends Transaction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public SpeedBox() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SpeedBox(String borderaux, String date, double montantTransfer, @NotNull User refUser, String numColis) {
		super(borderaux, date, montantTransfer, refUser);
		this.numColis = numColis;
	}
	@Column(name = "num_colis")
	private String numColis;
	public String getNumColis() {
		return numColis;
	}
	public void setNumColis(String numColis) {
		this.numColis = numColis;
	}
	@Override
	public String toString() {
		return "SpeedBox [numColis=" + numColis + ", toString()=" + super.toString() + "]";
	}
	
	
	
	

}
