package com.learning.dto;

public class ParagrapheDTO extends HistorizedDTO{
	private String name;

	
	private String content;

	
	private ChapitreDTO chapitre;

	public ParagrapheDTO() {
		super();
	}

	public ParagrapheDTO(String name) {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ChapitreDTO getChapitre() {
		return chapitre;
	}

	public void setChapitre(ChapitreDTO chapitre) {
		this.chapitre = chapitre;
	}


}
