package com.learning.dto;

import java.util.List;

public class CourDTO extends HistorizedDTO {

	private String name;

	private String content;

	private ModuleDTO module;

	private List<ChapitreDTO> chapitres;

	private List<ExercicesDTO> exercices;

	private ExercicesDTO td;

	private boolean isLaunched;
	
	private boolean hasQuiz;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<ExercicesDTO> getExercices() {
		return exercices;
	}

	public void setExercices(List<ExercicesDTO> exercices) {
		this.exercices = exercices;
	}

	public ExercicesDTO getTd() {
		return td;
	}

	public void setTd(ExercicesDTO td) {
		this.td = td;
	}

	public boolean isLaunched() {
		return isLaunched;
	}

	public void setLaunched(boolean isLaunched) {
		this.isLaunched = isLaunched;
	}

	public boolean isHasQuiz() {
		return hasQuiz;
	}

	public void setHasQuiz(boolean hasQuiz) {
		this.hasQuiz = hasQuiz;
	}
	

}
