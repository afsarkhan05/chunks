package com.synechron.prm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.synechron.prm.dao.UserDTO;
import com.synechron.prm.form.UserForm;
import com.synechron.prm.util.Helper;

public class RegisterUserAction extends DispatchAction
{

	
	
	
	public ActionForward view(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		UserForm userForm = (UserForm)form;

		Helper helper=new Helper();
		//get resource list 
		List<UserDTO> list=new ArrayList<UserDTO>();		
		list= helper.listUsers();
		request.setAttribute("userList",list);

		if(list!=null)
			return mapping.findForward("view_success");
		else
			return mapping.findForward("view_failure");

	}


	public ActionForward add(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		boolean outcome=false;

		UserForm userForm = (UserForm)form;
		
		Helper helper=new Helper();
		//adding resource to DB		
		outcome=helper.addUser(userForm);

		//get resource list 
		List<UserDTO> list=new ArrayList<UserDTO>();		
		list= helper.listUsers();
		request.setAttribute("userList",list);

		if(outcome)
			return mapping.findForward("add_success");
		else
			return mapping.findForward("add_failure");
	}

	public ActionForward update(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		boolean outcome=false;

		UserForm userForm = (UserForm)form;
		String userId = (String) request.getSession(true).getAttribute("userId");
		userForm.setUserId(Integer.parseInt(userId));
		
		Helper helper=new Helper();
		//update resource to DB		
		outcome=helper.updateUser(userForm);

		//get resource list 
		List<UserDTO> list=new ArrayList<UserDTO>();		
		list= helper.listUsers();
		request.setAttribute("userList",list);

		if(outcome)
			return mapping.findForward("update_success");
		else
			return mapping.findForward("update_failure");
	}

	public ActionForward delete(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		boolean outcome=false;
		UserForm userForm = (UserForm)form;

		Helper helper=new Helper();
		
		//--deleting resources--//

		int userId=Integer.parseInt(request.getParameter("userId"));
		outcome=helper.deleteUser(userId);


		//get resource list 
		List<UserDTO> list=new ArrayList<UserDTO>();		
		list= helper.listUsers();
		request.setAttribute("userList",list);



		if(outcome)
			return mapping.findForward("delete_success");
		else
			return mapping.findForward("delete_failure");
	}



	public ActionForward updateView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		UserForm userForm = (UserForm)form;

		Helper helper=new Helper();

		int userId=Integer.parseInt(request.getParameter("userId"));
		userForm.setUserId(userId);

		System.out.println(userId);
		userForm=helper.getuserDetail(userForm);


		if(userForm!=null)
			return mapping.findForward("view_update_success");
		else
			return mapping.findForward("view_update_failure");

	}

}
