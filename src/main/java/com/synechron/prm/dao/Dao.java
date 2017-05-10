package com.synechron.prm.dao;

import com.synechron.prm.bean.ArticleMaster;
import com.synechron.prm.bean.AuthMaster;
import com.synechron.prm.bean.CategoryMaster;
import com.synechron.prm.bean.RecoverMaster;
import com.synechron.prm.form.ArticleFormBean;
import com.synechron.prm.form.CategoryFormBean;
import com.synechron.prm.form.RegisterForm;
import com.synechron.prm.form.UserForm;
import com.synechron.prm.util.CommonConstants;
import com.synechron.prm.util.CustomDate;
import com.synechron.prm.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;

import java.util.*;


public class Dao
{

	CustomDate customDate=new CustomDate();
	

	/*
	 * User Operation/
	 * TableName: auth_master ClassName:AuthMaster
	 */

	//checking Login
	@SuppressWarnings("unchecked")
	public List checkLogin(RegisterForm registerForm){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		System.out.println("dao.Dao.checkLogin()");
		System.out.println("Checking Login for:>> "+ registerForm.getUsername());

		String sql="select authmaster.userName, authmaster.isAdmin, authmaster.userId from AuthMaster authmaster where userName='"+registerForm.getUsername()+"' and password ='"+registerForm.getPassword()+"' and isActive='yes'";
		Query q=session.createQuery(sql);

		List list=q.list();

		/*		Integer i=new Integer(1);
		Object obj=list.get(0);

		String s1=String.valueOf(obj);
		String s2=String.valueOf(i);

		if(s1.equals(s2))
			outcome=true;*/
		session.close();

		return list;

	}

	//checking admin
	public boolean checkAdmin(String userName){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		Transaction transaction=null;
		String result=null;
		System.out.println("dao.Dao.checkAdmin()");
		System.out.println(userName);

		try {

			transaction=session.beginTransaction();

			Query query=session.createSQLQuery("select isAdmin from auth_master where userName = :userName")
					.addScalar("isAdmin", StandardBasicTypes.STRING);
			query.setParameter("userName", userName);

			result = (String) query.uniqueResult(); 
			session.flush();
			session.clear();


			if(result.equals("yes")){
				outcome=true;
			}else{
				outcome=false;
			}


		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		finally{
			transaction.commit();
			session.close();
		}
		return outcome;

	}
	
	


	
	//get all user list
	public List<UserDTO> retreiveUserList()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("dao.Dao.retreiveUserList()");
		Transaction transaction = null;
		List<AuthMaster> allUsers=null; 
		List<UserDTO> userDTOList=new ArrayList<UserDTO>();

		try {
			transaction = session.beginTransaction(); 
			Query queryResult = session.createQuery("from AuthMaster");  
			allUsers =(List<AuthMaster>)queryResult.list();  

			for(int i=0;i<allUsers.size();i++){

				UserDTO userDTO=new UserDTO();
				AuthMaster usr=allUsers.get(i);
				userDTO.setUserId(usr.getUserId());
				userDTO.setUserName(usr.getUserName());
				userDTO.setPassword(usr.getPassword());
				userDTO.setFirstName(usr.getFirstName());
				userDTO.setLastName(usr.getLastName());
				userDTO.setEmail(usr.getEmail());
				userDTO.setMobile(usr.getMobile());
				userDTO.setIsActive(usr.getIsActive());
				userDTO.setIsAdmin(usr.getIsAdmin());
				userDTO.setModifiedDate(usr.getModifiedDate());
				userDTO.setCreatedDate(usr.getCreatedDate());

				userDTOList.add(userDTO);
			}
			session.flush();
			session.clear();

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}

		System.out.println("Database contents delivered from dao.dao.retreiveUserList()");   

		return userDTOList;

	}



	//adding user
	public boolean addUser(UserForm userForm){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		AuthMaster authMaster=new AuthMaster();
		RecoverMaster recoveryMaster=new RecoverMaster();
		System.out.println("dao.Dao.addUser()");
		System.out.println(userForm.getUserName());
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction(); 

			populatePOJO(userForm, authMaster);
			
			Calendar cal = GregorianCalendar.getInstance();
			authMaster.setModifiedDate(CommonConstants.SDF.format(cal.getTime()));
			authMaster.setCreatedDate(CommonConstants.SDF.format(cal.getTime()));
			
			session.save(authMaster);
			
			recoveryMaster.setUserId(authMaster.getUserId());
			recoveryMaster.setSecurityQuestion(userForm.getSecurityQuestion());
			recoveryMaster.setSecurityAnswer(userForm.getSecurityAnswer());
			recoveryMaster.setModifiedDate(CommonConstants.SDF.format(cal.getTime()));
			recoveryMaster.setCreatedDate(CommonConstants.SDF.format(cal.getTime()));
			
			session.save(recoveryMaster);
			
			outcome=true;
			session.flush();
			session.clear();

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{

			transaction.commit();
			session.close();
		}
		return outcome;

	}

	private void populatePOJO(UserForm userForm, AuthMaster authMaster) {
		
		authMaster.setUserName(userForm.getUserName());
		authMaster.setPassword(userForm.getPassword());
		authMaster.setEmail(userForm.getEmail());
		
		authMaster.setIsActive(CommonConstants.ACTIVE);
		authMaster.setIsAdmin(CommonConstants.INACTIVE);
		
			
		if(isValid(userForm.getFirstName()))
		authMaster.setFirstName(userForm.getFirstName());
		
		if(isValid(userForm.getLastName()))
		authMaster.setLastName(userForm.getLastName());
		
		if(isValid(userForm.getMobile()))
		authMaster.setMobile(userForm.getMobile());
		
		
		
		if(isValid(userForm.getGender()))
		authMaster.setGender(userForm.getGender());
		
		if(isValid(userForm.getAddress()))
		authMaster.setAddress(userForm.getAddress());
		
		if(isValid(userForm.getCity()))
		authMaster.setCity(userForm.getCity());
		
		if(isValid(userForm.getState()))
		authMaster.setState(userForm.getState());
		
		if(isValid(userForm.getCountry()))
		authMaster.setCountry(userForm.getCountry());
		//authMaster.setMobile(userForm.getMobile());

		
		if(isValid(userForm.getAboutUser())){
			authMaster.setAboutUser(userForm.getAboutUser());
		}
		
	}
	
	private void populateForm(UserForm userForm, AuthMaster authMaster) {


		userForm.setUserName(authMaster.getUserName());
		userForm.setPassword(authMaster.getPassword());
		userForm.setEmail(authMaster.getEmail());
		
		userForm.setIsActive(authMaster.getIsActive());
		userForm.setIsAdmin(authMaster.getIsAdmin());
		
			
		if(isValid(authMaster.getFirstName()))
		userForm.setFirstName(authMaster.getFirstName());
		
		if(isValid(authMaster.getLastName()))
		userForm.setLastName(authMaster.getLastName());
		
		if(isValid(authMaster.getMobile()))
		userForm.setMobile(authMaster.getMobile());
		
		if(isValid(authMaster.getGender()))
		userForm.setGender(authMaster.getGender());
		
		if(isValid(authMaster.getAddress()))
		userForm.setAddress(authMaster.getAddress());
		
		if(isValid(authMaster.getCity()))
		userForm.setCity(authMaster.getCity());
		
		if(isValid(authMaster.getState()))
		userForm.setState(authMaster.getState());
		
		if(isValid(authMaster.getCountry()))
		userForm.setCountry(authMaster.getCountry());
		//userForm.setMobile(authMaster.getMobile());

		
		if(isValid(authMaster.getAboutUser())){
			userForm.setAboutUser(authMaster.getAboutUser());
		}

		
	}
	
/*	private void populatePOJO(ArticleFormBean articleForm,
			ArticleMaster articleMaster, String articleLoc) {
		
		articleMaster.setArticleLocation(articleLoc);
		articleMaster.setArticleTitle(articleForm.getArticleTitle());
		articleMaster.setStatus(CommonConstants.PENDING);
		
		articleMaster.setCategoryId(Integer.parseInt(articleForm.getCategoryId()));//default category 1
		articleMaster.setIsActive(CommonConstants.ACTIVE);
		articleMaster.setArticleName(articleForm.getArticleName());
		articleMaster.setIsFeature(CommonConstants.INACTIVE);
		
	}*/
	
	private void populateForm(ArticleFormBean articleFormBean,
			ArticleMaster articleMaster) {

		articleFormBean.setArticleTitle(articleMaster.getArticleTitle());
		articleFormBean.setStatus(articleMaster.getStatus());
		
		articleFormBean.setCategoryId(String.valueOf(articleMaster.getCategoryId()));//default category 1
		articleFormBean.setIsActive(articleMaster.getIsActive());
		articleFormBean.setArticleName(articleMaster.getArticleName());
		articleFormBean.setIsFeature(articleMaster.getIsFeature());

		
		String desc = articleMaster.getArticleDesc();
		/*if(desc.length()>200){
			desc = desc.substring(0,200);
		}*/
		articleFormBean.setArticleDesc(org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4(desc));
		
	}
	
	private void populatePojo(ArticleFormBean articleForm,
			ArticleMaster articleMaster) {
		
		if(isValid(articleForm.getArticleDesc())){
			String articleDesc = articleForm.getArticleDesc();
			articleDesc = replaceAndEscape(articleDesc);
			
			articleMaster.setArticleDesc(articleDesc);
		}
		if(isValid(articleForm.getArticleName())){
			articleMaster.setArticleName(articleForm.getArticleName());
		}
		if(isValid(articleForm.getArticleTitle())){
			articleMaster.setArticleTitle(articleForm.getArticleTitle());
		}
		
		if(isValid(articleForm.getStatus())){
			articleMaster.setStatus(articleForm.getStatus());
		}else{
			articleMaster.setStatus(CommonConstants.PENDING);
		}
		
		if(isValid(articleForm.getIsFeature())){
			articleMaster.setIsFeature(articleForm.getIsFeature());
		}else{
			articleMaster.setIsFeature(CommonConstants.INACTIVE);
		}
		
		if(isValid(articleForm.getCategoryId())){
			articleMaster.setCategoryId(Integer.parseInt(articleForm.getCategoryId()));
		}else{
			articleMaster.setCategoryId(1);//default
		}
		
		if(isValid(articleForm.getIsActive())){
			articleMaster.setIsActive(articleForm.getIsActive());
		}else{
			articleMaster.setIsActive(CommonConstants.INACTIVE);
		}
		
	}
	
