package com.bigdata.chunks.controller;

import java.util.List;

import org.apache.struts.action.*;

import javax.servlet.http.*;

import com.bigdata.chunks.form.RegisterForm;
import com.bigdata.chunks.util.Helper;


public class RegisterAction extends Action
{
	
	//check login details
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		RegisterForm registerForm =(RegisterForm) form;
		HttpSession session = request.getSession(true);
		ActionMessages messages = new ActionMessages();
		messages=validate(mapping,request,registerForm, messages);
		String result = "failure";
		if(! messages.isEmpty())
		{
			//checking login details		
			boolean isValidUser = false;
			String isAdmin = "no";
			String userId = null;
			Helper helper=new Helper();
			List loginDetailList = helper.loginCheck(registerForm);
			//loginDetailList.add("afsar");loginDetailList.add("yes");loginDetailList.add("1");
			if(loginDetailList !=null && ! loginDetailList.isEmpty()){
				Object[] loginArr = (Object[]) loginDetailList.get(0);
				isValidUser = loginArr[0].toString() != null ? true : false;
				if(isValidUser){
					isAdmin = loginArr[1].toString();
					userId = loginArr[2].toString();
				}
			
				//boolean isAdmin=helper.adminCheck(registerForm.getUsername());
			
				registerForm.setIsAdmin(isAdmin);
			
			
				if (isValidUser) {
					session.setAttribute("userName", registerForm.getUsername());
					session.setAttribute("userId", userId);
					session.setAttribute("admin", isAdmin);
					result = "success";
						
					String togoURL  = (String) session.getAttribute("togoURL");
					System.out.println("Found TODO URL:"+ togoURL);
					if(togoURL !=null && togoURL.trim().length() >0){
						request.getRequestDispatcher(togoURL).forward(request, response);	
						session.setAttribute("togoURL", null);
					}
					//session.setAttribute("username", registerForm.getUsername());	
					System.out.println("Admin :>> " + registerForm.getIsAdmin());
					System.out.println("Session ID :>> " + session.getId());
					System.out.println("User ID :>> " + userId);
	
				}else{
					messages.add(null, new ActionMessage("error.login.invalid"));
				}
			}
		}
			if(! messages.isEmpty()){
				saveMessages(request,messages);
			}
	
			
			return mapping.findForward(result);
		
	}

	public ActionMessages validate(ActionMapping mapping, HttpServletRequest request, RegisterForm form, ActionMessages messages) {

		
		messages.add("message",new ActionMessage("actioinMessage.updateSuccess"));
		
		System.out.println("validating");
		if (form.getUsername() == null || form.getUsername().length() < 1) {
			messages.add("username", new ActionMessage("error.username.required"));
		}
		if (form.getPassword()== null || form.getPassword().length() < 1) {
			messages.add("password", new ActionMessage("error.password.required"));
		}

		return messages;
	}



}

