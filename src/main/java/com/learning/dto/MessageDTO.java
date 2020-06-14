package com.learning.dto;

import java.time.LocalDateTime;


public class MessageDTO extends HistorizedDTO {

	/**
	 * 
	 */
	
	private String message;
	private LocalDateTime time;
	private UserDTO userFrom;
	private UserDTO userTo;
	

	private ConversationDTO conversation;

	public MessageDTO() {
		super();
	}

	public MessageDTO(String message, LocalDateTime time) {
		
		this.message = message;
		this.time = time;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	

	public ConversationDTO getConversation() {
		return conversation;
	}

	public void setConversation(ConversationDTO conversation) {
		this.conversation = conversation;
	}

	public UserDTO getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(UserDTO userFrom) {
		this.userFrom = userFrom;
	}

	public UserDTO getUserTo() {
		return userTo;
	}

	public void setUserTo(UserDTO userTo) {
		this.userTo = userTo;
	}

	
	
	

}
