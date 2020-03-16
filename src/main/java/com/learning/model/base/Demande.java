package com.learning.model.base;

public class Demande<T> {
	private T model;
	private int page;
	private int size;
	private String sortField;
	private String sortFieldSecond;
	private SortOrder sortOrder;

	public Demande() {
		super();

	}

	public Demande(T model, int page, int size) {
		super();
		this.model = model;
		this.page = page;
		this.size = size;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortFieldSecond() {
		return sortFieldSecond;
	}

	public void setSortFieldSecond(String sortFieldSecond) {
		this.sortFieldSecond = sortFieldSecond;
	}

}
