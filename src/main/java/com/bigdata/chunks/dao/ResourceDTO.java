package com.bigdata.chunks.dao;

import java.util.ArrayList;
import java.util.List;

import antlr.debug.NewLineEvent;

public class ResourceDTO
{	

	int resourceId;
	String resourceName; 
	int projectId;
	String projectName;
	String bugzillaName; 
	String redmindName; 
	String isActive; 
	String modifiedDate;
	String createdDate;
	List resourceList=new ArrayList();

	public List getResourceList() {
		return resourceList;
	}
	public void setResourceList(List resourceList) {
		this.resourceList = resourceList;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	
}
