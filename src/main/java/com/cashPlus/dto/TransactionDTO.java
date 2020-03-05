package com.cashPlus.dto;

import java.util.Date;

public  class TransactionDTO extends HistorizedDTO {

	public TransactionDTO() {
		super();
	}



	public TransactionDTO(Long id, Date createdAt, Date updatedAt, String borderaux, String date,
			double montantTransfer, UserDTO refUser) {
		super(id, createdAt, updatedAt);
		this.borderaux = borderaux;
		this.date = date;
		this.montantTransfer = montantTransfer;
		this.refUser = refUser;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String borderaux;

	protected String date;

	protected double montantTransfer;

	protected UserDTO refUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBorderaux() {
		return borderaux;
	}

	public void setBorderaux(String borderaux) {
		this.borderaux = borderaux;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getMontantTransfer() {
		return montantTransfer;
	}

	public void setMontantTransfer(double montantTransfer) {
		this.montantTransfer = montantTransfer;
	}

	public UserDTO getRefUser() {
		return refUser;
	}

	public void setRefUser(UserDTO refUser) {
		this.refUser = refUser;
	}

	@Override
	public String toString() {
		return "Transaction [borderaux=" + borderaux + ", date=" + date + ", montantTransfer=" + montantTransfer
				+ ", refUser=" + refUser + ", toString()=" + super.toString() + "]";
	}

}
