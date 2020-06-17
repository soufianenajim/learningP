package com.learning.dto;

import java.util.List;

public class CourDTO extends HistorizedDTO {

	private String name;

	private String content;

	private ModuleAffectedDTO module;

	private List<ChapitreDTO> chapitres;

	private List<ExercicesDTO> exercices;

	private ExercicesDTO td;

	private boolean launched;
	
	private boolean hasQuiz;
	
	private Long idTeacher;
	
	private UserDTO student;
	
	private List<AttachmentFileDTO> attachmentFiles;

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

	public ModuleAffectedDTO getModule() {
		return module;
	}

	public void setModule(ModuleAffectedDTO module) {
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
		return launched;
	}

	public void setLaunched(boolean launched) {
		this.launched = launched;
	}

	public boolean isHasQuiz() {
		return hasQuiz;
	}

	public void setHasQuiz(boolean hasQuiz) {
		this.hasQuiz = hasQuiz;
	}

	public Long getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(Long idTeacher) {
		this.idTeacher = idTeacher;
	}

	
	public List<AttachmentFileDTO> getAttachmentFiles() {
		return attachmentFiles;
	}

	public void setAttachmentFiles(List<AttachmentFileDTO> attachmentFiles) {
		this.attachmentFiles = attachmentFiles;
	}

	public UserDTO getStudent() {
		return student;
	}

	public void setStudent(UserDTO student) {
		this.student = student;
	}
	
	
	

	
}
