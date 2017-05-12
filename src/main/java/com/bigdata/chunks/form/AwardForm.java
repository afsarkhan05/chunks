package com.bigdata.chunks.form;


import java.sql.Blob;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class AwardForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	
	Integer id;
	int empId;
	String name;
	FormFile image;
	String awardMonth;
	String desc;
	String isActive;
	byte[] convertedImage;
	 
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
	
	public FormFile getImage() {
		return image;
	}
	public void setImage(FormFile image) {
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
	public byte[] getConvertedImage() {
		return convertedImage;
	}
	public void setConvertedImage(byte[] convertedImage) {
		this.convertedImage = convertedImage;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
	
	
}
