package com.bigdata.chunks.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bigdata.chunks.bean.DisplayMaster;
import com.bigdata.chunks.dao.PaginatorDAO;
import com.bigdata.chunks.util.Helper;

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
        
        String query = "select SQL_CALC_FOUND_ROWS * from article_master ";

        if(categoryId !=null && ! categoryId.trim().equalsIgnoreCase("0")){
            try{
                query = query + "where categoryId = "+ Integer.parseInt(categoryId);
            }catch(Exception e){

            }
        }
        query = query + " limit "+(page-1)*recordsPerPage + ", " + recordsPerPage;
        
        ArrayList returnList=null;
        String[] items = {"articleName", "articleTitle","articleId", "articleDesc"};
        returnList = dao.fireQuery(query, false, items);
        
        List<DisplayMaster> list = helper.getResultList(returnList, request);
        request.setAttribute("articleResultList", list);
        
        int noOfRecords = dao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        System.out.println("noOfRecords pages " + noOfRecords);
        
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

}
