package com.synechron.prm.controller;



import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.synechron.prm.bean.ArticleMaster;
import com.synechron.prm.dao.ArticleDTO;
import com.synechron.prm.form.ArticleFormBean;
import com.synechron.prm.util.CommonConstants;
import com.synechron.prm.util.Helper;



public class AddArticleAction extends DispatchAction {
	
	Helper helper=new Helper();

	//Add & View Awards
	public ActionForward addFile(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Helper helper=new Helper();
		ArticleFormBean articleForm=(ArticleFormBean) form;
		String outcome = "fail";
		//convert image & set to bean
		System.out.println("in AwardAction.add()");

		if(articleForm.getArticleDoc()!=null){

			if(articleForm.getArticleDoc().getFileSize() == 0 ){
				// its a corrupted file
			}else if(articleForm.getArticleDoc().getFileSize() > 10000000 ){
				// file size exceeds 10MB file
			}else{
				
			
			byte[] byteArray= articleForm.getArticleDoc().getFileData();
			String fileLoc = "D:\\Workspace\\MyChunks\\uploaded\\" + articleForm.getArticleDoc().getFileName().replaceAll(" ", "_");
			File myFile = new File(fileLoc);
			
			FileOutputStream fos = new FileOutputStream(myFile);
			
			fos.write(byteArray);
			fos.flush();
			fos.close();
			
			if(helper.addArticleDoc(articleForm, fileLoc)){
				List<ArticleDTO> list=new ArrayList<ArticleDTO>();		
				list= helper.listArticle();
				request.setAttribute("articleList",list);

				outcome = "list";
			}
			
			
			//FileInputStream fis = new fileFileInputStream();
			
			}
			/*	Blob myblob=Hibernate.createBlob(byteArray);
			awardForm.setConvertedImage(myblob);*/
		}


		return mapping.findForward(outcome);

	}
	
	//Add & View Awards
	public ActionForward addArticle(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Helper helper=new Helper();
		ArticleFormBean articleForm=(ArticleFormBean) form;
		String outcome = "fail";
		System.out.println("in AddArticleAction.addArticle()");
		
		//String articleDesc = articleForm.getArticleDesc();
		String articleTitle = articleForm.getArticleTitle();
		String articleName = articleForm.getArticleName();
		
		if(articleName == null){
			if(articleTitle !=null){
				articleName = articleTitle;
			}
		}
		
		/*
		  
		 ArticleMaster articleMaster = new ArticleMaster();
		 populatePojo(articleForm, articleMaster);
		
		
		Calendar cal = GregorianCalendar.getInstance();
		articleMaster.setModifiedDate(CommonConstants.SDF.format(cal.getTime()));
		articleMaster.setCreatedDate(CommonConstants.SDF.format(cal.getTime()));
		*/
		
		if(helper.addArticle(articleForm)){
			List<ArticleDTO> list=new ArrayList<ArticleDTO>();		
			list= helper.listArticle();
			request.setAttribute("articleList",list);

			outcome = "list";
		}
		

		return mapping.findForward(outcome);

	}








	public ActionForward update(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		boolean outcome=false;

		ArticleFormBean articleFormBean = (ArticleFormBean)form;
		Integer articleId = articleFormBean.getArticleId();
		
		Helper helper=new Helper();
		//update resource to DB		
		outcome=helper.updateArticle(articleFormBean);

		//get resource list 
		List<ArticleDTO> list=new ArrayList<ArticleDTO>();		
		list= helper.listArticle();
		request.setAttribute("articleList",list);

		if(outcome)
			return mapping.findForward("list");
		else
			return mapping.findForward("list");
	}

	public ActionForward delete(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		boolean outcome=false;
		
		
		//--deleting resources--//

		int articleId = Integer.parseInt(request.getParameter("articleId"));
		outcome = helper.deleteArticle(articleId);


		//get resource list 
		List<ArticleDTO> list=new ArrayList<ArticleDTO>();		
		list= helper.listArticle();
		request.setAttribute("articleList",list);



		if(outcome)
			return mapping.findForward("list");
		else
			return mapping.findForward("list");
	}



	public ActionForward updateView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		ArticleFormBean articleFormBean = (ArticleFormBean)form;

		

		int articleId=Integer.parseInt(request.getParameter("articleId"));
		articleFormBean.setArticleId(articleId);

		System.out.println("ArticleAction.updateView articleId:: " +articleId);
		articleFormBean = helper.getArticleDetail(articleFormBean);
		articleFormBean.setEdit("yes");

		if(articleFormBean!=null)
			return mapping.findForward("update");
		else
			return mapping.findForward("view_update_failure");

	}
	
	public boolean isValid(String checkStr) {
		boolean result = false;
		
		if(checkStr !=null && checkStr.trim().length() > 0){
			result = true;
		}
		return result;
	}
	


}
