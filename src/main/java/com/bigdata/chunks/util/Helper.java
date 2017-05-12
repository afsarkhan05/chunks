package com.bigdata.chunks.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bigdata.chunks.bean.DisplayMaster;
import com.bigdata.chunks.dao.ArticleDTO;
import com.bigdata.chunks.dao.AwardsDTO;
import com.bigdata.chunks.dao.CategoryDTO;
import com.bigdata.chunks.dao.Dao;
import com.bigdata.chunks.dao.GraphDao;
import com.bigdata.chunks.dao.PaginatorDAO;
import com.bigdata.chunks.dao.ProjectDTO;
import com.bigdata.chunks.dao.ResourceDTO;
import com.bigdata.chunks.dao.UserDTO;
import com.bigdata.chunks.form.AllocateForm;
import com.bigdata.chunks.form.ArticleFormBean;
import com.bigdata.chunks.form.AwardForm;
import com.bigdata.chunks.form.CategoryFormBean;
import com.bigdata.chunks.form.ProjectForm;
import com.bigdata.chunks.form.RegisterForm;
import com.bigdata.chunks.form.UserForm;


public class Helper 
{

	Dao dao=new Dao();
	GraphDao graphDao=new GraphDao();

	//User Operation

	public List loginCheck(RegisterForm registerForm){

		System.out.println("Util.Helper.loginCheck");

		return dao.checkLogin(registerForm);
	}
	
	public boolean adminCheck(String userName){
		
		System.out.println("Util.Helper.loginCheck");
		
		return dao.checkAdmin(userName);
	}

/*	public boolean addArticle(ArticleMaster articleMaster){

		System.out.println("util.Helper.addArticle()");

		return dao.addArticle(articleMaster);	

	}*/
	
	
	public boolean addArticleDoc(ArticleFormBean articleForm, String articleLocation){

		System.out.println("util.Helper.addArticleDoc()");

		return dao.addArticleDoc(articleForm, articleLocation);	

	}
	
	public boolean updateArticleDoc(ArticleFormBean articleForm){

		System.out.println("util.Helper.updateArticleDoc()");

		return dao.updateArticleDoc(articleForm);	

	}

	/* Cataegory Parts Start*/
	
	public List getArticle(ArticleFormBean articleForm){

		System.out.println("Util.Helper.getArticle");

		return dao.getArticle(articleForm);
	}
	

	
/*	public List getArticle(){

		System.out.println("Util.Helper.getArticle");

		return dao.getArticle();
	}*/
	
	public boolean addArticle(ArticleFormBean articleFormBean){

		System.out.println("util.Helper.addArticle()");

		return dao.addArticle(articleFormBean);	

	}

	public List<ArticleDTO> listArticle(){
		
		System.out.println("Util.Helper.listArticle");
		
		return dao.retreiveArticleList();
	}
	
	public ArticleFormBean getArticleDetail(ArticleFormBean articleFormBean){
		
		System.out.println("util.Helper.getArticleDetail()");
		
		return dao.getArticleDetail(articleFormBean);
	}
	
	public boolean deleteArticle(int articleId){

		System.out.println("util.Helper.deleteArticle()");

		return dao.deleteArticle(articleId);	

	}
	
	public boolean updateArticle(ArticleFormBean articleFormBean){

		System.out.println("util.Helper.updateArticle()");

		return dao.updateArticle(articleFormBean);	

	}
	
	/* Article parts Ends*/
	
	
	
	/* Cataegory Parts Start*/
	
	public List getCategory(){

		System.out.println("Util.Helper.getCategory");

		return dao.getCategory();
	}
	
	public boolean addCategory(CategoryFormBean categoryFormBean){

		System.out.println("util.Helper.addCategory()");

		return dao.addCategory(categoryFormBean);	

	}

	public List<CategoryDTO> listCategory(){
		
		System.out.println("Util.Helper.listCategory");
		
		return dao.retreiveCategoryList();
	}
	
	public CategoryFormBean getCategoryDetail(CategoryFormBean categoryFormBean){
		
		System.out.println("util.Helper.getCategoryDetail()");
		
		return dao.getCategoryDetail(categoryFormBean);
	}
	
	public boolean deleteCategory(int categoryId){

		System.out.println("util.Helper.deleteCategory()");

		return dao.deleteCategory(categoryId);	

	}
	
	public boolean updateCategory(CategoryFormBean categoryFormBean){

		System.out.println("util.Helper.updateCategory()");

		return dao.updateCategory(categoryFormBean);	

	}
	
