package com.learning.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attachment_file")
public class AttachmentFile extends Historized {

	private static final long serialVersionUID = -8858004000210805400L;

	private String attachmentPath;

	private String fileName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cour_id")
	private Cour cour;

	
	public AttachmentFile() {
		super();
		
	}


	public AttachmentFile(Long id) {
		super(id);
		// TODO Auto-generated constructor stub
	}


	public AttachmentFile(String attachmentPath, String fileName,Cour cour) {
		
		this.attachmentPath = attachmentPath;
		this.fileName = fileName;
		this.cour=cour;
	}

	
	public AttachmentFile(Long id, String attachmentPath, String fileName) {
		super(id);
		this.attachmentPath = attachmentPath;
		this.fileName = fileName;
	}


	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Cour getCour() {
		return cour;
	}

	public void setCour(Cour cour) {
		this.cour = cour;
	}
	

}
