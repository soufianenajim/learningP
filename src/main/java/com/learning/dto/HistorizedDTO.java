package com.learning.dto;

import java.util.Date;

public abstract class HistorizedDTO {
	
	protected Long id;

	
	protected Date createdAt;

	
	protected Date updatedAt;

	 public HistorizedDTO(Long id) {
		super();
		this.id = id;
	}

	public HistorizedDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



}
