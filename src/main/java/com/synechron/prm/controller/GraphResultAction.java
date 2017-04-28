package com.synechron.prm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.synechron.prm.dao.Dao;
import com.synechron.prm.dao.ProjectDTO;
import com.synechron.prm.form.GraphResultForm;
import com.synechron.prm.util.Helper;

public class GraphResultAction extends DispatchAction {

	Helper helper=new Helper();
	ActionErrors errors = new ActionErrors();
	//check login details
	public ActionForward getGraph(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		
		
		GraphResultForm resultForm=(GraphResultForm) form ;
		System.out.println("GraphResultAction.getGraph()");
		List<ProjectDTO> projectList=null;
		//projectList=Dao.getProjectList(false);
		request.setAttribute("projectList", projectList);
		
		return mapping.findForward("success");


	}
	
	public ActionForward getMaturity(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		
		
		GraphResultForm resultForm=(GraphResultForm) form ;
		System.out.println("GraphResultAction.getMaturity()");
		List<ProjectDTO> projectList=null;
		//projectList=Dao.getProjectList(false);
		request.setAttribute("projectList", projectList);
		
		return mapping.findForward("successMaturity");


	}
	
	public ActionForward getGraphForAdmin(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{	
		
		GraphResultForm resultForm=(GraphResultForm) form ;
		System.out.println("GraphResultAction.getGraph()");
		List<ProjectDTO> projectList=null;
		//projectList=Dao.getProjectList(false);
		request.setAttribute("projectList", projectList);
		
		return mapping.findForward("successGraphAdmin");

	}
	
	public ActionForward getMaturityForAdmin(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		
		
		GraphResultForm resultForm=(GraphResultForm) form ;
		System.out.println("GraphResultAction.getMaturity()");
		List<ProjectDTO> projectList=null;
		//projectList=Dao.getProjectList(false);
		request.setAttribute("projectList", projectList);
		
		return mapping.findForward("successMaturityAdmin");


	}
	
}
