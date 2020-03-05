package com.cashPlus.dto;

import java.util.Date;


public class RoleDTO extends HistorizedDTO {

	private static final long serialVersionUID = -8858004000210805400L;

	
	
	private String name;
	
	

	
	


	public RoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleDTO(Long id, Date createdAt, Date updatedAt) {
		super(id, createdAt, updatedAt);
		// TODO Auto-generated constructor stub
	}

	public RoleDTO(Long id, Date createdAt, Date updatedAt, String name) {
		super(id, createdAt, updatedAt);
		this.name = name;
	}

	public String getRoleName() {
		return name;
	}

	public void setRoleName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Role [name=" + name +  ", toString()=" + super.toString() + "]";
	}

	

	


	

	
}