	/* Category parts Ends*/
	
	
	@SuppressWarnings("unchecked")
	public List<DisplayMaster> getResultList(ArrayList returnList, HttpServletRequest request) {
		
		
		List rowList = null;
        DisplayMaster displayMaster = null;
        List<DisplayMaster> list = new ArrayList();
		if(returnList !=null && ! returnList.isEmpty()){
			//System.out.println("return list "+ returnList.size());
			for (Object object : returnList) {
				rowList = (ArrayList)object;
				if (rowList != null && rowList.size() > 0) {
					displayMaster = new DisplayMaster();
					if(isValid((String) rowList.get(0))){
						displayMaster.setTitle((String) rowList.get(0));
						displayMaster.setValue((String) rowList.get(0));
						if(request != null){
							request.setAttribute("title", CommonConstants.title + " " + (String) rowList.get(0));
							request.setAttribute("description", CommonConstants.title + " "+ (String) rowList.get(0));
						}
					}
					
					displayMaster.setDescription(setDescription((String) rowList.get(3)));

					displayMaster.setAnchor(setArticleAnchor((String) rowList.get(1), (String) rowList.get(2), request));
					list.add(displayMaster);
				}
			}
		}
		
		return list;
		
		
	}
	
	private String setDescription(String description) {
		
		if(isValid(description)){
			if(description.length() > 200){
				description = description.substring(0, 200);
			}
			description = org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4(description);
		}
		return description;
	}
	
	private String setArticleAnchor(String title, String articleId, HttpServletRequest req) {

		if(! isValid(title)){
			title = "Java Chunks";
		}
		
		String scheme = req.getScheme();             
        String serverName = req.getServerName(); 
        int serverPort = req.getServerPort();
        String contextPath = req.getContextPath();
        String url = scheme + "://" +serverName + ":" 
        		     + serverPort  
        		     + contextPath + "/chunks/article/" 
        		     + title.replaceAll(" ", "_")
        		     + "/" + articleId;

		
		return url;
	}
	
	
	private boolean isValid(String checkStr) {
		boolean result = false;
		
		if(checkStr !=null && checkStr.trim().length() > 0){
			result = true;
		}
		return result;
	}
	
	public void getFeatureArticle(HttpServletRequest request, String limit){
		
        String query = "select * from article_master am where am.isFeature='yes' and am.isActive='yes' limit "+limit;
        String[] items = {"articleName", "articleTitle","articleId", "articleDesc"};
        request.setAttribute("featuredArticle", getData(limit, query, items, request));
	}
	
	public List<DisplayMaster> getData(String limit, String query, String[] items, HttpServletRequest request){
		if(limit == null){
			limit = " 10 ";
		}
		limit = String.valueOf(Integer.parseInt(limit) * 2);
		PaginatorDAO dao = new PaginatorDAO();
        List<DisplayMaster> list = new ArrayList<DisplayMaster>();
        try{
	        ArrayList returnList = dao.fireQuery(query, false, items);
	        Collections.shuffle(returnList);
	        list = getResultList(returnList, request);
        }catch(Exception e){
        	
        }
		
		return list;
	}
	
	public void getLatestArticle(HttpServletRequest request, String limit){
		
		String query = "select * from article_master am where am.isFeature='no' and am.isActive='yes' order by createddate desc limit "+ limit;
        String[] items = {"articleName", "articleTitle","articleId", "articleDesc"};
        request.setAttribute("latestArticle", getData(limit, query, items, request));
	}
	
	public void getRelatedArticle(HttpServletRequest request, String limit){
		
		String categoryId = request.getParameter("categoryId");
		if(categoryId == null){ categoryId = "1"; }
		
        String query = "select * from article_master am where am.isActive='yes' and am.categoryId ="+ categoryId +" limit  "+ limit;
        String[] items = {"articleName", "articleTitle","articleId", "articleDesc"};
        request.setAttribute("relatedArticle", getData(limit, query, items, request));
	}
	
	
	
	public List<UserDTO> listUsers(){
		
		System.out.println("Util.Helper.listUsers");
		
		return dao.retreiveUserList();
	}
	
	public UserForm getuserDetail(UserForm userForm){
		
		System.out.println("util.Helper.getuserDetail()");
		
		return dao.getUserDetails(userForm);
	}
	
	public boolean addUser(UserForm userForm){

		System.out.println("util.Helper.addUser()");

		return dao.addUser(userForm);	

	}
	
	public boolean updateUser(UserForm userForm){

		System.out.println("util.Helper.updateUser()");

		return dao.updateUser(userForm);	

	}
	
