package com.cashPlus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "autretransaction")
public class AutreTransaction extends Transaction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AutreTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AutreTransaction(String borderaux, String date, double montantTransfer, @NotNull User refUser, String info) {
		super(borderaux, date, montantTransfer, refUser);
		this.info = info;
	}
	@Column(name = "info")
	private String info ;
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "AutreTransaction [info=" + info + ", toString()=" + super.toString() + "]";
	}


	

}
