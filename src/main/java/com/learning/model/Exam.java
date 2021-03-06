package com.learning.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "exam")
public class Exam extends Historized {

	private static final long serialVersionUID = -8858004000210805400L;
	
	

	@Column(name = "name", length = 100)
	private String name;
	
	@Column(name="start_date_time")
	private LocalDateTime startDateTime;
	
	@Column(name="end_date_time")
	private LocalDateTime endDateTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "module_id")
	private ModuleAffected module;

	private TypeEnumExam type;
	
	@Column(name = "launched", columnDefinition = "boolean default false", nullable = false)
	private boolean launched;
	
	private double scale;
	
	
	@OneToMany(mappedBy = "exam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<NoteExam> noteExams;
	
	@OneToMany(mappedBy = "exam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Question> questions;
	
	
	
	public Exam() {
		super();
	}

	public Exam(String name) {
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

	public ModuleAffected getModule() {
		return module;
	}

	public void setModule(ModuleAffected module) {
		this.module = module;
	}
	
	

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	
	public TypeEnumExam getType() {
		return type;
	}

	public void setType(TypeEnumExam type) {
		this.type = type;
	}
	

	public boolean isLaunched() {
		return launched;
	}

	public void setLaunched(boolean launched) {
		this.launched = launched;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}
	
	

	public List<NoteExam> getNoteExams() {
		return noteExams;
	}

	public void setNoteExams(List<NoteExam> noteExams) {
		this.noteExams = noteExams;
	}

	@Override
	public String toString() {
		return "Exam [name=" + name + ", module=" + module + "]";
	}

	

	

	
	

}
