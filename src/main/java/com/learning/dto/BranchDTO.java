package com.learning.dto;

public class BranchDTO extends HistorizedDTO {

	
	
	private String name;

	private OrganizationDTO organization;

	public BranchDTO() {
		super();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	



	public OrganizationDTO getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDTO organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "BranchDTO [name=" + name + "]";
	}



	



	
	

	

}
