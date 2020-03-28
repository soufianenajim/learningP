package com.learning.dto;

import java.util.List;

public class ChapitreDTO extends HistorizedDTO {

	private String name;

	private CourDTO cour;

	private List<ParagrapheDTO> paragraphes;

	public ChapitreDTO() {
		super();
	}

	public ChapitreDTO(String name) {
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

	public CourDTO getCour() {
		return cour;
	}

	public void setCour(CourDTO cour) {
		this.cour = cour;
	}

	public List<ParagrapheDTO> getParagraphes() {
		return paragraphes;
	}

	public void setParagraphes(List<ParagrapheDTO> paragraphes) {
		this.paragraphes = paragraphes;
	}

}
