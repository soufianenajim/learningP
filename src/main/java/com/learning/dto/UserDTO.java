package com.learning.dto;

import java.util.List;

public class UserDTO extends HistorizedDTO {

	private String email;

	private String password;

	private String firstName;

	private String lastName;

	private String phone;
	
	private String token;

	private String tokenDate;

	private Boolean isOnline = false;

	private Boolean isOffline = false;

	private RoleDTO refRole;

	private List<GroupDTO> groups;

	private OrganizationDTO organization;

	private Long groupId;

	private Long levelId;

	private Long branchId;
	
	private String role;

	public UserDTO() {
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

	public RoleDTO getRefRole() {
		return refRole;
	}

	public void setRefRole(RoleDTO refRole) {
		this.refRole = refRole;
	}

	public List<GroupDTO> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupDTO> groups) {
		this.groups = groups;
	}

	public OrganizationDTO getOrganization() {
		return organization;
	}

	public void setOrganization(OrganizationDTO organization) {
		this.organization = organization;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}
