package com.synechron.prm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.synechron.prm.util.Helper;
import com.synechron.prm.util.OptionItem;

public class UploadArticleAction extends Action {
	
	Helper helper=new Helper();

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		List<OptionItem> categoryList = (List<OptionItem>)request.getSession(true).getAttribute("categoryList");
		
		if(categoryList ==null || categoryList.isEmpty()){
			List category = helper.getCategory();
			categoryList = new ArrayList<OptionItem>();
			if(category !=null && !category.isEmpty()){
				Object[] str = null;
				OptionItem item = null;
				for (int i = 0; i < category.size(); i++) {
					str = (Object[]) category.get(i);
					item = new OptionItem(str[0].toString(), str[1].toString());
					categoryList.add(item);
				}
			}
			request.getSession(true).setAttribute("categoryList", categoryList);
		
		}
		
		/*String articleId = request.getParameter("articleId");
		String articleTitle = request.getParameter("articleTitle");
		
		System.out.println("Article ID::: "+ articleId);
		System.out.println("Article Title::: "+ articleTitle);
		
		List articleList = null;
		if(articleId !=null){
			articleList = helper.getArticle(articleForm);
		}
		
		if(articleList !=null && ! articleList.isEmpty()){
			Object[] articleArr = (Object[]) articleList.get(0);
			String htmlStr = org.apache.commons.lang3.StringEscapeUtils.unescapeHtml4(articleArr[0].toString());
			
			//System.out.println("My HTML STRING:::: "+ htmlStr);
			
			articleForm.setArticleDesc(htmlStr);
			
			request.setAttribute("desc", htmlStr);
			request.setAttribute("articleTitle", "My Chunks Title "+ articleArr[1]);
			request.setAttribute("articleD", "My Chunks Desc "+ articleArr[1]);
		}else{
			request.setAttribute("articleTitle", "My Chunks Title ");
			request.setAttribute("articleD", "My Chunks Desc ");
		}*/
		
        return mapping.findForward("success");

	}

}
