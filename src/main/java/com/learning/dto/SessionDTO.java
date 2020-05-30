package com.learning.dto;

import java.time.LocalDate;


public class SessionDTO extends HistorizedDTO {

	

	
	private String name;


	private OrganizationDTO organization;

	private LocalDate startDate;

	private LocalDate endDate;
	
	
	private LocalDate startDateExam;
	
	private LocalDate endDateExam;
	
	private LocalDate startDateCatchUp;
	
	private LocalDate endDateCatchUp;

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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getStartDateExam() {
		return startDateExam;
	}

	public void setStartDateExam(LocalDate startDateExam) {
		this.startDateExam = startDateExam;
	}

	public LocalDate getEndDateExam() {
		return endDateExam;
	}

	public void setEndDateExam(LocalDate endDateExam) {
		this.endDateExam = endDateExam;
	}

	public LocalDate getStartDateCatchUp() {
		return startDateCatchUp;
	}

	public void setStartDateCatchUp(LocalDate startDateCatchUp) {
		this.startDateCatchUp = startDateCatchUp;
	}

	public LocalDate getEndDateCatchUp() {
		return endDateCatchUp;
	}

	public void setEndDateCatchUp(LocalDate endDateCatchUp) {
		this.endDateCatchUp = endDateCatchUp;
	}

	@Override
	public String toString() {
		return "Session [name=" + name + ", organization=" + organization + ", startDate=" + startDate + ", endDate="
				+ endDate + ", startDateExam=" + startDateExam + ", endDateExam=" + endDateExam + ", startDateCatchUp="
				+ startDateCatchUp + ", endDateCatchUp=" + endDateCatchUp + "]";
	}



}
