package com.synechron.prm.dao;

import java.util.Date;

public class GraphDTO {
	
	String projectName;
	int projectId;
	int bugzillaCount;
	int redmindCount;
	int  []bugzilla;
	int  []redmind;
	String timeStamp;
	Date bugCreatedTime;
	Date bugModifiedTime;
	String status;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getBugzillaCount() {
		return bugzillaCount;
	}
	public void setBugzillaCount(int bugzillaCount) {
		this.bugzillaCount = bugzillaCount;
	}
	public int getRedmindCount() {
		return redmindCount;
	}
	public void setRedmindCount(int redmindCount) {
		this.redmindCount = redmindCount;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public Date getBugCreatedTime() {
		return bugCreatedTime;
	}
	public void setBugCreatedTime(Date bugCreatedTime) {
		this.bugCreatedTime = bugCreatedTime;
	}
	public Date getBugModifiedTime() {
		return bugModifiedTime;
	}
	public void setBugModifiedTime(Date bugModifiedTime) {
		this.bugModifiedTime = bugModifiedTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int[] getBugzilla() {
		return bugzilla;
	}
	public void setBugzilla(int[] bugzilla) {
		this.bugzilla = bugzilla;
	}
	public int[] getRedmind() {
		return redmind;
	}
	public void setRedmind(int[] redmind) {
		this.redmind = redmind;
	}
	

}
