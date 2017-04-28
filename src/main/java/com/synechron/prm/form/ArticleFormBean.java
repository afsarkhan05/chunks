package com.synechron.prm.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class ArticleFormBean extends ActionForm
{
	
	int empId;
	String articleName;
	String articleTitle;
	FormFile articleDoc;
	String articleDesc;
	private Integer articleId;
	private String categoryId;
	
	String isActive;
	String isFeature;
	String status;
	
	private String edit = "no";

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public FormFile getArticleDoc() {
		return articleDoc;
	}

	public void setArticleDoc(FormFile articleDoc) {
		this.articleDoc = articleDoc;
	}

	public String getArticleDesc() {
		return articleDesc;
	}

	public void setArticleDesc(String articleDesc) {
		this.articleDesc = articleDesc;
	}

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsFeature() {
		return isFeature;
	}

	public void setIsFeature(String isFeature) {
		this.isFeature = isFeature;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	


}
