package com.synechron.prm.form;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts.action.ActionForm;

import com.synechron.prm.dao.ProjectDTO;

public class ProjectForm extends ActionForm {
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String projectName;
	private String bugzillaName;
	private String redmindName;
	private String isActive;
	private String createdDate;
	private String modifiedDate;
	private String isRedmindProject;
	
	private List<ProjectDTO> projectlist;
	
	public List<ProjectDTO> getProjectlist() {
		return projectlist;
	}
	public void setProjectlist(List<ProjectDTO> projectlist) {
		this.projectlist = projectlist;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getIsRedmindProject() {
		return isRedmindProject;
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