	private void populateArticleDTO(ArticleMaster articleMaster, ArticleDTO articleDTO) {


		articleDTO.setArticleTitle(articleMaster.getArticleTitle());
		articleDTO.setStatus(articleMaster.getStatus());
		
		articleDTO.setCategoryId(articleMaster.getCategoryId());//default category 1
		articleDTO.setIsActive(articleMaster.getIsActive());
		articleDTO.setArticleName(articleMaster.getArticleName());
		articleDTO.setIsFeature(articleMaster.getIsFeature());
		articleDTO.setIsFile("no");
		String desc = articleMaster.getArticleDesc();
		if(desc == null){
			desc = articleMaster.getArticleLocation();
			articleDTO.setIsFile("yes");
		}else{
			if(desc.length()>200){
				desc = desc.substring(0,200);
			}
		}
		articleDTO.setArticleDesc(org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4(desc));
		
	}
	

	private boolean isValid(String checkStr) {
		boolean result = false;
		
		if(checkStr !=null && checkStr.trim().length() > 0){
			result = true;
		}
		return result;
	}

	//update user
	public boolean updateUser(UserForm userForm){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		AuthMaster authMaster=new AuthMaster();
		System.out.println("dao.Dao.updateUser()");
		System.out.println(userForm.getUserName());
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction(); 
			
			populatePOJO(userForm, authMaster);
			authMaster.setUserId(userForm.getUserId());
			
			Calendar cal = GregorianCalendar.getInstance();
			authMaster.setModifiedDate(CommonConstants.SDF.format(cal.getTime()));
			
			
			
			session.update(authMaster);
			outcome=true;
			session.flush();
			session.clear();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		finally{

			transaction.commit();
			session.close();
		}
		return outcome;
	}

