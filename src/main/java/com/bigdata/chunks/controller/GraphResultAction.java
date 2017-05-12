package com.bigdata.chunks.controller;

import com.bigdata.chunks.dao.ProjectDTO;
import com.bigdata.chunks.form.GraphResultForm;
import com.bigdata.chunks.util.Helper;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
