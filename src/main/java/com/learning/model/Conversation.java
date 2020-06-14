package com.learning.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "conversation")
public class Conversation extends Historized {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user1")
	private User user1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user2")
	private User user2;
	
	private Long lastUserSend;

	private int notReadMessages;

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public int getNotReadMessages() {
		return notReadMessages;
	}

	public void setNotReadMessages(int notReadMessages) {
		this.notReadMessages = notReadMessages;
	}

	public Long getLastUserSend() {
		return lastUserSend;
	}

	public void setLastUserSend(Long lastUserSend) {
		this.lastUserSend = lastUserSend;
	}
	
	
	

}