	//delete user
	public boolean deleteUser(int userId){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		Transaction transaction=null;
		System.out.println("dao.Dao.deleteUser()");
		System.out.println(userId);

		try {

			transaction=session.beginTransaction();
			Query query=session.createQuery("delete from AuthMaster where userId = :userId");	
			query.setParameter("userId", userId);
			int i=query.executeUpdate();
			session.flush();
			session.clear();
			if(i!=0)
				outcome=true;


		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{
			transaction.commit();
			session.close();
		}
		return outcome;


	}

	//get particular user details
	public UserForm getUserDetails(UserForm userForm){

		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("In the dao.Dao.getResourceDetails()");
		Transaction transaction = null;
		List<AuthMaster> allResource=null; 


		try {
			transaction = session.beginTransaction(); 
			Query queryResult = session.createQuery("from AuthMaster where userId =:userId"); 
			queryResult.setParameter("userId",userForm.getUserId());

			allResource =(List<AuthMaster>)queryResult.list();  


			for(int i=0;i<allResource.size();i++){


				AuthMaster authMaster=allResource.get(i);

				populateForm(userForm, authMaster);


			}
			session.flush();
			session.clear();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}

		System.out.println("Database contents delivered from dao.Dao.getResourceDetails()");   




		return userForm;
	}



	/*
	 * Resources CRUD operation 
	 * TableName: resource_master ClassName: ResourceMaster
	 */

	//	Get all resource list
/*	public List<ResourceDTO> retreiveResourceList()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("dao.Dao.retreiveResourceList()");
		Transaction transaction = null;
		List<ResourceMaster> allResource=null; 
		List<ResourceDTO> rsrcDTOList=new ArrayList<ResourceDTO>();

		try {
			transaction = session.beginTransaction(); 
			Query queryResult = session.createQuery("from ResourceMaster");  
			allResource =(List<ResourceMaster>)queryResult.list();  

			for(int i=0;i<allResource.size();i++){

				ResourceDTO rsrcDTO=new ResourceDTO();
				ResourceMaster rsrc=allResource.get(i);	
				rsrcDTO.setResourceId(rsrc.getId());
				rsrcDTO.setResourceName(rsrc.getResourceName());
				rsrcDTO.setBugzillaName(rsrc.getBugzillaName());
				rsrcDTO.setRedmindName(rsrc.getRedmindName());
				rsrcDTO.setIsActive(rsrc.getIsActive());
				rsrcDTO.setProjectId(rsrc.getProjectmaster());
				rsrcDTO.setProjectName(getProjectName(rsrc.getProjectmaster()));
				rsrcDTO.setModifiedDate(rsrc.getModifiedDate());
				rsrcDTO.setCreatedDate(rsrc.getCreatedDate());
				rsrcDTOList.add(rsrcDTO);
			}
			session.flush();
			session.clear();

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}

		System.out.println("Database contents delivered from dao.dao.retreiveResourceList()");   

		return rsrcDTOList;

	} */


	//get project name
	public String getProjectName(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		List result=null;
		String name=null;
		int nameLength=0;
		try {
			transaction = session.beginTransaction(); 
			String sql="select ProjectName from projectmaster where id ="+ id;
			Query queryResult = session.createSQLQuery(sql)
					.addScalar("ProjectName", StandardBasicTypes.TEXT);

			result=queryResult.list();
			name=result.toString();
			nameLength=name.length();
			session.flush();
			session.clear();

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}


		return name.substring(1, --nameLength);

	}


	//	Get Resource Details for updateresource.jsp 
/*	public AllocateForm getResourceDetails(AllocateForm allocateForm){

		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("In the dao.Dao.getResourceDetails()");
		Transaction transaction = null;
		List<ResourceMaster> allResource=null; 


		try {
			transaction = session.beginTransaction(); 
			Query queryResult = session.createQuery("from ResourceMaster where resource_id =:resourceId"); 
			queryResult.setParameter("resourceId",allocateForm.getResourceId());
			allResource =(List<ResourceMaster>)queryResult.list();  


			for(int i=0;i<allResource.size();i++){


				ResourceMaster resourceMaster=allResource.get(i);
				allocateForm.setResourceId(resourceMaster.getResourceId());
				allocateForm.setResourceName(resourceMaster.getResourceName());
				allocateForm.setProjectId(resourceMaster.getProjectmaster());
				allocateForm.setBugzillaName(resourceMaster.getBugzillaName());
				allocateForm.setRedmindName(resourceMaster.getRedmindName());
				allocateForm.setIsActive(resourceMaster.getIsActive());
				allocateForm.setModifiedDate(resourceMaster.getModifiedDate());
				allocateForm.setCreatedDate(resourceMaster.getCreatedDate());


			}
			session.flush();
			session.clear();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}

		System.out.println("Database contents delivered from dao.Dao.getResourceDetails()");   
		return allocateForm;


	}*/

	//Get project list for addresource.jsp 
/*	public static  List<ProjectDTO> getProjectList(boolean isAddResource){

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Projectmaster> allProjects=null;
		List<ProjectDTO> projectList=new ArrayList<ProjectDTO>();

		try {
			transaction = session.beginTransaction(); 
			String query = " from Projectmaster";
			if(! isAddResource){
				query = query + " where isActive='yes'";
			}
			Query queryResult = session.createQuery( query );  

			allProjects =(List<Projectmaster>) queryResult.list();  

			for(int i=0;i<allProjects.size();i++){

				ProjectDTO prjDTO=new ProjectDTO();
				Projectmaster prj=allProjects.get(i);	
				prjDTO.setId(prj.getId());
				prjDTO.setProjectName(prj.getProjectName());
				projectList.add(prjDTO);	

			}
			session.flush();
			session.clear();

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}

		System.out.println("Database contents delivered from dao.Dao.retreiveProjectList");   
		return projectList;

	}*/

	//	Add Resources 
/*	public boolean addResource(AllocateForm allocateform){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		ResourceMaster resourceMaster=new ResourceMaster();
		System.out.println("dao.Dao.addResource()");
		System.out.println(allocateform.getResourceName());
		System.out.println(allocateform.getProjectId());
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction(); 



			//resourceMaster.setId(resourceId.getResourceId());
			//resourceMaster.setId();
			resourceMaster.setResourceName(allocateform.getResourceName());
			resourceMaster.setBugzillaName(allocateform.getBugzillaName());
			resourceMaster.setRedmindName(allocateform.getRedmindName());
			resourceMaster.setIsActive(allocateform.getIsActive());
			resourceMaster.setProjectmaster(allocateform.getProjectId());
			resourceMaster.setModifiedDate(allocateform.getModifiedDate());
			resourceMaster.setCreatedDate(allocateform.getCreatedDate());
			session.save(resourceMaster);
			outcome=true;
			session.flush();
			session.clear();

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{

			transaction.commit();
			session.close();
		}
		return outcome;

	}*/

	//	update resource
/*	public boolean updateResource(AllocateForm allocateform){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		ResourceMaster resourceMaster=(ResourceMaster)session.get(ResourceMaster.class,allocateform.getResourceId());

		System.out.println("dao.Dao.updateResource()");
		System.out.println(allocateform.getResourceName());

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction(); 


			//setting form values to Resource Master POJO

			resourceMaster.setBugzillaName(allocateform.getBugzillaName());
			resourceMaster.setRedmindName(allocateform.getRedmindName());
			resourceMaster.setIsActive(allocateform.getIsActive());
			resourceMaster.setProjectmaster(allocateform.getProjectId());
			resourceMaster.setModifiedDate(allocateform.getModifiedDate());
			resourceMaster.setCreatedDate(allocateform.getCreatedDate());

			session.update(resourceMaster);

			outcome=true;
			session.flush();
			session.clear();

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{
			transaction.commit();
			session.close();
		}
		return outcome;

	}*/

	//	delete resource
	public boolean deleteResource(int resourceId){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		Transaction transaction=null;
		System.out.println("dao.Dao.deleteResource()");
		System.out.println(resourceId);

		try {

			transaction=session.beginTransaction();
			Query query=session.createQuery("delete from ResourceMaster where resource_id = :resourceId");	
			query.setParameter("resourceId", resourceId);
			int i=query.executeUpdate();
			session.flush();
			session.clear();
			if(i!=0)
				outcome=true;


		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{
			transaction.commit();
			session.close();
		}
		return outcome;


	}


	/*
	 * Project CRUD operation  
	 * Table Name: projectmaster ClassName: ProjectMaster
	 */

	//	Get all project list
/*	public List<ProjectDTO> retreiveProjectList()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;
		List<Projectmaster> allProjects=null; 
		List<ProjectDTO> prjDTOList=new ArrayList<ProjectDTO>();

		try {
			transaction = session.beginTransaction(); 
			Query queryResult = session.createQuery("from Projectmaster");  
			allProjects =(List<Projectmaster>) queryResult.list();  

			for(int i=0;i<allProjects.size();i++){

				ProjectDTO prjDTO=new ProjectDTO();
				Projectmaster prj=allProjects.get(i);			
				prjDTO.setId(prj.getId());
				prjDTO.setProjectName(prj.getProjectName());
				prjDTO.setIsActive(prj.getIsActive());
				prjDTO.setModifiedDate(prj.getModifiedDate());
				prjDTO.setCreatedDate(prj.getCreatedDate());
				prjDTO.setIsRedmindProject(prj.getIsRedmindProject());
				prjDTOList.add(prjDTO);
			}
			session.flush();
			session.clear();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}

		System.out.println("Database contents delivered from dao.Dao.retreiveProjectList");   
		return prjDTOList;

	}  */

	//	get project details for updateproject.jsp
/*	public ProjectForm getProjectDetails(ProjectForm projectForm){

		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("In the dao.Dao.getResourceDetails()");
		Transaction transaction = null;
		List<Projectmaster> allProjects=null; 


		try {
			transaction = session.beginTransaction(); 
			Query queryResult = session.createQuery("from Projectmaster where id =:projectId"); 
			queryResult.setParameter("projectId",projectForm.getId());
			allProjects =(List<Projectmaster>)queryResult.list();  


			for(int i=0;i<allProjects.size();i++){


				Projectmaster projectmaster=allProjects.get(i);

				projectForm.setId(projectmaster.getId());
				projectForm.setProjectName(projectmaster.getProjectName());
				projectForm.setRedmindName(projectmaster.getRedmindName());
				projectForm.setBugzillaName(projectmaster.getBugzillaName());
				projectForm.setIsActive(projectmaster.getIsActive());
				projectForm.setCreatedDate(projectmaster.getCreatedDate());
				projectForm.setModifiedDate(projectmaster.getModifiedDate());
				projectForm.setIsRedmindProject(projectmaster.getIsRedmindProject());

			}
			session.flush();
			session.clear();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}

		System.out.println("Database contents delivered from dao.Dao.getProjectDetails()");   

		return projectForm;
	}*/

	//	add project
/*	public boolean addProject(ProjectForm form) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		System.out.println("dao.Dao.addProject()");
		Projectmaster projectmaster=new Projectmaster();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction(); 


			projectmaster.setProjectName(form.getProjectName());
			projectmaster.setRedmindName(form.getRedmindName());
			projectmaster.setBugzillaName(form.getBugzillaName());
			projectmaster.setIsActive(form.getIsActive());
			projectmaster.setModifiedDate(form.getModifiedDate());
			projectmaster.setCreatedDate(form.getCreatedDate());
			projectmaster.setIsRedmindProject(form.getIsRedmindProject());

			session.save(projectmaster);

			session.flush();
			session.clear();
			outcome=true;

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{

			transaction.commit();
			session.close();
		}
		return outcome;

	}

	//	update project
	public boolean updateProject(ProjectForm form) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		Projectmaster projectMaster=(Projectmaster)session.get(Projectmaster.class, form.getId());

		Transaction transaction = null;

		try {
			transaction = session.beginTransaction(); 
			//setting form values to Project Master POJO

			projectMaster.setProjectName(form.getProjectName());
			projectMaster.setRedmindName(form.getRedmindName());
			projectMaster.setBugzillaName(form.getBugzillaName());
			projectMaster.setIsActive(form.getIsActive());
			projectMaster.setCreatedDate(form.getCreatedDate());
			projectMaster.setModifiedDate(form.getModifiedDate());
			projectMaster.setIsRedmindProject(form.getIsRedmindProject());

			session.update(projectMaster);

			session.flush();
			session.clear();
			outcome=true;

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{

			transaction.commit();
			session.close();
		}

		return outcome;
	}*/

	//	delete project
	public boolean deleteProject(int projectid) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		System.out.println("dao.Dao.deleteProject()");
		System.out.println(projectid);
		Transaction transaction = null;

		try {
			transaction=session.beginTransaction();
			Query query=session.createQuery("delete from Projectmaster where id = :projectid");	
			query.setParameter("projectid", projectid);
			int i=query.executeUpdate();
			session.flush();
			session.clear();
			if(i!=0)
				outcome=true;


		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{
			transaction.commit();
			session.close();
		}
		return outcome;

	}


	/*
	 *Graph Manipulation 
	 * Table Name: bugzilla_master & redmind_master ClassName: BugzillaMaster & RedmindMaster
	 */

	//getting all project bug count for Bugzilla pie chart
	public int getTotalBugCountForPieBugzilla(){

		Session session = HibernateUtil.getSessionFactory().openSession();
		GraphDTO dto=new GraphDTO();
		int bugzillaCount = 0;

		//System.out.println("getting total Bug Count");
		try {
			String sqlQuery = "SELECT SUM(a.bug_Count)'count' " +
					" FROM bug_master a , (SELECT  projectid, MAX(modifiedDate) 'modifiedDate' FROM bug_master GROUP BY projectid) b" +
					" WHERE a.projectid = b.projectid  AND a.modifiedDate = b.modifiedDate ";

			Query query = session.createSQLQuery(sqlQuery)
					.addScalar("count", StandardBasicTypes.INTEGER);

			bugzillaCount = (Integer) query.uniqueResult();

			System.out.println("Bugzilla Count " + bugzillaCount);
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();
		}
		return bugzillaCount;
	}

	//getting all project bug count for Redmind pie chart
	public int getTotalBugCountForPieRedmind(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		GraphDTO dto=new GraphDTO();
		int redmind = 0;

		try{
			String sqlQuery1 = "SELECT SUM(a.bug_Count)'count' " +
					" FROM redmind_master a , (SELECT  projectid, MAX(modifiedDate) 'modifiedDate' FROM redmind_master GROUP BY projectid) b" +
					" WHERE a.projectid = b.projectid  AND a.modifiedDate = b.modifiedDate ";

			Query query1 = session.createSQLQuery(sqlQuery1)
					.addScalar("count", StandardBasicTypes.INTEGER);

			redmind = (Integer) query1.uniqueResult();

			System.out.println("Redmind Count " + redmind);

		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();
		}

		return redmind;

	}

	//getting total count for particular project  for pie chart

	//getting total count from bugzilla_master for particular project 
	public int getBugZillaCount(int pid){
		Session session = HibernateUtil.getSessionFactory().openSession();
		int bugzillaCount=0;

		try {


			String sqlQuery = "SELECT b.bug_count" +
					" FROM (SELECT bug_count, MAX(modifiedDate)'maxDate' FROM bug_master WHERE projectid="+pid+") a, bug_master b" +
					" WHERE b.modifiedDate =a.maxDate AND projectid="+pid+"";
			//System.out.println(sqlQuery);
			Query query = session.createSQLQuery(sqlQuery)
					.addScalar("bug_count", StandardBasicTypes.INTEGER);

			bugzillaCount = (Integer) query.uniqueResult();

		} catch (Exception e) {
			// TODO: handle exception

		}
		finally{

			session.close();
		}
		System.out.println("Bugzilla Count " + bugzillaCount);
		return bugzillaCount;


	}


	//getting total count from redmind_master for particular project  
	public int getRedMindCount(int pid){

		Session session = HibernateUtil.getSessionFactory().openSession();
		int redmindCount=0;

		try {


			String sqlQuery = "SELECT b.bug_count" +
					" FROM (SELECT bug_count, MAX(modifiedDate)'maxDate' FROM redmind_master WHERE projectid="+pid+") a, redmind_master b" +
					" WHERE b.modifiedDate =a.maxDate AND projectid="+pid+"";

			Query query = session.createSQLQuery(sqlQuery)
					.addScalar("bug_count", StandardBasicTypes.INTEGER);


			redmindCount = (Integer) query.uniqueResult();


		} catch (Exception e) {
			// TODO: handle exception

		}
		finally{

			session.close();
		}
		System.out.println("Redmind Count " + redmindCount);

		return redmindCount;

	}

	//getting count for all project

	//getting array values for all project from bugzilla_master
	public int[] getBugZillaArrayForAll(int arrayLength, String st){

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query=null;
		List result=null;
		int[]bugzillaArray = null;
		try{
			if(st.trim().equals("all")){

				System.out.println("Getting bugzilla data sort by: " + st);
				String sqlQuery="SELECT a.projectid,  a.bug_Count FROM bug_master a , (SELECT  projectid, MAX(modifiedDate) 'modifiedDate' FROM bug_master GROUP BY projectid) b" +
						" WHERE a.projectid = b.projectid  AND a.modifiedDate = b.modifiedDate GROUP BY  projectid";

				query=session.createSQLQuery(sqlQuery)
						.addScalar("projectid", StandardBasicTypes.INTEGER)
						.addScalar("bug_count", StandardBasicTypes.INTEGER);

				result=query.list();
				bugzillaArray=new int[arrayLength];
				Iterator resultIterator = result.iterator();

				int j=0;
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//System.out.print(obj[i] + "\t");

						bugzillaArray[j]=(Integer) obj[1];


					}
					j++;
					//System.out.println("");

				}

			}else if(st.trim().equals("monthly")){

				System.out.println("Getting bugzilla data sort by: " + st);

				String sqlQuery="SELECT MONTH(modifiedDate)'Month',SUM(bug_count)'Count'" +
						" FROM" +
						" (" +
						" SELECT projectid, MAX(modifiedDate) 'Max_Date' FROM bug_master " +
						" WHERE modifiedDate BETWEEN '"+customDate.getYearStartTime()+"'  AND  '"+customDate.getYearEndTime()+"'" +
						" AND projectid IN (SELECT id FROM projectmaster WHERE isActive='yes') GROUP BY  projectid, DATE(modifiedDate)" +
						" ) a," +
						" bug_master b" +
						" WHERE a.projectid = b.projectid AND b.modifiedDate = a.Max_Date" +
						" GROUP BY MONTH(modifiedDate)";

				query=session.createSQLQuery(sqlQuery)
						.addScalar("Month", StandardBasicTypes.INTEGER)
						.addScalar("Count", StandardBasicTypes.INTEGER);
				result=query.list();


				bugzillaArray=new int[]{0,0, 0,0, 0,0, 0,0, 0,0, 0,0};
				Iterator resultIterator = result.iterator();
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//System.out.print(obj[i] + "\t");

						int j=(Integer)obj[0];
						bugzillaArray[--j]=(Integer) obj[i];


					}
					//System.out.println("");

				}

			}else if(st.trim().equals("week")){

				System.out.println("Getting bugzilla data sort by: " + st);

				String sqlQuery="SELECT WEEK(modifiedDate)'Week',SUM(bug_count)'Count'" +
						" FROM" +
						" (" +
						" SELECT projectid, MAX(modifiedDate) 'Max_Date' FROM bug_master " +
						" WHERE modifiedDate BETWEEN '"+customDate.getMonthStartTime()+"'  AND  '"+customDate.getMonthEndTime()+"'" +
						" AND projectid IN (SELECT id FROM projectmaster WHERE isActive='yes')" +
						" GROUP BY projectid, DATE(modifiedDate)" +
						" ) a, bug_master b" +
						" WHERE a.projectid = b.projectid AND b.modifiedDate = a.Max_Date" +
						" GROUP BY WEEK(modifiedDate)";

				//System.out.println(sqlQuery);
				query=session.createSQLQuery(sqlQuery)
						.addScalar("Week", StandardBasicTypes.INTEGER)
						.addScalar("Count", StandardBasicTypes.INTEGER);

				result=query.list();
				bugzillaArray=new int[]{0,0, 0,0, 0};

				Iterator resultIterator = result.iterator();
				int j=0;
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//System.out.print(obj[i] + "\t");

						bugzillaArray[j]=(Integer) obj[1];
					}
					//System.out.println("");				
					j++;
				}
				//for(int i: bugzillaArray){System.out.println(i+"\t");}



			}else if(st.trim().equals("weekly")){



				System.out.println("Getting bugzilla data sort by: " + st);

				String sqlQuery="SELECT WEEKDAY(modifiedDate)'Day',SUM(bug_count)'Count' " +
						" FROM" +
						" (" +
						" SELECT projectid, MAX(modifiedDate) 'Max_Date' FROM bug_master " +
						" WHERE modifiedDate BETWEEN '"+customDate.getStartWeek()+"'  AND  '"+customDate.getEndWeek()+"'" +
						" AND projectid IN (SELECT id FROM projectmaster WHERE isActive='yes')" +
						" GROUP BY projectid, DATE(modifiedDate)" +
						" ) a, bug_master b" +
						" WHERE a.projectid = b.projectid AND b.modifiedDate = a.Max_Date" +
						" GROUP BY WEEKDAY(modifiedDate)";

				//System.out.println(sqlQuery);
				query=session.createSQLQuery(sqlQuery)
						.addScalar("Day", StandardBasicTypes.INTEGER)
						.addScalar("Count", StandardBasicTypes.INTEGER);

				result=query.list();
				bugzillaArray=new int[]{0,0, 0,0, 0,0, 0};

				Iterator resultIterator = result.iterator();
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//System.out.print("OBJECT  "+obj[i] + "\t");

						int j=(Integer)obj[0];
						bugzillaArray[j]=(Integer) obj[i];

					}
					//System.out.println("");

				}


			}
			else if(st.trim().equals("daily")){
				System.out.println("Getting bugzilla data sort by: " + st);

				String sqlQuery="SELECT " +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (0,1) THEN a.bug_count ELSE 0 END ) '0-1'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (2,3) THEN a.bug_count ELSE 0 END ) '2-3'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (4,5) THEN a.bug_count ELSE 0 END ) '4-5'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (6,7) THEN a.bug_count ELSE 0 END ) '6-7'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (8,9) THEN a.bug_count ELSE 0 END ) '8-9'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (10,11) THEN a.bug_count ELSE 0 END ) '10-11'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (12,13) THEN a.bug_count ELSE 0 END ) '12-13'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (14,15) THEN a.bug_count ELSE 0 END ) '14-15'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (16,17) THEN a.bug_count ELSE 0 END ) '16-17'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (18,19) THEN a.bug_count ELSE 0 END ) '18-19'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (20,21) THEN a.bug_count ELSE 0 END ) '20-21'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (22,23) THEN a.bug_count ELSE 0 END ) '22-23'" +
						" FROM" +
						" (SELECT  projectid, HOUR(modifiedDate), MAX(modifiedDate) 'modifiedDate', bug_count " +
						" FROM bug_master " +
						" WHERE modifiedDate BETWEEN '"+customDate.getTodayStartTime()+"' AND '"+customDate.getTodayEndTime()+"' " +
						" GROUP BY HOUR(modifiedDate), projectid) a";

				query=session.createSQLQuery(sqlQuery)
						.addScalar("0-1", StandardBasicTypes.INTEGER)
						.addScalar("2-3", StandardBasicTypes.INTEGER)
						.addScalar("4-5", StandardBasicTypes.INTEGER)
						.addScalar("6-7", StandardBasicTypes.INTEGER)
						.addScalar("8-9", StandardBasicTypes.INTEGER)
						.addScalar("10-11", StandardBasicTypes.INTEGER)
						.addScalar("12-13", StandardBasicTypes.INTEGER)
						.addScalar("14-15", StandardBasicTypes.INTEGER)
						.addScalar("16-17", StandardBasicTypes.INTEGER)
						.addScalar("18-19", StandardBasicTypes.INTEGER)
						.addScalar("20-21", StandardBasicTypes.INTEGER)
						.addScalar("22-23", StandardBasicTypes.INTEGER);

				result=query.list();

				bugzillaArray=new int[]{0,0, 0,0, 0,0, 0,0, 0,0, 0,0};

				Iterator resultIterator = result.iterator();
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//System.out.print(i+" "+obj[i] + "\t");

						if(obj[i]==null){
							bugzillaArray[i]=0;
						}else{
							bugzillaArray[i]=(Integer) obj[i];
						}

					}
					//System.out.println("");

				}

				//for(int i:bugzillaArray)System.out.println(i);


			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();
		}

		return bugzillaArray;
	}


	//getting array values for all project from redmind_master
	public int[] getRedMindArrayForAll(int arrayLength, String st){

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query=null;
		List result=null;
		int[]redmindArray = null;

		try{
			if(st.trim().equals("all")){

				System.out.println("Getting bugzilla data sort by: " + st);

				String sqlQuery="SELECT a.projectid,  a.bug_Count FROM redmind_master a , (SELECT  projectid, MAX(modifiedDate) 'modifiedDate' FROM redmind_master GROUP BY projectid) b" +
						" WHERE a.projectid = b.projectid  AND a.modifiedDate = b.modifiedDate GROUP BY  projectid";

				query=session.createSQLQuery(sqlQuery)
						.addScalar("projectid", StandardBasicTypes.INTEGER)
						.addScalar("bug_count", StandardBasicTypes.INTEGER);

				result=query.list();
				redmindArray=new int[arrayLength];
				int j=0;
				Iterator resultIterator = result.iterator();
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();

					for (int i = 0; i < obj.length; i++) {
						//	System.out.print(obj[i] + "\t");

						redmindArray[j]=(Integer) obj[1];

					}
					j++;
					//System.out.println("");

				}

			}else if(st.trim().equals("monthly")){

				System.out.println("Getting bugzilla data sort by: " + st);

				String sqlQuery="SELECT MONTH(modifiedDate)'Month',SUM(bug_count)'Count'" +
						" FROM" +
						" (" +
						" SELECT projectid, MAX(modifiedDate) 'Max_Date' FROM redmind_master " +
						" WHERE modifiedDate BETWEEN '"+customDate.getYearStartTime()+"'  AND  '"+customDate.getYearEndTime()+"'" +
						" AND projectid IN (SELECT id FROM projectmaster WHERE isActive='yes') GROUP BY  projectid,  DATE(modifiedDate)" +
						" ) a," +
						" redmind_master b" +
						" WHERE a.projectid = b.projectid AND b.modifiedDate = a.Max_Date" +
						" GROUP BY MONTH(modifiedDate)";
				
				query=session.createSQLQuery(sqlQuery)
						.addScalar("Month", StandardBasicTypes.INTEGER)
						.addScalar("Count", StandardBasicTypes.INTEGER);
				result=query.list();


				redmindArray=new int[]{0,0, 0,0, 0,0, 0,0, 0,0, 0,0};
				Iterator resultIterator = result.iterator();
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//	System.out.print(obj[i] + "\t");

						int j=(Integer)obj[0];
						redmindArray[--j]=(Integer) obj[i];


					}
					//	System.out.println("");

				}

			}else if(st.trim().equals("week")){

				System.out.println("Getting bugzilla data sort by: " + st);

				String sqlQuery="SELECT WEEK(modifiedDate)'Week',SUM(bug_count)'Count'" +
						" FROM" +
						" (" +
						" SELECT projectid, MAX(modifiedDate) 'Max_Date' FROM redmind_master " +
						" WHERE modifiedDate BETWEEN '"+customDate.getMonthStartTime()+"'  AND  '"+customDate.getMonthEndTime()+"'" +
						" AND projectid IN (SELECT id FROM projectmaster WHERE isActive='yes')" +
						" GROUP BY projectid, DATE(modifiedDate)" +
						" ) a, redmind_master b" +
						" WHERE a.projectid = b.projectid AND b.modifiedDate = a.Max_Date" +
						" GROUP BY WEEK(modifiedDate)";
				
				//System.out.println(sqlQuery);
				query=session.createSQLQuery(sqlQuery)
						.addScalar("Week", StandardBasicTypes.INTEGER)
						.addScalar("Count", StandardBasicTypes.INTEGER);

				result=query.list();
				redmindArray=new int[]{0,0, 0,0, 0};

				Iterator resultIterator = result.iterator();
				int j=0;
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//System.out.print(obj[i] + "\t");


						redmindArray[j]=(Integer) obj[1];

					}
					j++;
					//System.out.println("");				

				}
				//for(int i: bugzillaArray){System.out.println(i+"\t");}



			}else if(st.trim().equals("weekly")){



				System.out.println("Getting bugzilla data sort by: " + st);

				String sqlQuery="SELECT WEEKDAY(modifiedDate)'Day',SUM(bug_count)'Count' " +
						" FROM" +
						" (" +
						" SELECT projectid, MAX(modifiedDate) 'Max_Date' FROM redmind_master " +
						" WHERE modifiedDate BETWEEN '"+customDate.getStartWeek()+"'  AND  '"+customDate.getEndWeek()+"'" +
						" AND projectid IN (SELECT id FROM projectmaster WHERE isActive='yes')" +
						" GROUP BY projectid, DATE(modifiedDate)" +
						" ) a, redmind_master b" +
						" WHERE a.projectid = b.projectid AND b.modifiedDate = a.Max_Date" +
						" GROUP BY WEEKDAY(modifiedDate)";

				//System.out.println(sqlQuery);
				query=session.createSQLQuery(sqlQuery)
						.addScalar("Day", StandardBasicTypes.INTEGER)
						.addScalar("Count", StandardBasicTypes.INTEGER);

				result=query.list();
				redmindArray=new int[]{0,0, 0,0, 0,0, 0};

				Iterator resultIterator = result.iterator();
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//	System.out.print("OBJECT  "+obj[i] + "\t");

						int j=(Integer)obj[0];
						redmindArray[j]=(Integer) obj[i];

					}
					//	System.out.println("");

				}


			}
			else if(st.trim().equals("daily")){
				System.out.println("Getting redmine data sort by: " + st);

				String sqlQuery="SELECT " +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (0,1) THEN a.bug_count ELSE 0 END ) '0-1'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (2,3) THEN a.bug_count ELSE 0 END ) '2-3'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (4,5) THEN a.bug_count ELSE 0 END ) '4-5'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (6,7) THEN a.bug_count ELSE 0 END ) '6-7'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (8,9) THEN a.bug_count ELSE 0 END ) '8-9'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (10,11) THEN a.bug_count ELSE 0 END ) '10-11'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (12,13) THEN a.bug_count ELSE 0 END ) '12-13'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (14,15) THEN a.bug_count ELSE 0 END ) '14-15'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (16,17) THEN a.bug_count ELSE 0 END ) '16-17'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (18,19) THEN a.bug_count ELSE 0 END ) '18-19'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (20,21) THEN a.bug_count ELSE 0 END ) '20-21'," +
						" SUM(CASE WHEN HOUR(a.modifiedDate) IN (22,23) THEN a.bug_count ELSE 0 END ) '22-23'" +
						" FROM" +
						" (SELECT  projectid, HOUR(modifiedDate), MAX(modifiedDate) 'modifiedDate', bug_count " +
						" FROM redmind_master " +
						" WHERE modifiedDate BETWEEN '"+customDate.getTodayStartTime()+"' AND '"+customDate.getTodayEndTime()+"' " +
						" GROUP BY HOUR(modifiedDate), projectid) a";

				query=session.createSQLQuery(sqlQuery)
						.addScalar("0-1", StandardBasicTypes.INTEGER)
						.addScalar("2-3", StandardBasicTypes.INTEGER)
						.addScalar("4-5", StandardBasicTypes.INTEGER)
						.addScalar("6-7", StandardBasicTypes.INTEGER)
						.addScalar("8-9", StandardBasicTypes.INTEGER)
						.addScalar("10-11", StandardBasicTypes.INTEGER)
						.addScalar("12-13", StandardBasicTypes.INTEGER)
						.addScalar("14-15", StandardBasicTypes.INTEGER)
						.addScalar("16-17", StandardBasicTypes.INTEGER)
						.addScalar("18-19", StandardBasicTypes.INTEGER)
						.addScalar("20-21", StandardBasicTypes.INTEGER)
						.addScalar("22-23", StandardBasicTypes.INTEGER);

				result=query.list();

				redmindArray=new int[]{0,0, 0,0, 0,0, 0,0, 0,0, 0,0};

				Iterator resultIterator = result.iterator();
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//	System.out.print(i+" "+obj[i] + "\t");
						if(obj[i]==null){
							redmindArray[i]=0;
						}else{
							redmindArray[i]=(Integer) obj[i];
						}


					}
					//System.out.println("");

				}

				//for(int i:redmindArray)System.out.println(i);


			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();
		}
		return redmindArray;
	}


	//getting count for particular project

	//getting array values for particular project from bugzilla_master
	public int[] getBugZillaArray(int pid, String st){


		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query=null;
		List result=null;
		int[]bugZillaArray = null;

		try{
			if(st.trim().equals("all")||st.trim().equals("monthly") ){

				System.out.println("Getting bugzilla data PID: " + pid + " sort by: " + st);

				String sqlQuery="SELECT MONTH(modifiedDate)'Month',SUM(bug_count) 'Count'" +
						" FROM" +
						" (" +
						" SELECT projectid, MAX(modifiedDate) 'Max_Date'" +
						" FROM bug_master WHERE modifiedDate BETWEEN '"+customDate.getYearStartTime()+"'  AND  '"+customDate.getYearEndTime()+"'" +
						" AND projectid ="+pid+" GROUP BY DATE(modifiedDate)" +
						" )a," +
						" bug_master b" +
						" WHERE a.projectid = b.projectid AND b.modifiedDate = a.Max_Date" +
						" GROUP BY MONTH(modifiedDate)";


				//System.out.println(sqlQuery);

				query=session.createSQLQuery(sqlQuery)
						.addScalar("Month", StandardBasicTypes.INTEGER)
						.addScalar("Count", StandardBasicTypes.INTEGER);

				result=query.list();


				bugZillaArray=new int[]{0,0, 0,0, 0,0, 0,0, 0,0, 0,0};
				Iterator resultIterator = result.iterator();
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//	System.out.print(obj[i] + "\t");

						int j=(Integer)obj[0];
						bugZillaArray[--j]=(Integer) obj[i];


					}
					//	System.out.println("");

				}

			}else if(st.trim().equals("week")){

				System.out.println("Getting bugzilla data sort by: " + st);

				String sqlQuery="SELECT WEEK(modifiedDate)'Week',SUM(bug_count) 'Count'" +
						" FROM" +
						" (" +
						" SELECT projectid, MAX(modifiedDate) 'Max_Date' FROM bug_master " +
						" WHERE modifiedDate BETWEEN '"+customDate.getMonthStartTime()+"'  AND  '"+customDate.getMonthEndTime()+"'" +
						" AND projectid = "+pid+" GROUP BY DATE(modifiedDate)" +
						" ) a, " +
						" bug_master b" +
						" WHERE a.projectid = b.projectid AND b.modifiedDate = a.Max_Date" +
						" GROUP BY WEEK(modifiedDate)";

				//System.out.println(sqlQuery);
				query=session.createSQLQuery(sqlQuery)
						.addScalar("Week", StandardBasicTypes.INTEGER)
						.addScalar("Count", StandardBasicTypes.INTEGER);

				result=query.list();
				bugZillaArray=new int[]{0,0, 0,0, 0};

				Iterator resultIterator = result.iterator();

				int j=0;


				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//System.out.print(obj[i] + "\t");



						if(obj[i]!=null){
							bugZillaArray[j]=(Integer) obj[1];

						}else{
							bugZillaArray[i]=0;
						}

					}
					j++;
					//System.out.println("");				

				}

				//for(int i: bugZillaArray){System.out.println(i+"\t");}



			}else if(st.trim().equals("weekly")){



				System.out.println("Getting bugzilla data PID: " + pid + " sort by: " + st);

				String sqlQuery=" SELECT WEEKDAY(a.modifiedDate)'weekday' , a.bug_Count 'count'" +
						" FROM bug_master a , (SELECT MAX(modifiedDate) 'modifiedDate' FROM bug_master " +
						" WHERE modifiedDate BETWEEN '"+customDate.getStartWeek()+"'  AND  '"+customDate.getEndWeek()+"' " +
						" AND projectid="+pid +
						" GROUP BY WEEKDAY(modifiedDate) ) b " +
						" WHERE a.modifiedDate = b.modifiedDate AND a.projectid="+pid +
						" GROUP BY  WEEKDAY(a.modifiedDate)";

				//System.out.println(sqlQuery);
				query=session.createSQLQuery(sqlQuery)
						.addScalar("weekday", StandardBasicTypes.INTEGER)
						.addScalar("count", StandardBasicTypes.INTEGER);

				result=query.list();
				bugZillaArray=new int[]{0,0, 0,0, 0,0, 0};

				Iterator resultIterator = result.iterator();
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//		System.out.print("OBJECT  "+obj[i] + "\t");

						int j=(Integer)obj[0];
						bugZillaArray[j]=(Integer) obj[i];

					}
					//	System.out.println("");

				}


			}
			else if(st.trim().equals("daily")){

				System.out.println("Getting bugzilla data PID: " + pid + " sort by: " + st);

				String sqlQuery="SELECT " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM bug_master WHERE HOUR(modifiedDate) BETWEEN 0 AND 1 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '0-1', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM bug_master WHERE HOUR(modifiedDate) BETWEEN 2 AND 3 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '2-3'," +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM bug_master WHERE HOUR(modifiedDate) BETWEEN 4 AND 5 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '4-5', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM bug_master WHERE HOUR(modifiedDate) BETWEEN 6 AND 7 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '6-7', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM bug_master WHERE HOUR(modifiedDate) BETWEEN 8 AND 9 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '8-9', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM bug_master WHERE HOUR(modifiedDate) BETWEEN 10 AND 11 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '10-11', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM bug_master WHERE HOUR(modifiedDate) BETWEEN 12 AND 13 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '12-13', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM bug_master WHERE HOUR(modifiedDate) BETWEEN 14 AND 15 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '14-15', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM bug_master WHERE HOUR(modifiedDate) BETWEEN 16 AND 17 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '16-17',  " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM bug_master WHERE HOUR(modifiedDate) BETWEEN 18 AND 19 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count  ELSE 0 END) '18-19', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM bug_master WHERE HOUR(modifiedDate) BETWEEN 20 AND 21 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '20-21'," +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM bug_master WHERE HOUR(modifiedDate) BETWEEN 22 AND 23 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '22-23'" +
						" FROM bug_master" +
						" WHERE  projectid=" + pid + " AND modifiedDate BETWEEN '"+customDate.getTodayStartTime()  + "' AND '"+  customDate.getTodayEndTime()  + "'";

				//System.out.println(sqlQuery);
				query=session.createSQLQuery(sqlQuery)
						.addScalar("0-1", StandardBasicTypes.INTEGER)
						.addScalar("2-3", StandardBasicTypes.INTEGER)
						.addScalar("4-5", StandardBasicTypes.INTEGER)
						.addScalar("6-7", StandardBasicTypes.INTEGER)
						.addScalar("8-9", StandardBasicTypes.INTEGER)
						.addScalar("10-11", StandardBasicTypes.INTEGER)
						.addScalar("12-13", StandardBasicTypes.INTEGER)
						.addScalar("14-15", StandardBasicTypes.INTEGER)
						.addScalar("16-17", StandardBasicTypes.INTEGER)
						.addScalar("18-19", StandardBasicTypes.INTEGER)
						.addScalar("20-21", StandardBasicTypes.INTEGER)
						.addScalar("22-23", StandardBasicTypes.INTEGER);

				result=query.list();

				bugZillaArray=new int[]{0,0, 0,0, 0,0, 0,0, 0,0, 0,0};

				Iterator resultIterator = result.iterator();

				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//System.out.print(i+" "+obj[i] + "\t");

						if(obj[i]!=null){
							bugZillaArray[i]=(Integer) obj[i];

						}else{
							bugZillaArray[i]=0;
						}
						//System.out.print(i+" "+bugZillaArray[i] + "\t");
					}
					//System.out.println(" ");

				}


			}
			//for(int i:bugZillaArray)System.out.println(i);
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();
		}
		return bugZillaArray;


	}


	//getting array values for particular project from redmind_master
	public int[] getRedMindArray(int pid, String st){

		Session session = HibernateUtil.getSessionFactory().openSession();

		Query query=null;
		List result=null;
		int []redMindArray =null;

		try{
			if(st.trim().equals("all")||st.trim().equals("monthly")){

				System.out.println("Getting bugzilla data PID: " + pid + " sort by: " + st);

				String sqlQuery="SELECT MONTH(modifiedDate)'Month',SUM(bug_count) 'Count'" +
						" FROM" +
						" (" +
						" SELECT projectid, MAX(modifiedDate) 'Max_Date'" +
						" FROM redmind_master WHERE modifiedDate BETWEEN '"+customDate.getYearStartTime()+"'  AND  '"+customDate.getYearEndTime()+"'" +
						" AND projectid ="+pid+" GROUP BY DATE(modifiedDate)" +
						" )a," +
						" redmind_master b" +
						" WHERE a.projectid = b.projectid AND b.modifiedDate = a.Max_Date" +
						" GROUP BY MONTH(modifiedDate)";

				query=session.createSQLQuery(sqlQuery)
						.addScalar("Month", StandardBasicTypes.INTEGER)
						.addScalar("Count", StandardBasicTypes.INTEGER);

				result=query.list();


				redMindArray=new int[]{0,0, 0,0, 0,0, 0,0, 0,0, 0,0};
				Iterator resultIterator = result.iterator();
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//System.out.print(obj[i] + "\t");

						int j=(Integer)obj[0];
						redMindArray[--j]=(Integer) obj[i];


					}
					//System.out.println("");

				}

			}else if(st.trim().equals("week")){

				System.out.println("Getting bugzilla data sort by: " + st);

				String sqlQuery="SELECT WEEK(modifiedDate)'Week',SUM(bug_count) 'Count'" +
						" FROM" +
						" (" +
						" SELECT projectid, MAX(modifiedDate) 'Max_Date' FROM redmind_master " +
						" WHERE modifiedDate BETWEEN '"+customDate.getMonthStartTime()+"'  AND  '"+customDate.getMonthEndTime()+"'" +
						" AND projectid = "+pid+" GROUP BY DATE(modifiedDate)" +
						" ) a, " +
						" redmind_master b" +
						" WHERE a.projectid = b.projectid AND b.modifiedDate = a.Max_Date" +
						" GROUP BY WEEK(modifiedDate)";

				//System.out.println(sqlQuery);
				query=session.createSQLQuery(sqlQuery)
						.addScalar("Week", StandardBasicTypes.INTEGER)
						.addScalar("Count", StandardBasicTypes.INTEGER);

				result=query.list();
				redMindArray=new int[]{0,0, 0,0, 0};

				Iterator resultIterator = result.iterator();
				int j=0;
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//System.out.print(obj[i] + "\t");


						redMindArray[j]=(Integer) obj[1];

					}
					j++;
					//System.out.println("");				

				}
				//for(int i: bugzillaArray){System.out.println(i+"\t");}



			}else if(st.trim().equals("weekly")){



				System.out.println("Getting bugzilla data PID: " + pid + " sort by: " + st);

				String sqlQuery=" SELECT WEEKDAY(a.modifiedDate)'weekday' , a.bug_Count 'count'" +
						" FROM redmind_master a , (SELECT MAX(modifiedDate) 'modifiedDate' FROM redmind_master " +
						" WHERE modifiedDate BETWEEN '"+customDate.getStartWeek()+"'  AND  '"+customDate.getEndWeek()+"' " +
						" AND projectid="+pid +
						" GROUP BY WEEKDAY(modifiedDate) ) b " +
						" WHERE a.modifiedDate = b.modifiedDate AND a.projectid="+pid +
						" GROUP BY  WEEKDAY(a.modifiedDate)";

				//System.out.println(sqlQuery);
				query=session.createSQLQuery(sqlQuery)
						.addScalar("weekday", StandardBasicTypes.INTEGER)
						.addScalar("count", StandardBasicTypes.INTEGER);


				result=query.list();
				redMindArray=new int[]{0,0, 0,0, 0,0, 0};

				Iterator resultIterator = result.iterator();
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {
						//		System.out.print("OBJECT  "+obj[i] + "\t");

						int j=(Integer)obj[0];
						redMindArray[j]=(Integer) obj[i];

					}
					//	System.out.println("");

				}


			}
			else if(st.trim().equals("daily")){

				System.out.println("Getting redmine data PID: " + pid + " sort by: " + st);

				String sqlQuery="SELECT " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM redmind_master WHERE HOUR(modifiedDate) BETWEEN 0 AND 1 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '0-1', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM redmind_master WHERE HOUR(modifiedDate) BETWEEN 2 AND 3 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '2-3'," +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM redmind_master WHERE HOUR(modifiedDate) BETWEEN 4 AND 5 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '4-5', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM redmind_master WHERE HOUR(modifiedDate) BETWEEN 6 AND 7 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '6-7', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM redmind_master WHERE HOUR(modifiedDate) BETWEEN 8 AND 9 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '8-9', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM redmind_master WHERE HOUR(modifiedDate) BETWEEN 10 AND 11 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '10-11', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM redmind_master WHERE HOUR(modifiedDate) BETWEEN 12 AND 13 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '12-13', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM redmind_master WHERE HOUR(modifiedDate) BETWEEN 14 AND 15 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '14-15', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM redmind_master WHERE HOUR(modifiedDate) BETWEEN 16 AND 17 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '16-17',  " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM redmind_master WHERE HOUR(modifiedDate) BETWEEN 18 AND 19 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count  ELSE 0 END) '18-19', " +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM redmind_master WHERE HOUR(modifiedDate) BETWEEN 20 AND 21 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '20-21'," +
						"SUM(CASE WHEN  modifiedDate = (SELECT MAX(modifiedDate) FROM redmind_master WHERE HOUR(modifiedDate) BETWEEN 22 AND 23 AND projectid=" + pid + " AND modifiedDate BETWEEN '" +customDate.getTodayStartTime()  + "' AND  '"+  customDate.getTodayEndTime()  + "') THEN bug_Count ELSE 0 END) '22-23'" +
						" FROM redmind_master" +
						" WHERE  projectid=" + pid + " AND modifiedDate BETWEEN '"+customDate.getTodayStartTime()  + "' AND '"+  customDate.getTodayEndTime()  + "'";

				query=session.createSQLQuery(sqlQuery)
						.addScalar("0-1", StandardBasicTypes.INTEGER)
						.addScalar("2-3", StandardBasicTypes.INTEGER)
						.addScalar("4-5", StandardBasicTypes.INTEGER)
						.addScalar("6-7", StandardBasicTypes.INTEGER)
						.addScalar("8-9", StandardBasicTypes.INTEGER)
						.addScalar("10-11", StandardBasicTypes.INTEGER)
						.addScalar("12-13", StandardBasicTypes.INTEGER)
						.addScalar("14-15", StandardBasicTypes.INTEGER)
						.addScalar("16-17", StandardBasicTypes.INTEGER)
						.addScalar("18-19", StandardBasicTypes.INTEGER)
						.addScalar("20-21", StandardBasicTypes.INTEGER)
						.addScalar("22-23", StandardBasicTypes.INTEGER);

				result=query.list();

				redMindArray=new int[]{0,0, 0,0, 0,0, 0,0, 0,0, 0,0};

				Iterator resultIterator = result.iterator();
				while(resultIterator.hasNext())
				{
					Object[] obj = (Object[]) resultIterator.next();
					for (int i = 0; i < obj.length; i++) {

						//System.out.print(i+" "+obj[i] + "\t");

						if(obj[i]==null){
							redMindArray[i]=0;
						}else{
							redMindArray[i]=(Integer) obj[i];
						}

						//System.out.print(i+" "+redMindArray[i] + "\t");
					}
					//System.out.println("");

				}

				//for(int i:redMindArray)System.out.println(i);


			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			session.close();
		}

		return redMindArray;
	}





	/*	
	 * Employee Awards Manipulation
	 * 
	 */

	//adding award details to award_master

