package com.synechron.prm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.synechron.prm.form.ArticleFormBean;
import com.synechron.prm.util.CommonConstants;
import com.synechron.prm.util.Helper;

public class ViewArticleAction extends Action {
	
	Helper helper=new Helper();

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		ArticleFormBean articleForm = (ArticleFormBean)form;
		
		String articleId = request.getParameter("articleId");
		String articleTitle = request.getParameter("articleTitle");
		String categoryId = request.getParameter("categoryId");
		
		System.out.println("Article ID::: "+ articleId);
		System.out.println("categoryId ID::: "+ categoryId);
		System.out.println("Article Title::: "+ articleTitle);
		
		List articleList = null;
		if(articleId !=null){
			articleList = helper.getArticle(articleForm);
		}
		
		if(articleList !=null && ! articleList.isEmpty()){
			Object[] articleArr = (Object[]) articleList.get(0);
			if(articleArr[2] !=null){
				request.setAttribute("articleTitle", CommonConstants.title  + " "+  articleArr[1]);
				request.setAttribute("description", CommonConstants.title  + " "+  articleArr[1]);
				ActionForward af = new ActionForward();
				af.setName("pageForward");
				af.setPath(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+ "/"+articleArr[2].toString()+"?categoryId="+articleArr[3]+"&articleId="+articleId);
				af.setRedirect(true);
				return af;
			}else {
				String htmlStr = org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4(articleArr[0].toString());
				//System.out.println("My HTML STRING:::: "+ htmlStr);
				articleForm.setArticleDesc(htmlStr);
				request.setAttribute("desc", htmlStr);
				request.setAttribute("articleTitle", CommonConstants.title  + " "+  articleArr[1]);
				request.setAttribute("description", CommonConstants.title  + " "+  articleArr[1]);
			}
		}else{
			request.setAttribute("articleTitle", "My Chunks Title ");
			request.setAttribute("description", "My Chunks Desc ");
		}
		
        request.setAttribute("noOfPages", 0);
        request.setAttribute("currentPage", 1);
        
        String articleResult = "/chunks/all/article/";
        if(articleId !=null)
        	articleResult = articleResult + articleId + "/";
        else
        	articleResult = articleResult +  "0/";

        

        request.setAttribute("articleResult", articleResult);
        
        return mapping.findForward("success");

	}

}
