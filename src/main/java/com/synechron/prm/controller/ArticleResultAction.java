package com.synechron.prm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.synechron.prm.bean.DisplayMaster;
import com.synechron.prm.dao.PaginatorDAO;
import com.synechron.prm.util.CommonConstants;
import com.synechron.prm.util.Helper;

public class ArticleResultAction extends Action {
	
	Helper helper=new Helper();

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		int page = 1;
        int recordsPerPage = 2;
        String categoryId = request.getParameter("categoryId");
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        PaginatorDAO dao = new PaginatorDAO();
        String query = "select SQL_CALC_FOUND_ROWS * from article_master limit "
            + (page-1)*recordsPerPage + ", " + recordsPerPage;
        
        ArrayList returnList=null;
        String[] items = {"articleName", "articleTitle","articleId", "articleDesc"};
        returnList = dao.fireQuery(query, false, items);
        
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
						request.setAttribute("title", CommonConstants.title + " " + (String) rowList.get(0));
						request.setAttribute("description", CommonConstants.title + " "+ (String) rowList.get(0));
					}
					
					displayMaster.setDescription(setDescription((String) rowList.get(3)));

					displayMaster.setAnchor(setArticleAnchor((String) rowList.get(1), (String) rowList.get(2), request));
					list.add(displayMaster);
				}
			}
		}
		
        
        
        
        int noOfRecords = dao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        System.out.println("noOfRecords pages " + noOfRecords);
        request.setAttribute("articleResultList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        
        String articleResult = "/chunks/all/article/";
        if(categoryId !=null)
        	articleResult = articleResult + categoryId + "/";
        else
        	articleResult = articleResult +  "0/";

        request.setAttribute("articleResult", articleResult);
        	
        	
        return mapping.findForward("success");

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

}
