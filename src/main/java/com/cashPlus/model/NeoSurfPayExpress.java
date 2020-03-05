package com.cashPlus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "neoSurfPayExpress")

public class NeoSurfPayExpress extends Transaction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	
	@Column(name = "num_commande")
	private String numCommande;
	
	


	public NeoSurfPayExpress(String borderaux, String date, double montantTransfer, @NotNull User refUser,
			String numCommande) {
		super(borderaux, date, montantTransfer, refUser);
		this.numCommande = numCommande;
	}
	
	public NeoSurfPayExpress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNumCommande() {
		return numCommande;
	}
	public void setNumCommande(String numCommande) {
		this.numCommande = numCommande;
	}
	@Override
	public String toString() {
		return "NeoSurfPayExpress [numCommande=" + numCommande + ", toString()=" + super.toString() + "]";
	}
	

}
