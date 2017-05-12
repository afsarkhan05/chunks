package com.bigdata.chunks.dao;

public class ProjectDTO
{
	
	private Integer id;
	private String projectName;
	private String bugzillaName;
	private String redmindName;
	private String isActive;
	private String createdDate;
	private String modifiedDate;
	private String isRedmindProject;


	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getIsRedmindProject() {
		return this.isRedmindProject;
	}

	public void setIsRedmindProject(String isRedmindProject) {
		this.isRedmindProject = isRedmindProject;
	}

	public String getBugzillaName() {
		return bugzillaName;
	}

	public void setBugzillaName(String bugzillaName) {
		this.bugzillaName = bugzillaName;
	}

	public String getRedmindName() {
		return redmindName;
	}

	public void setRedmindName(String redmindName) {
		this.redmindName = redmindName;
	}



}
