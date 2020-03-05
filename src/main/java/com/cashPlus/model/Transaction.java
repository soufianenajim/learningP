package com.cashPlus.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class Transaction extends Historized {
	
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "borderaux")
	protected String borderaux;

	@Column(name = "date")
	private String date;

	@Column(name = "montant_transfer")
	private double montantTransfer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REF_USER")
	private User refUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REF_ARCHIVE")
	private Archive refArchive;
	
	@Column(name = "isArchive")
	private boolean isArchive=false;

	public Transaction() {
		super();
	}

	public Transaction( String borderaux, String date, double montantTransfer, @NotNull User refUser) {
		
		this.borderaux = borderaux;
		this.date = date;
		this.montantTransfer = montantTransfer;
		this.refUser = refUser;
	}
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

	public User getRefUser() {
		return refUser;
	}

	public void setRefUser(User refUser) {
		this.refUser = refUser;
	}

	public Archive getRefArchive() {
		return refArchive;
	}

	public void setRefArchive(Archive refArchive) {
		this.refArchive = refArchive;
	}

	public boolean isArchive() {
		return isArchive;
	}

	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}

	@Override
	public String toString() {
		return "Transaction [borderaux=" + borderaux + ", date=" + date + ", montantTransfer=" + montantTransfer
				+ ", refUser=" + refUser + ", toString()=" + super.toString() + "]";
	}

	

	
}
