package com.cashPlus.dto;

import java.io.Serializable;
import java.util.Date;

public class HistorizedDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Long id;

	protected Date createdAt;

	protected Date updatedAt;

	public HistorizedDTO() {
		super();
		// TODO Auto-generated constructor stub
	}




	public HistorizedDTO(Long id, Date createdAt, Date updatedAt) {
	super();
	this.id = id;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
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

	@Override
	public String toString() {
		return "Historized [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
