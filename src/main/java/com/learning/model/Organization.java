package com.learning.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "organization")
public class Organization extends Historized {
	private static final long serialVersionUID = -8858004000210805400L;

	@Column(name = "name", length = 100)
	private String name;

	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Branch> branchs;

	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Level> levels;

	private TypeOrganizationEnum type;

	private Double thresholdeCatchUp;

	private Double thresholdeSucccess;
	
	private Double scale;

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

	@Override
	public String toString() {
		return "Organization [name=" + name + "]";
	}

}
