package com.cashPlus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "in_transaction")
public class In extends Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "sms")
	private Long sms;

	@Column(name = "frais")
	private double frais;

	public In(String borderaux, String date, double montantTransfer, @NotNull User refUser, Long sms, double frais) {
		super(borderaux, date, montantTransfer, refUser);
		this.sms = sms;
		this.frais = frais;
	}
	

	public In() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getSms() {
		return sms;
	}

	public void setSms(Long sms) {
		this.sms = sms;
	}

	public double getFrais() {
		return frais;
	}

	public void setFrais(double frais) {
		this.frais = frais;
	}

	@Override
	public String toString() {
		return "In [sms=" + sms + ", frais=" + frais + ", toString()=" + super.toString() + "]";
	}

}
