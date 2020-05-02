package com.learning.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User extends Historized {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7263208288016824088L;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "password", length = 255)
	private String password;

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(name = "phone", length = 20)
	private String phone;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;
	
	@ManyToMany
	@JoinTable(
	  name = "user_group", 
	  joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "group_id"))
	List<Group> groups;

	public User() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	



	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", token=" + token + ", tokenDate=" + tokenDate + ", isOnline=" + isOnline + ", isOffline="
				+ isOffline + ", refRole=" + refRole +  ", organization="
				+ organization + "]";
	}

}
