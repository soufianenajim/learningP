package com.learning.dto;

import java.util.List;

public class OrganizationDTO extends HistorizedDTO {

	private String name;

	private List<BranchDTO> branchs;

	private List<LevelDTO> levels;

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

	@Override
	public String toString() {
		return "OrganizationDTO [name=" + name + "]";
	}

}
