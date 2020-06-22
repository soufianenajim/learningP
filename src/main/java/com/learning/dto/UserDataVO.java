package com.learning.dto;

import java.util.List;

import com.learning.model.Role;

public class UserDataVO {

	private String username;
	private String password;
	private String email;
	List<Role> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserDataDTO [username=" + username + ", password=" + password + ", email=" + email + ", roles=" + roles
				+ "]";
	}

}
