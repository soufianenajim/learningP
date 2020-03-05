package com.cashPlus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "autrefawatir")
public class AutreFawatir extends Fawatir {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	public AutreFawatir(String borderaux, String date, double montantTransfer, @NotNull User refUser, double frais,
			String refPaiement) {
		super(borderaux, date, montantTransfer, refUser, frais);
		this.refPaiement = refPaiement;
	}
	
	public AutreFawatir() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name = "ref_paiement")
	private String refPaiement;
	public String getRefPaiement() {
		return refPaiement;
	}
	public void setRefPaiement(String refPaiement) {
		this.refPaiement = refPaiement;
	}
	@Override
	public String toString() {
		return "AutreFawatir [refPaiement=" + refPaiement + ", toString()=" + super.toString() + "]";
	}
	

	
	
	

}
