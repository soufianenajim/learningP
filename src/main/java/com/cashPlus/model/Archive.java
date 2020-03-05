package com.cashPlus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "archive")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Archive extends Historized {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7263208288016824088L;

	@Column(name = "nom_client", length = 30)
	@NotNull
	private String nomClient;

	@Column(name = "prenom_client", length = 255)
	@NotNull
	private String prenomClient;
    
	@Column(name = "cin", length = 100)
	private String cin;

	@Column(name = "n_telephone", length = 100)
	private String numTelephone;

	@Column(name = "borderaux", length = 100)
	private String borderaux;

	@Column(name = "date", length = 100)
	private String date;

	@Column(name = "montant_transfer", length = 100)
	private String montantTransfer;

	@Column(name = "matricule_paiement", length = 100)
	private String matriculePaiement;

	@Column(name = "chiffre_affaire", length = 100)
	private String chiffreAffaire;
	@Column(name = "nbr_transaction", length = 100)
	private int nbrTransaction;
	

	public Archive() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Archive(@NotNull String nomClient, @NotNull String prenomClient, @NotNull String cin, String numTelephone,
			String borderaux, String date, String montantTransfer, String matriculePaiement, String chiffreAffaire,
			int nbrTransaction) {
		
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.cin = cin;
		this.numTelephone = numTelephone;
		this.borderaux = borderaux;
		this.date = date;
		this.montantTransfer = montantTransfer;
		this.matriculePaiement = matriculePaiement;
		this.chiffreAffaire = chiffreAffaire;
		this.nbrTransaction = nbrTransaction;
	}

}
