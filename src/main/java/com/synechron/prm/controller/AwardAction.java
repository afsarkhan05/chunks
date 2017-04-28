package com.synechron.prm.controller;



import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.Hibernate;

import com.synechron.prm.dao.AwardsDTO;
import com.synechron.prm.dao.Dao;
import com.synechron.prm.dao.GraphDTO;
import com.synechron.prm.dao.ProjectDTO;
import com.synechron.prm.dao.ResourceDTO;
import com.synechron.prm.form.AllocateForm;
import com.synechron.prm.form.AwardForm;
import com.synechron.prm.form.AwardForm;
import com.synechron.prm.form.AwardForm;
import com.synechron.prm.util.Helper;



public class AwardAction extends DispatchAction {

	Helper helper=new Helper();
	byte []img=null;
	//Add & View Awards
	public ActionForward add(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		AwardForm awardForm=(AwardForm) form;
		boolean outcome;
		//convert image & set to bean
		System.out.println("in AwardAction.add()");

		if(awardForm.getImage()!=null){

			byte[] byteArray=awardForm.getImage().getFileData();
			awardForm.setConvertedImage(byteArray);

			/*	Blob myblob=Hibernate.createBlob(byteArray);
			awardForm.setConvertedImage(myblob);*/
		}

		//add to database
		outcome=helper.addAwardsDetails(awardForm);

		List<AwardsDTO> list=new ArrayList<AwardsDTO>();		
		list=helper.getAwards(awardForm);
		request.setAttribute("awardslist",list);

		if(outcome=true){

			return mapping.findForward("success");

		}else{

			return mapping.findForward("success");
		}

	}

	public ActionForward updateView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		AwardForm awardForm = (AwardForm)form;

		//get project list
		int empId=Integer.parseInt(request.getParameter("id"));
		awardForm.setId(empId);
		System.out.println(empId);
		awardForm=helper.getPerticularAwardDetails(awardForm);
		//System.out.println("Resource Name::" + awardForm.getResourceName());


		return mapping.findForward("viewUpdateSuccess");
	}

	public ActionForward update(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{	
		AwardForm awardForm = (AwardForm)form;

		boolean outcome=false;

		outcome=helper.updateAwards(awardForm);

		//getting project list 
		List<AwardsDTO> list=new ArrayList<AwardsDTO>();		
		list=helper.getAwards(awardForm);
		request.setAttribute("awardslist",list);

		if(outcome)
			return mapping.findForward("update_success");
		else
			return mapping.findForward("update_failure");


	}

	public ActionForward delete(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		AwardForm awardForm = (AwardForm)form;

		boolean outcome=false;


		int id=Integer.parseInt(request.getParameter("id"));
		outcome=helper.deleteAwards(id);

		//getting project list 
		List<AwardsDTO> list=new ArrayList<AwardsDTO>();		
		list=helper.getAwards(awardForm);
		request.setAttribute("awardslist",list);

		if(outcome)
			return mapping.findForward("delete_success");
		else
			return mapping.findForward("delete_failure");
	}

	public ActionForward getAwards(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		AwardForm awardForm=(AwardForm) form;

		//getting project list 
		List<AwardsDTO> list=new ArrayList<AwardsDTO>();		
		list=helper.getAwards(awardForm);

		request.setAttribute("awardslist",list);

		if(list!=null){
			return mapping.findForward("getDetailSuccess");
		}else{
			return mapping.findForward("getDetailSuccess");
		}

	}

	public ActionForward viewPerticularAward(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		AwardForm awardForm=(AwardForm) form;

		int id=new Integer(request.getParameter("id"));


		awardForm.setId(id);

		System.out.println("Getting details for id: "+ id);

		awardForm=helper.getPerticularAwardDetails(awardForm);
		img=awardForm.getConvertedImage();

		return mapping.findForward("viewPerticularSuccess");
	}

	public ActionForward getAwardsForMonth(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{


		AwardForm awardForm=(AwardForm) form ;

		System.out.println("AwardAction.getAwardsForMonth");

		List<AwardsDTO> winnerList=null;
		winnerList=helper.getWinnersListOfMonth(awardForm);
		request.setAttribute("winnerList", winnerList);

		return mapping.findForward("viewMonthSuccess");
	}

	public ActionForward getImage(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response)throws Exception
	{

		response.setContentType("image/jpeg");

		ServletOutputStream stream = response.getOutputStream();
		stream.write(img);
		stream.flush();
		stream.close();

		return null;

	}

}
