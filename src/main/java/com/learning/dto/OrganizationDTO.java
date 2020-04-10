package com.learning.dto;

public class OrganizationDTO extends  HistorizedDTO {
	
	private String name;

	
	

	public OrganizationDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "OrganizationDTO [name=" + name + "]";
	}

}
