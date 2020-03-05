package com.cashPlus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.cashPlus.dto.UserDTO;
@Entity
@Table(name = "user")
public class User extends Historized {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7263208288016824088L;

	@Column(name = "LOGIN", length = 30)
	private String login;

	@Column(name = "PASSWORD", length = 255)
	private String password;
	
	@Column(name = "first_name", length = 100)
	private String firstName;

	@Column(name = "last_name", length = 100)
	private String lastName;

	
	@Column(name = "TOKEN", length = 512)
	private String token;

	@Column(name = "TOKEN_DATE")
	private String tokenDate;

	@Column(name = "IS_ONLINE")
	@NotNull
	private Boolean isOnline = false;

	@Column(name = "IS_OFFLINE")
	@NotNull
	private Boolean isOffline = false;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REF_ROLE")
	private Role refRole;


     
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String firstName,@NotNull String login, @NotNull String password) {
		super();
		this.firstName=firstName;
		this.login = login;
		this.password = password;
		;
	}

	
	

	public User(UserDTO user) {
		super();

	}

	public User(Long id , String login,  String password, String firstName, String lastName, String token,
			String tokenDate, @NotNull Boolean isOnline, @NotNull Boolean isOffline,Role role) {
		super(id);
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.token = token;
		this.tokenDate = tokenDate;
		this.isOnline = isOnline;
		this.isOffline = isOffline;
		this.refRole=role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenDate() {
		return tokenDate;
	}

	public void setTokenDate(String tokenDate) {
		this.tokenDate = tokenDate;
	}

	public Boolean getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}

	public Boolean getIsOffline() {
		return isOffline;
	}

	public void setIsOffline(Boolean isOffline) {
		this.isOffline = isOffline;
	}
	
	

	

	public Role getRefRole() {
		return refRole;
	}

	public void setRefRole(Role refRole) {
		this.refRole = refRole;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + ", token=" + token + ", tokenDate=" + tokenDate
				+ ", isOnline=" + isOnline + ", isOffline=" + isOffline +  ", toString()="
				+ super.toString() + "]";
	}


	
}
