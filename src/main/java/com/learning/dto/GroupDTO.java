package com.learning.dto;

public class GroupDTO extends HistorizedDTO {

	private String name;

	private LevelDTO level;

	private BranchDTO branch;

	private Long organizationId;

	public GroupDTO() {
		super();
	}

	public GroupDTO(String name) {
		super();
		this.name = name;
	}

	public GroupDTO(Long id) {
		super(id);

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LevelDTO getLevel() {
		return level;
	}

	public void setLevel(LevelDTO level) {
		this.level = level;
	}

	public BranchDTO getBranch() {
		return branch;
	}

	public void setBranch(BranchDTO branch) {
		this.branch = branch;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
