package com.bigdata.chunks.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.bigdata.chunks.dao.Dao;
import com.bigdata.chunks.dao.ProjectDTO;
import com.bigdata.chunks.form.ProjectForm;
import com.bigdata.chunks.util.Helper;

public class ProjectAction extends DispatchAction {
	
	Dao dao=new Dao();
	Helper helper=new Helper();

	public ActionForward add(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		boolean outcome=false;
		ProjectForm projectForm=(ProjectForm)form;

		//adding project details to DB
		outcome=helper.addProject(projectForm);
		
		//getting project list 
		List<ProjectDTO> list=new ArrayList<ProjectDTO>();		
		//list= dao.retreiveProjectList();
		request.setAttribute("projectList",list);

		if(outcome)
			return mapping.findForward("success");
		else
			return mapping.findForward("failure");
	}

	public ActionForward update(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		boolean outcome=false;
		ProjectForm projectForm=(ProjectForm)form;
		
		//updating project details into DB
		
		outcome=helper.updateProject(projectForm);

		//getting project list 
		List<ProjectDTO> list=new ArrayList<ProjectDTO>();		
		list= helper.listProject();
		request.setAttribute("projectList",list);

		if(outcome)
			return mapping.findForward("update_success");
		else
			return mapping.findForward("update_failure");
	}
	
	public ActionForward delete(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		boolean outcome=false;
		ProjectForm projectForm=(ProjectForm)form;
		
		//deleting project details into DB
		int projectid=Integer.parseInt(request.getParameter("id"));
		outcome=helper.deleteProject(projectid);
			
		//getting project list 
		List<ProjectDTO> list=new ArrayList<ProjectDTO>();		
		//list=dao.retreiveProjectList();
		request.setAttribute("projectList",list);

		if(outcome)
			return mapping.findForward("delete_success");
		else
			return mapping.findForward("delete_failure");
	}
	
	public ActionForward updateView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		boolean outcome=false;
		ProjectForm projectForm=(ProjectForm)form;
		
		int projectId=Integer.parseInt(request.getParameter("id"));
		projectForm.setId(projectId);
		System.out.println(projectId);
		projectForm=helper.getProjectDetails(projectForm);
		
		System.out.println("Project Name::" + projectForm.getProjectName());
		
		return mapping.findForward("view_update_success");
	}

}
