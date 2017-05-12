package com.bigdata.chunks.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.bigdata.chunks.dao.ProjectDTO;

public class GraphResultForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	String projectName;
	int bugzilla_count;
	int redmind_count;
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getBugzilla_count() {
		return bugzilla_count;
	}
	public void setBugzilla_count(int bugzilla_count) {
		this.bugzilla_count = bugzilla_count;
	}
	public int getRedmind_count() {
		return redmind_count;
	}
	public void setRedmind_count(int redmind_count) {
		this.redmind_count = redmind_count;
	}
	

}
