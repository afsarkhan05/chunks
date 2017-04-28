package com.synechron.prm.controller;

import org.apache.struts.action.*;

import javax.servlet.http.*;

import com.synechron.prm.form.RegisterForm;
import com.synechron.prm.util.Helper;


public class LogOutAction extends Action
{
	Helper helper=new Helper();
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		ActionMessages messages = new ActionMessages();
	    messages.clear();
	   
	    HttpSession session = request.getSession();
	    session.setAttribute("userName", null);
	    session.setAttribute("userId", null);
	    session.invalidate();
	    
		messages.add(null, new ActionMessage("warning.logout.success"));
		if(! messages.isEmpty()){
			saveMessages(request, messages);
		}
		
	
		System.out.println("Log Out Successfully");
		return mapping.findForward("success");

	}
}