package com.learning.dto;

public class LevelDTO extends HistorizedDTO {

	
	private String name;

	private OrganizationDTO organization;

	
	


	public LevelDTO() {
		super();
	}
	public LevelDTO(Long id) {
		super(id);
	
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
		return "LevelDTO [name=" + name + "]";
	}



	
	

	

}
