package com.cashPlus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "devise")
public class Devise extends Transaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	

	public Devise(String borderaux, String date, double montantTransfer, @NotNull User refUser, String qualiteClient) {
		super(borderaux, date, montantTransfer, refUser);
		this.qualiteClient = qualiteClient;
	}
	

	public Devise() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Column(name = "qualite_client")
	private String qualiteClient;

	public String getQualiteClient() {
		return qualiteClient;
	}

	public void setQualiteClient(String qualiteClient) {
		this.qualiteClient = qualiteClient;
	}

	@Override
	public String toString() {
		return "Devise [qualiteClient=" + qualiteClient + ", toString()=" + super.toString() + "]";
	}
	

}
