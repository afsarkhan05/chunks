package com.bigdata.chunks.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.bigdata.chunks.dao.CategoryDTO;
import com.bigdata.chunks.form.CategoryFormBean;
import com.bigdata.chunks.util.Helper;

public class CategoryAction extends DispatchAction
{

	Helper helper=new Helper();
	
/*	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		return mapping.findForward("");
		
	}*/
	
	
	public ActionForward list(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		Helper helper=new Helper();
		//get resource list 
		List<CategoryDTO> list=new ArrayList<CategoryDTO>();		
		list= helper.listCategory();
		request.setAttribute("categoryList",list);
		
		
		if(list!=null)
			return mapping.findForward("list");
		else
			return mapping.findForward("list");

	}


	public ActionForward add(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		boolean outcome=false;

		CategoryFormBean categoryFormBean = (CategoryFormBean)form;
		
		
		//adding resource to DB		
		outcome= helper.addCategory(categoryFormBean);

		//get resource list 
		List<CategoryDTO> list=new ArrayList<CategoryDTO>();		
		list= helper.listCategory();
		request.setAttribute("categoryList",list);
		
		if(outcome)
			return mapping.findForward("list");
		else
			return mapping.findForward("list");
	}

	public ActionForward update(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		boolean outcome=false;

		CategoryFormBean categoryFormBean = (CategoryFormBean)form;
		String userId = categoryFormBean.getCategoryId();
		
		Helper helper=new Helper();
		//update resource to DB		
		outcome=helper.updateCategory(categoryFormBean);

		//get resource list 
		List<CategoryDTO> list=new ArrayList<CategoryDTO>();		
		list= helper.listCategory();
		request.setAttribute("categoryList",list);

		if(outcome)
			return mapping.findForward("list");
		else
			return mapping.findForward("list");
	}

	public ActionForward delete(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		boolean outcome=false;
		Helper helper=new Helper();
		
		//--deleting resources--//

		int categoryId=Integer.parseInt(request.getParameter("categoryId"));
		outcome=helper.deleteCategory(categoryId);


		//get resource list 
		List<CategoryDTO> list=new ArrayList<CategoryDTO>();		
		list= helper.listCategory();
		request.setAttribute("categoryList",list);



		if(outcome)
			return mapping.findForward("list");
		else
			return mapping.findForward("list");
	}



	public ActionForward updateView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		CategoryFormBean categoryFormBean = (CategoryFormBean)form;

		Helper helper=new Helper();

		int categoryId=Integer.parseInt(request.getParameter("categoryId"));
		categoryFormBean.setCategoryId(String.valueOf(categoryId));

		System.out.println("CategoryAction.updateView categoryId:: " +categoryId);
		categoryFormBean = helper.getCategoryDetail(categoryFormBean);
		categoryFormBean.setEdit("yes");
		
		if(categoryFormBean!=null)
			return mapping.findForward("update");
		else
			return mapping.findForward("view_update_failure");

	}

}
