package com.synechron.prm.dao;


// Generated Jun 21, 2012 4:19:12 PM by Hibernate Tools 3.4.0.CR1

/**
 * AuthMaster generated by hbm2java
 */
public class CategoryDTO implements java.io.Serializable {

	

	private Integer categoryId;
	
	private String categoryName;
	private String categoryDesc;
	
	private String isActive;

	private String modifiedDate;
	private String createdDate;

	

	public CategoryDTO() {
	}

	public CategoryDTO(String categoryName, String categoryDesc, 
			String modifiedDate, String createdDate,
			String isActive) {
		
		this.categoryName = categoryName;
		this.categoryDesc = categoryDesc;
		
		this.modifiedDate = modifiedDate;
		this.createdDate = createdDate;
		
		this.isActive = isActive;

	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
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


}
