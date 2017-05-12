package com.bigdata.chunks.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.*;
import javax.servlet.http.*;

import com.bigdata.chunks.form.AllocateForm;
import com.bigdata.chunks.dao.*;
import com.bigdata.chunks.util.Helper;

public class ViewResource extends Action{
	
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		AllocateForm allocateform = (AllocateForm)form;
		Helper helper=new Helper();
		
		System.out.println("::ViewResource Action");
		//get resource list 
		List<ResourceDTO> list=new ArrayList<ResourceDTO>();		
		list=helper.listResource();
		
		request.setAttribute("resourceList",list);
		
		if(list!=null)
			return mapping.findForward("success");
		else
			return mapping.findForward("failure");
	}
	
}