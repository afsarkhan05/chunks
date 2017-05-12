package com.bigdata.chunks.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.*;

import com.bigdata.chunks.dao.ResourceDTO;

public class AllocateForm extends ActionForm
{
	
	private static final long serialVersionUID = 1L;
	
	int resourceId;
	String resourceName; 
	int projectId;
	String projectname;
	String bugzillaName; 
	String redmindName; 
	String isActive; 
	String modifiedDate;
	String createdDate;
	List projectList=new ArrayList();
	List resourceList=new ArrayList();

	
	public List getProjectList() {
		return projectList;
	}
	public void setProjectList(List projectList) {
		this.projectList = projectList;
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
	public List getResourceList() {
		return resourceList;
	}
	public void setResourceList(List resourceList) {
		this.resourceList = resourceList;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	
	
}
