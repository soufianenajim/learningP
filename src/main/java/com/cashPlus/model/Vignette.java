package com.cashPlus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vignette")
public class Vignette extends Fawatir {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Vignette(String borderaux, String date, double montantTransfer, @NotNull User refUser, double frais,
			String matriculeVehicule) {
		super(borderaux, date, montantTransfer, refUser, frais);
		this.matriculeVehicule = matriculeVehicule;
	}
	

	public Vignette() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Column(name = "matricule_V")
	private String matriculeVehicule;

	public String getMatriculeVehicule() {
		return matriculeVehicule;
	}

	public void setMatriculeVehicule(String matriculeVehicule) {
		this.matriculeVehicule = matriculeVehicule;
	}

	@Override
	public String toString() {
		return "Vignette [matriculeVehicule=" + matriculeVehicule + ", toString()=" + super.toString() + "]";
	}
	

}
