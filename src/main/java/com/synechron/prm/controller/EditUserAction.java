/**
 * 
 */
package com.synechron.prm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.synechron.prm.form.UserForm;
import com.synechron.prm.util.Helper;

/**
 * @author Afsark
 *
 */
public class EditUserAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession(true);
		String userId = (String) session.getAttribute("userId");
		System.out.println("Edit User Action for userId::: "+ userId);
		UserForm userForm = (UserForm) form;
		if(userId !=null && userId.length() > 0){
			Helper helper=new Helper();
			userForm.setUserId(Integer.parseInt(userId));
			helper.getuserDetail(userForm);
			userForm.setEdit("yes");
			
			helper.getuserDetail(userForm);
		}
		
		return mapping.findForward("editUser");
	}
	
}
