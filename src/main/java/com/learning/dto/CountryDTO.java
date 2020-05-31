package com.learning.dto;

public class CountryDTO {

	private Long id;

	private String code;

	private String lang;

	public CountryDTO(Long id, String code, String lang) {
		super();
		this.id = id;
		this.code = code;
		this.lang = lang;
	}

	public CountryDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
