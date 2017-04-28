package com.synechron.prm.dao;


import java.sql.Blob;

public class AwardsDTO {
	
	Integer id;
	int empId;
	String name;
	byte[] image;
	String awardMonth;
	String desc;
	String isActive;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getAwardMonth() {
		return awardMonth;
	}
	public void setAwardMonth(String awardMonth) {
		this.awardMonth = awardMonth;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
}
