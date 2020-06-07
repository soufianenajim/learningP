package com.learning.dto;

public class ConversationDTO extends HistorizedDTO {

	private UserDTO user1;


	private UserDTO user2;

	private Long notReadMessages;

	public UserDTO getUser1() {
		return user1;
	}

	public void setUser1(UserDTO user1) {
		this.user1 = user1;
	}

	public UserDTO getUser2() {
		return user2;
	}

	public void setUser2(UserDTO user2) {
		this.user2 = user2;
	}

	public Long getNotReadMessages() {
		return notReadMessages;
	}

	public void setNotReadMessages(Long notReadMessages) {
		this.notReadMessages = notReadMessages;
	}



}
