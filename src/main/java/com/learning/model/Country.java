package com.learning.model;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Country implements Serializable {

	private static final long serialVersionUID = -4167889962531021138L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 3)
	private String code;

	@Column(length = 50)
	private String englishLang;

	@Column(length = 50)
	private String frenchLang;

	@Column(length = 50)
	private String arabLang;

	public Country(Long id, String code, String englishLang, String frenchLang, String arabLang) {
		super();
		this.id = id;
		this.code = code;
		this.englishLang = englishLang;
		this.frenchLang = frenchLang;
		this.arabLang = arabLang;
	}

	public Country() {
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

	public String getEnglishLang() {
		return englishLang;
	}

	public void setEnglishLang(String englishLang) {
		this.englishLang = englishLang;
	}

	public String getFrenchLang() {
		return frenchLang;
	}

	public void setFrenchLang(String frenchLang) {
		this.frenchLang = frenchLang;
	}

	public String getArabLang() {
		return arabLang;
	}

	public void setArabLang(String arabLang) {
		this.arabLang = arabLang;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return Objects.equals(code, other.code);
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", code=" + code + ", englishLang=" + englishLang + ", frenchLang=" + frenchLang
				+ ", arabLang=" + arabLang + "]";
	}

}
