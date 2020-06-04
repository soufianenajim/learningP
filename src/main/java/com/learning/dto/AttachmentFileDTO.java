package com.learning.dto;

public class AttachmentFileDTO extends HistorizedDTO {

	
	private String attachmentPath;

	private String fileName;
	
	
	private CourDTO cour;

	
	public AttachmentFileDTO() {
		super();
	
	}


	public AttachmentFileDTO(String attachmentPath, String fileName,CourDTO cour) {
		
		this.attachmentPath = attachmentPath;
		this.fileName = fileName;
		this.cour=cour;
	}

	
	public AttachmentFileDTO(Long id, String attachmentPath, String fileName) {
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

	public CourDTO getCour() {
		return cour;
	}

	public void setCour(CourDTO cour) {
		this.cour = cour;
	}
	

}
