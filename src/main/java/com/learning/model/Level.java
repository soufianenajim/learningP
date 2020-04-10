package com.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "level")
public class Level extends Historized {

	private static final long serialVersionUID = -8858004000210805400L;

	@Column(name = "name", length = 100)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Organization getOrganization() {
		return organization;
	}



	public void setOrganization(Organization organization) {
		this.organization = organization;
	}



	@Override
	public String toString() {
		return "Level [name=" + name + "]";
	}



	
	

	

}
