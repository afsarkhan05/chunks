package com.synechron.prm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.synechron.prm.bean.AuthMaster;
import com.synechron.prm.dao.PaginatorDAO;
import com.synechron.prm.form.UserForm;
import com.synechron.prm.util.Helper;

public class UserListAction extends Action {
	
	Helper helper=new Helper();

	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		UserForm userForm = (UserForm)form;
		
		int page = 1;
        int recordsPerPage = 2;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        PaginatorDAO dao = new PaginatorDAO();
        List<AuthMaster> list = dao.viewAllUsers((page-1)*recordsPerPage, recordsPerPage);
        int noOfRecords = dao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        System.out.println("User List Size " + list.size());
        request.setAttribute("employeeList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        return mapping.findForward("success");

	}

}