/*	public boolean addAwards(AwardForm awardForm){
		boolean outcome=false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("dao.Dao.addAwards");
		AwardMaster awardMaster=new AwardMaster(); 
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction(); 

			awardMaster.setEmpId(awardForm.getEmpId());
			awardMaster.setName(awardForm.getName());
			awardMaster.setImage(awardForm.getConvertedImage());
			awardMaster.setAwardMonth(customDate.getDateMonthYear(awardForm.getAwardMonth()));
			awardMaster.setIsActive(awardForm.getIsActive());
			awardMaster.setDesc(awardForm.getDesc());

			session.save(awardMaster);

			session.flush();
			session.clear();
			outcome=true;

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{
			transaction.commit();
			session.close();
		}
		System.out.println(outcome);
		return outcome;
	}


	//updating award details to award_master
	public boolean updateAwards(AwardForm awardForm){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		AwardMaster awardMaster=(AwardMaster)session.get(AwardMaster.class,awardForm.getId());

		System.out.println("dao.Dao.updateAwards()");


		Transaction transaction = null;
		try {
			transaction = session.beginTransaction(); 
			//setting form values to Award Master POJO
			awardMaster.setEmpId(awardForm.getEmpId());
			awardMaster.setName(awardForm.getName());
			awardMaster.setAwardMonth(customDate.getDateMonthYear(awardForm.getAwardMonth()));
			awardMaster.setIsActive(awardForm.getIsActive());
			awardMaster.setDesc(awardForm.getDesc());

			session.update(awardMaster);
			session.flush();
			session.clear();
			outcome=true;

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{
			transaction.commit();
			session.close();
		}

		System.out.println(outcome);
		return outcome;

	}*/


	//deleting award details to award_master
	public boolean deleteAwards(int id){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		Transaction transaction = null;
		System.out.println("dao.Dao.deleteAwards()");
		System.out.println(id);

		try {
			transaction=session.beginTransaction();

			Query query=session.createQuery("delete from AwardMaster where id = :id");	
			query.setParameter("id", id);

			session.flush();
			session.clear();
			int i=query.executeUpdate();

			if(i!=0)
				outcome=true;


		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{

			transaction.commit();
			session.close();
		}
		System.out.println(outcome);
		return outcome;

	}

	
	public boolean addArticle(ArticleMaster articleMaster) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction(); 
			session.save(articleMaster);
			session.flush();
			session.clear();
			outcome = true;
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{

			transaction.commit();
			session.close();
		}
		
		return outcome;

	}
	
	
	public boolean addArticleDoc(ArticleFormBean articleForm, String articleLocation) {
		
		ArticleMaster articleMaster = new ArticleMaster();
		
		Calendar cal = GregorianCalendar.getInstance();
		articleMaster.setModifiedDate(CommonConstants.SDF.format(cal.getTime()));
		articleMaster.setCreatedDate(CommonConstants.SDF.format(cal.getTime()));
		
		//populatePOJO(articleForm, articleMaster, articleLocation);
		articleMaster.setArticleLocation(articleLocation);
		populatePojo(articleForm, articleMaster);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction(); 
			session.save(articleMaster);
			session.flush();
			session.clear();
			outcome = true;
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{

			transaction.commit();
			session.close();
		}
		
		return outcome;
	}

	public boolean updateArticleDoc(ArticleFormBean articleForm) {
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List getArticle(ArticleFormBean articleForm){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		
		String sql="select am.articleDesc, am.articleTitle from ArticleMaster am where articleId="+articleForm.getArticleId();
		Query q=session.createQuery(sql);

		List list=q.list();

		/*		Integer i=new Integer(1);
		Object obj=list.get(0);

		String s1=String.valueOf(obj);
		String s2=String.valueOf(i);

		if(s1.equals(s2))
			outcome=true;*/
		session.close();

		return list;

	}
	
	/* Category Starts*/
	
	@SuppressWarnings("unchecked")
	public List getCategory(){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		
		String sql="select am.categoryId, am.categoryName from CategoryMaster am";
		Query q=session.createQuery(sql);

		List list=q.list();

		/*		Integer i=new Integer(1);
		Object obj=list.get(0);

		String s1=String.valueOf(obj);
		String s2=String.valueOf(i);

		if(s1.equals(s2))
			outcome=true;*/
		session.close();

		return list;

	}

	public boolean addCategory(CategoryFormBean categoryFormBean) {
		CategoryMaster categoryMaster = new CategoryMaster();
		
		Calendar cal = GregorianCalendar.getInstance();
		categoryMaster.setModifiedDate(CommonConstants.SDF.format(cal.getTime()));
		categoryMaster.setCreatedDate(CommonConstants.SDF.format(cal.getTime()));
		
		populatePOJO(categoryFormBean, categoryMaster);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction(); 
			session.save(categoryMaster);
			session.flush();
			session.clear();
			outcome = true;
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{

			transaction.commit();
			session.close();
		}
		
		return outcome;
	}
	
	
	//get particular user details
	public CategoryFormBean getCategoryDetail(CategoryFormBean categoryFormBean){

		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("In the dao.Dao.getCategoryDetail()");
		Transaction transaction = null;
		List<CategoryMaster> allResource=null; 


		try {
			transaction = session.beginTransaction(); 
			Query queryResult = session.createQuery("from CategoryMaster where categoryId =:categoryId"); 
			queryResult.setParameter("categoryId",categoryFormBean.getCategoryId());

			allResource =(List<CategoryMaster>)queryResult.list();  


			for(int i=0;i<allResource.size();i++){


				CategoryMaster categoryMaster= allResource.get(i);

				populateForm(categoryFormBean, categoryMaster);


			}
			session.flush();
			session.clear();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}

		System.out.println("Database contents delivered from dao.Dao.getCategoryDetail()");   




		return categoryFormBean;
	}
	
	

	//get all user list
	public List<CategoryDTO> retreiveCategoryList()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("dao.Dao.retreiveCategoryList()");
		Transaction transaction = null;
		List<CategoryDTO> categoryList=new ArrayList<CategoryDTO>();
		List<CategoryDTO> dtoList = new ArrayList<CategoryDTO>();
		try {
			transaction = session.beginTransaction(); 
			Query queryResult = session.createQuery("from CategoryMaster");  
			List<CategoryMaster> list =(List<CategoryMaster>)queryResult.list();  
			CategoryMaster category = null;
			CategoryDTO categoryDTO = null;
			
			for(int i=0;i<list.size();i++){
				category = list.get(i);
				categoryDTO =new CategoryDTO();
				populateCategoryDTO(category, categoryDTO);
				dtoList.add(categoryDTO);
			}
			session.flush();
			session.clear();

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}

		System.out.println("Database contents delivered from dao.dao.retreiveCategoryList()");   

		return dtoList;

	}
	
	//delete user
	public boolean deleteCategory(int categoryId){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		Transaction transaction=null;
		System.out.println("dao.Dao.deleteCategory()");
		System.out.println(categoryId);

		try {

			transaction=session.beginTransaction();
			Query query=session.createQuery("delete from CategoryMaster where categoryId = :categoryId");	
			query.setParameter("categoryId", categoryId);
			int i=query.executeUpdate();
			session.flush();
			session.clear();
			if(i!=0)
				outcome=true;


		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{
			transaction.commit();
			session.close();
		}
		return outcome;


	}
	
	//update user
	public boolean updateCategory(CategoryFormBean categoryFormBean){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		CategoryMaster categoryMaster=new CategoryMaster();
		System.out.println("dao.Dao.updateCategory()");
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction(); 
			
			Query queryResult = session.createQuery("from CategoryMaster where categoryId =:categoryId"); 
			queryResult.setParameter("categoryId",categoryFormBean.getCategoryId());

			List allResource =(List<CategoryMaster>)queryResult.list();  


			for(int i=0;i<allResource.size();i++){
				categoryMaster= (CategoryMaster) allResource.get(i);
			}
			
			
			populatePOJO(categoryFormBean, categoryMaster);
			categoryMaster.setCategoryId(Integer.parseInt(categoryFormBean.getCategoryId()));
			
			Calendar cal = GregorianCalendar.getInstance();
			categoryMaster.setModifiedDate(CommonConstants.SDF.format(cal.getTime()));
			
			
			
			session.update(categoryMaster);
			outcome=true;
			session.flush();
			session.clear();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		finally{

			transaction.commit();
			session.close();
		}
		return outcome;
	}
	
	
	/* Category Ends*/
	
	
	
	
	/* Article Starts*/
	
	public boolean addArticle(ArticleFormBean articleForm) {
		ArticleMaster articleMaster = new ArticleMaster();
		
		Calendar cal = GregorianCalendar.getInstance();
		articleMaster.setModifiedDate(CommonConstants.SDF.format(cal.getTime()));
		articleMaster.setCreatedDate(CommonConstants.SDF.format(cal.getTime()));
		
		//populatePOJO(articleFormBean, articleMaster, null);
		populatePojo(articleForm, articleMaster);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction(); 
			session.save(articleMaster);
			session.flush();
			session.clear();
			outcome = true;
		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{

			transaction.commit();
			session.close();
		}
		
		return outcome;
	}
	
	
	//get particular user details
	public ArticleFormBean getArticleDetail(ArticleFormBean articleFormBean){

		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("In the dao.Dao.getArticleDetail()");
		Transaction transaction = null;
		List<ArticleMaster> allResource=null; 


		try {
			transaction = session.beginTransaction(); 
			Query queryResult = session.createQuery("from ArticleMaster where articleId =:articleId"); 
			queryResult.setParameter("articleId",articleFormBean.getArticleId());

			allResource =(List<ArticleMaster>)queryResult.list();  


			for(int i=0;i<allResource.size();i++){


				ArticleMaster articleMaster= allResource.get(i);

				populateForm(articleFormBean, articleMaster);


			}
			session.flush();
			session.clear();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}

		System.out.println("Database contents delivered from dao.Dao.getArticleDetail()");   




		return articleFormBean;
	}
	
	



	//get all user list
	public List<ArticleDTO> retreiveArticleList()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("dao.Dao.retreiveArticleList()");
		Transaction transaction = null;
		List<ArticleDTO> articleList=new ArrayList<ArticleDTO>();
		List<ArticleDTO> dtoList = new ArrayList<ArticleDTO>();
		try {
			transaction = session.beginTransaction(); 
			Query queryResult = session.createQuery("from ArticleMaster");  
			List<ArticleMaster> list =(List<ArticleMaster>)queryResult.list();  
			ArticleMaster article = null;
			ArticleDTO articleDTO = null;
			
			for(int i=0;i<list.size();i++){
				article = list.get(i);
				articleDTO =new ArticleDTO();
				articleDTO.setArticleId(article.getArticleId());
				populateArticleDTO(article, articleDTO);
				dtoList.add(articleDTO);
			}
			session.flush();
			session.clear();

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}

		System.out.println("Database contents delivered from dao.dao.retreiveArticleList()");   

		return dtoList;

	}
	


	//delete user
	public boolean deleteArticle(int articleId){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		Transaction transaction=null;
		System.out.println("dao.Dao.deleteArticle()");
		System.out.println(articleId);

		try {

			transaction=session.beginTransaction();
			Query query=session.createQuery("delete from ArticleMaster where articleId = :articleId");	
			query.setParameter("articleId", articleId);
			int i=query.executeUpdate();
			session.flush();
			session.clear();
			if(i!=0)
				outcome=true;


		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();
		}
		finally{
			transaction.commit();
			session.close();
		}
		return outcome;


	}
	
	//update user
	public boolean updateArticle(ArticleFormBean articleFormBean){

		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean outcome=false;
		ArticleMaster articleMaster=new ArticleMaster();
		System.out.println("dao.Dao.updateArticle()");
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction(); 
			
			Query queryResult = session.createQuery("from ArticleMaster where articleId =:articleId"); 
			queryResult.setParameter("articleId",articleFormBean.getArticleId());

			List allResource =(List<ArticleMaster>)queryResult.list();  


			for(int i=0;i<allResource.size();i++){
				articleMaster= (ArticleMaster) allResource.get(i);
			}
			
			
			populatePojo(articleFormBean, articleMaster);
			articleMaster.setArticleId(articleFormBean.getArticleId());
			
			Calendar cal = GregorianCalendar.getInstance();
			articleMaster.setModifiedDate(CommonConstants.SDF.format(cal.getTime()));
			
			
			
			session.update(articleMaster);
			outcome=true;
			session.flush();
			session.clear();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			transaction.rollback();
		}
		finally{

			transaction.commit();
			session.close();
		}
		return outcome;
	}
	
	
	/* Article Ends*/
	
	
	
	





	private void populatePOJO(CategoryFormBean categoryFormBean,
			CategoryMaster categoryMaster) {
		
		categoryMaster.setCategoryDesc(categoryFormBean.getCategoryDesc());
		categoryMaster.setCategoryName(categoryFormBean.getCategoryName());
		categoryMaster.setIsActive(categoryFormBean.getIsActive());
		
	}
	
	private void populateCategoryDTO(CategoryMaster category,
			CategoryDTO categoryDTO) {

		categoryDTO.setCategoryDesc(category.getCategoryDesc());
		categoryDTO.setCategoryName(category.getCategoryName());
		categoryDTO.setIsActive(category.getIsActive());
		categoryDTO.setCategoryId(category.getCategoryId());
	}
	
	private void populateForm(CategoryFormBean categoryFormBean,
			CategoryMaster categoryMaster) {
		
		categoryFormBean.setCategoryDesc(categoryMaster.getCategoryDesc());
		categoryFormBean.setCategoryName(categoryMaster.getCategoryName());
		categoryFormBean.setIsActive(categoryMaster.getIsActive());
		categoryFormBean.setCategoryId(String.valueOf(categoryMaster.getCategoryId()));
		
	}
	
	private String replaceAndEscape(String articleDesc) {
		
		articleDesc = articleDesc.replaceAll("<!DOCTYPE html>", "");
		articleDesc = articleDesc.replaceAll("<html>", "");
		articleDesc = articleDesc.replaceAll("</html>", "");
		articleDesc = articleDesc.replaceAll("<head>", "");
		articleDesc = articleDesc.replaceAll("</head>", "");
		articleDesc = articleDesc.replaceAll("<body>", "");
		articleDesc = articleDesc.replaceAll("</body>", "");
		
		return org.apache.commons.lang3.StringEscapeUtils.escapeHtml4(articleDesc);

	}
	




	//getting list of all awards for awardlist.jsp 
