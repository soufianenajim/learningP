package com.learning.dto;

public class RoleDTO extends HistorizedDTO {

	private String name;

	public RoleDTO() {
		super();
	}

	public RoleDTO(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
