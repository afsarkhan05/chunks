package com.bigdata.chunks.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.*;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.*;

import com.bigdata.chunks.form.*;
import com.bigdata.chunks.util.Helper;
import com.bigdata.chunks.dao.*;

public class AllocateAction extends DispatchAction
{

	Helper helper=new Helper();

	public ActionForward add(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		boolean outcome=false;

		AllocateForm allocateform = (AllocateForm)form;

		//adding resource to DB		
		outcome=helper.addResource(allocateform);

		//get resource list 
		List<ResourceDTO> list=new ArrayList<ResourceDTO>();		
		list= helper.listResource();
		request.setAttribute("resourceList",list);

		if(outcome)
			return mapping.findForward("success");
		else
			return mapping.findForward("failure");
	}

	public ActionForward update(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		boolean outcome=false;
		AllocateForm allocateForm = (AllocateForm)form;

		outcome=helper.updateResource(allocateForm);

		//get resource list 
		List<ResourceDTO> list=new ArrayList<ResourceDTO>();		
		list=helper.listResource();
		request.setAttribute("resourceList",list);

		if(outcome)
			return mapping.findForward("update_success");
		else
			return mapping.findForward("update_failure");
	}

	public ActionForward delete(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		boolean outcome=false;
		AllocateForm allocateForm = (AllocateForm)form;


		//--deleting resources--//

		//allocateForm.setRid(new Integer(request.getParameter("rid")));

		int resourceId=Integer.parseInt(request.getParameter("resourceId"));
		outcome=helper.deleteResource(resourceId);


		//get resource list 
		List<ResourceDTO> list=new ArrayList<ResourceDTO>();		
		list=helper.listResource();
		request.setAttribute("resourceList",list);


		if(outcome)
			return mapping.findForward("delete_success");
		else
			return mapping.findForward("delete_failure");
	}
	
	public ActionForward addResourceView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception{
	
	//	AllocateForm allocateForm = (AllocateForm)form;
		//getting projectList
		
		//allocateForm=helper.getProjectList(allocateForm);
		//request.setAttribute("List", allocateForm);
		
		List<ProjectDTO> projectList=null;
		//projectList=Dao.getProjectList(true);
		request.setAttribute("projectList", projectList);
		
		return mapping.findForward("addResourceView");
	}
	
	public ActionForward updateView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		AllocateForm allocateForm = (AllocateForm)form;
		
		//get project list
		int resourceId=Integer.parseInt(request.getParameter("resourceId"));
		allocateForm.setResourceId(resourceId);
		System.out.println(resourceId);
		allocateForm=helper.getResourceDetails(allocateForm);
		
		List<ProjectDTO> projectList=null;
		//projectList=Dao.getProjectList(true);
		request.setAttribute("projectList", projectList);
		
		System.out.println("Resource Name::" + allocateForm.getResourceName());
		//request.setAttribute("resourceDetails",allocateForm);

		if(allocateForm!=null)
			return mapping.findForward("view_update_success");
		else
			return mapping.findForward("view_update_failure");

	}
}