/*	public  List<AwardsDTO> getAwards(AwardForm form){

		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("dao.Dao.getAwards()");
		Transaction transaction = null;
		List<AwardMaster> allAwards=null; 
		List<AwardsDTO> awardsDtoList=new ArrayList<AwardsDTO>();

		try {
			transaction = session.beginTransaction(); 
			Query queryResult = session.createQuery("from AwardMaster where isActive='yes'");  
			allAwards =(List<AwardMaster>)queryResult.list();  

			for(int i=0;i<allAwards.size();i++){

				AwardsDTO awardsDto=new AwardsDTO();
				AwardMaster awrd=allAwards.get(i);

				awardsDto.setId(awrd.getId());
				awardsDto.setEmpId(awrd.getEmpId());
				awardsDto.setName(awrd.getName());
				awardsDto.setIsActive(awrd.getIsActive());
				awardsDto.setImage(awrd.getImage());
				awardsDto.setAwardMonth(customDate.getMonthYear(awrd.getAwardMonth()));
				awardsDto.setDesc(awrd.getDesc());

				awardsDtoList.add(awardsDto);
			}
			session.flush();
			session.clear();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}

		if (awardsDtoList!=null)	 System.out.println("Database contents delivered from dao.dao.getAwards()");   
		return awardsDtoList;


	}


	//getting particular award details from award_master 
	public AwardForm getAwardDetail(AwardForm awardForm){
		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("In the dao.Dao.getAwardDetail()");
		Transaction transaction = null;
		List<AwardMaster> award=null; 


		try {
			transaction = session.beginTransaction(); 
			Query queryResult = session.createQuery("from AwardMaster where isActive='yes' and id =:id"); 
			queryResult.setParameter("id",awardForm.getId());
			award =(List<AwardMaster>)queryResult.list();  


			for(int i=0;i<award.size();i++){


				AwardMaster awardMaster=award.get(i);
				//awardForm.setId(awardMaster.getId());
				awardForm.setEmpId(awardMaster.getEmpId());
				awardForm.setName(awardMaster.getName());
				awardForm.setConvertedImage(awardMaster.getImage());
				awardForm.setAwardMonth(customDate.getMonthYear(awardMaster.getAwardMonth()));
				awardForm.setDesc(awardMaster.getDesc());
				awardForm.setIsActive(awardMaster.getIsActive());

			}
			session.flush();
			session.clear();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}

		if (awardForm!=null) System.out.println("Database contents delivered from dao.Dao.getAwardDetail()");   
		return awardForm;




	}

	//list for particular month

	//getting only winner name list from award_master for current month
	public  List<AwardsDTO> getWinnerListOfMonth(AwardForm form){

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		List<AwardMaster> winners=null;
		List<AwardsDTO> winnerList=new ArrayList<AwardsDTO>();

		System.out.println("In the dao.Dao.getWinnerListOfMonth()");

		try {
			transaction = session.beginTransaction(); 
			Query queryResult = session.createQuery("FROM AwardMaster WHERE MONTH(awardMonth) = MONTH(CURRENT_DATE) AND isActive='yes'");  

			winners =(List<AwardMaster>) queryResult.list();  

			for(int i=0;i<winners.size();i++){

				AwardsDTO awrdDTO=new AwardsDTO();
				AwardMaster awrd=winners.get(i);	
				awrdDTO.setId(awrd.getId());
				awrdDTO.setName(awrd.getName());
				winnerList.add(awrdDTO);	
			}
			session.flush();
			session.clear();

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			transaction.rollback();
		}finally{

			transaction.commit();
			session.close();
		}

		if (winnerList!=null)	System.out.println("Database contents delivered from dao.Dao.getWinnerListOfMonth");   
		return winnerList;


	}


	//getting all details of winner from award_master for current month
	public List<AwardsDTO> getWinnerDetailOfMonth(AwardForm form){

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query=null;
		Transaction transaction = null;

		List<AwardMaster> winnerDetails=null;
		List<AwardsDTO> winnerList=new ArrayList<AwardsDTO>();
		System.out.println("In the dao.Dao.getWinnerDetailOfMonth()");

		try {

			query=session.createQuery("FROM AwardMaster WHERE MONTH(awardMonth) = MONTH(CURRENT_DATE()) AND isActive='yes'");

			transaction = session.beginTransaction(); 
			Query queryResult = session.createQuery("from Projectmaster");  

			winnerDetails =(List<AwardMaster>) queryResult.list();  

			for(int i=0;i<winnerDetails.size();i++){

				AwardsDTO awrdDTO=new AwardsDTO();
				AwardMaster awrd=winnerDetails.get(i);	

				awrdDTO.setId(awrd.getId());
				awrdDTO.setEmpId(awrd.getEmpId());
				awrdDTO.setName(awrd.getName());
				awrdDTO.setImage(awrd.getImage());
				awrdDTO.setDesc(awrd.getDesc());

				winnerList.add(awrdDTO);	

			}
			session.flush();
			session.clear();

		} catch (Exception e) {
			// TODO: handle exception
			transaction.rollback();

		}
		finally{
			transaction.commit();
			session.close();
		}

		if (winnerList!=null)	System.out.println("Database contents delivered from dao.Dao.getWinnerDetailOfMonth");  
		return winnerList;

	}
*/

	//Testing purpose

	/*public static void main(String []args){
		Dao dao=new Dao();
		dao.getBugZillaArray(5, "week");
		//dao.getRedMindArray(1, "daily");

		//dao.getBugZillaArrayForAll(5, "week");

		//dao.getBugZillaCount(1);
		//dao.getRedMindCount(1);
	}*/

}


