package com.learning.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.learning.dto.UserDTO;

@Entity
@Table(name = "message")
public class Message extends Historized {

	/**
	 * 
	 */
	private static final long serialVersionUID = 163194501751659427L;
	private String message;
	private LocalDateTime time;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_from")
	private User userFrom;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_to")
	private User userTo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conversation_id")
	private Conversation conversation;

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

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public User getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}

	public User getUserTo() {
		return userTo;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}

}
