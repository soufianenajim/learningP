package com.learning.dto;

import java.util.List;

import com.learning.model.Country;
import com.learning.model.Percentage;

public class OrganizationDTO extends HistorizedDTO {

	private String name;

	private String adresse;

	private Country country;

	private Percentage percentage;

	private String phoneNumber;

	private String timeZone;

	private int timeOfBlock;

	private int nbrAttempt;

	private boolean deletable;

	private String logo;
	private String type;

	private Double thresholdeCatchUp;

	private Double thresholdeSucccess;

	private Double scale;

	private List<BranchDTO> branchs;

	private List<LevelDTO> levels;

	public List<BranchDTO> getBranchs() {
		return branchs;
	}

	public void setBranchs(List<BranchDTO> branchs) {
		this.branchs = branchs;
	}

	public List<LevelDTO> getLevels() {
		return levels;
	}

	public void setLevels(List<LevelDTO> levels) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
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
