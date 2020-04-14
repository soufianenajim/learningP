package com.learning.dto;

import java.util.List;

public class CourDTO extends HistorizedDTO {
	
	

	
	private String name;

	
	private String introduction;
	
	private String resume;
	
	private String conclusion;
	
	private ModuleDTO module;
	
	
	private List<ChapitreDTO> chapitres;
	

	private List<QuizDTO> quizs;

	public CourDTO() {
		super();
	}

	public CourDTO(String name) {
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

	public ModuleDTO getModule() {
		return module;
	}

	public void setModule(ModuleDTO module) {
		this.module = module;
	}
	

	public List<ChapitreDTO> getChapitres() {
		return chapitres;
	}

	public void setChapitres(List<ChapitreDTO> chapitres) {
		this.chapitres = chapitres;
	}

	public List<QuizDTO> getQuizs() {
		return quizs;
	}

	public void setQuizs(List<QuizDTO> quizs) {
		this.quizs = quizs;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
	

	
}
