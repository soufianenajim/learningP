package com.learning.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "organization")
public class Organization extends Historized {
	private static final long serialVersionUID = -8858004000210805400L;

	@Column(name = "name", length = 100)
	private String name;

	private String adresse;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	private String phoneNumber;

	private String timeZone;

	@Column(name = "time_of_block", columnDefinition = "int default 5")
	private int timeOfBlock;

	@Column(name = "nbr_attempt", columnDefinition = "int default 3")
	private int nbrAttempt;

	@Column(updatable = false, columnDefinition = "boolean default true")
	private boolean deletable;
	@Lob
	private String logo;
	private TypeOrganizationEnum type;

	private Double thresholdeCatchUp;

	private Double thresholdeSucccess;

	private Double scale;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "percentage_id")
	private Percentage percentage;

	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Branch> branchs;

	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Level> levels;

	public List<Branch> getBranchs() {
		return branchs;
	}

	public void setBranchs(List<Branch> branchs) {
		this.branchs = branchs;
	}

	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getThresholdeCatchUp() {
		return thresholdeCatchUp;
	}

	public void setThresholdeCatchUp(Double thresholdeCatchUp) {
		this.thresholdeCatchUp = thresholdeCatchUp;
	}

	public TypeOrganizationEnum getType() {
		return type;
	}

	public void setType(TypeOrganizationEnum type) {
		this.type = type;
	}

	public Double getThresholdeSucccess() {
		return thresholdeSucccess;
	}

	public void setThresholdeSucccess(Double thresholdeSucccess) {
		this.thresholdeSucccess = thresholdeSucccess;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public int getTimeOfBlock() {
		return timeOfBlock;
	}

	public void setTimeOfBlock(int timeOfBlock) {
		this.timeOfBlock = timeOfBlock;
	}

	public int getNbrAttempt() {
		return nbrAttempt;
	}

	public void setNbrAttempt(int nbrAttempt) {
		this.nbrAttempt = nbrAttempt;
	}

	public boolean isDeletable() {
		return deletable;
	}

	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	

	public Percentage getPercentage() {
		return percentage;
	}

	public void setPercentage(Percentage percentage) {
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "Organization [name=" + name + "]";
	}

}