	public boolean deleteUser(int userId){

		System.out.println("util.Helper.updateUser()");

		return dao.deleteUser(userId);	

	}
	

	
	/*
	 * Resource Details & Manipulation 
	 */

	public List<ResourceDTO> listResource(){

		System.out.println("Util.Helper.listResource");

		return null; //dao.retreiveResourceList();

	}

	public AllocateForm getResourceDetails(AllocateForm allocateForm){

		System.out.println("Util.Helper.getResourceDetails");

		return null; // dao.getResourceDetails(allocateForm);

	}

	public boolean addResource(AllocateForm allocateForm){

		System.out.println("util.Helper.addResource()");

		return false; // dao.addResource(allocateForm);	

	}

	public boolean updateResource(AllocateForm allocateForm){

		System.out.println("util.Helper.updateResource()");

		return false; // dao.updateResource(allocateForm);	

	}

	public boolean deleteResource(int resourceId){

		System.out.println("util.Helper.deleteResource()");

		return dao.deleteResource(resourceId);	

	}

	
	/*
	 * Project Details & Manipulation
	 */
	
	public List<ProjectDTO> listProject(){

		System.out.println("In the Helper.listProject");

		return null; // dao.retreiveProjectList();

	}

	public boolean addProject(ProjectForm projectForm){

		System.out.println("util.Helper.addProject()");

		return false; // dao.addProject(projectForm);	

	}

	public ProjectForm getProjectDetails(ProjectForm projectForm){

		System.out.println("util.Helper.updateResource()");

		return null; // dao.getProjectDetails(projectForm);
	}

	public boolean updateProject(ProjectForm projectForm){

		System.out.println("util.Helper.updateProject()");

		return false; // dao.updateProject(projectForm);

	}

	public boolean deleteProject(int projectId)
	{
		System.out.println("util.Helper.deleteProject()");

		return dao.deleteProject(projectId);	

	}



	/*
	 * Graph manipulation
	 */

	//getting total count for pie chart
	public int getBugZillaCount(int pid){

		System.out.println("util.Helper.getBugZillaCount()");

		return dao.getBugZillaCount(pid);

	}

	public int getRedMindCount(int pid){

		System.out.println("util.Helper.getRedMindCount()");

		return dao.getRedMindCount(pid);

	}

	
	//getting sorted array  for all as well as particular project for bar chart

	public int[] getBugZillaArrayForAll(int length, String st){

		System.out.println("util.Helper.getBugZillaArrayForAll()");

		return dao.getBugZillaArrayForAll(length, st);


	}

	public int[] getRedMindArrayForAll(int length, String st){


		System.out.println("util.Helper.getRedMindArrayForAll()");

		return dao.getRedMindArrayForAll(length, st);


	}

	public int[] getBugZillaArray(int pid, String st){

		System.out.println("util.Helper.getBugZillaArray()");

		if(pid==0){

			return dao.getBugZillaArrayForAll(pid, st);
		}

		else{
			return dao.getBugZillaArray(pid, st);
		}


	}

	public int[] getRedMindArray(int pid, String st){
		
		System.out.println("util.Helper.getRedMindArray()");

		if(pid==0){

			return dao.getRedMindArrayForAll(pid, st);

		}else{

			return dao.getRedMindArray(pid, st);
		}

	}


	/*
	 * Maturity Calculation
	 */


	public int[] getMaturityBugZillaArrayForAll(int arrayLength){


		return graphDao.getBugZillaArrayForAll(arrayLength);

	}

	public int[] getMaturityRedMindArrayForAll(int arrayLength){


		return graphDao.getRedMindArrayForAll(arrayLength);

	}

	public int[] getMaturityBugZillaArray(int pid){

		return graphDao.getBugZillaArray(pid);
	}

	public int[] getMaturityRedMindArray(int pid){

		return graphDao.getRedMindArray(pid);
	}

	
	/*
	 * Employee Awards manipulation
	 */

	public boolean addAwardsDetails(AwardForm form){

		return false; // dao.addAwards(form);
	}

	public boolean updateAwards(AwardForm form){

		return false; // dao.updateAwards(form);
	}

	public boolean deleteAwards(int id){

		return dao.deleteAwards(id);
	}

	public List<AwardsDTO> getAwards(AwardForm form){


		return null; // dao.getAwards(form);
	}

	public AwardForm getPerticularAwardDetails(AwardForm form){

		return null; // dao.getAwardDetail(form);

	}

	public List<AwardsDTO> getWinnersListOfMonth(AwardForm form){


		return null; // dao.getWinnerListOfMonth(form);
	}


}
