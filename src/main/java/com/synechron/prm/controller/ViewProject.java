package com.synechron.prm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.synechron.prm.dao.ProjectDTO;
import com.synechron.prm.util.Helper;
import com.synechron.prm.form.ProjectForm;;

public class ViewProject extends Action {


	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		ProjectForm projectform=(ProjectForm)form;
		Helper helper=new Helper();

		//getting project list 
		List<ProjectDTO> list=new ArrayList<ProjectDTO>();		
		list=helper.listProject();

		request.setAttribute("projectList",list);

		if(list!=null)
			return mapping.findForward("success");
		else
			return mapping.findForward("getDetailFailure");
	}

}
