package com.learning.dto;

import java.util.List;

public class OrganizationDTO extends HistorizedDTO {

	private String name;

	private List<BranchDTO> branchs;

	private List<LevelDTO> levels;

	private String type;

	private Double thresholdeCatchUp;

	private Double thresholdeSucccess;

	private Double scale;

	public OrganizationDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getThresholdeCatchUp() {
		return thresholdeCatchUp;
	}

	public void setThresholdeCatchUp(Double thresholdeCatchUp) {
		this.thresholdeCatchUp = thresholdeCatchUp;
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

	@Override
	public String toString() {
		return "OrganizationDTO [name=" + name + "]";
	}

}
