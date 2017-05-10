package com.synechron.prm.controller;

import com.synechron.prm.dao.Dao;
import com.synechron.prm.dao.GraphDTO;
import com.synechron.prm.util.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class MaturityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GraphDTO dto=new GraphDTO();
	Dao dao=new Dao();
	Helper helper=new Helper();

	public MaturityServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		/*String id=request.getParameter("id");
		String bugTool=request.getParameter("bugTool");
		
		System.out.println("Project ID:: " + id + " Tool:: " + bugTool);
		int []chartData=null;
		String[] arrayTimeStamp=null;
		
		
		
		if(id.equals("0")&&bugTool.equals("bugzilla")){
			
			//getting project list & set to simple array list
			List<ProjectDTO> projectList=null;
			//projectList=Dao.getProjectList(false);
			List<String> timeStamp = new ArrayList<String>();
			for(ProjectDTO dto:projectList){

				timeStamp.add(dto.getProjectName());

			}
			String[] arrayList = new String[ timeStamp.size() ];
			timeStamp.toArray( arrayList );
			arrayTimeStamp=arrayList;
			
			//getting array
			chartData=helper.getMaturityBugZillaArrayForAll(timeStamp.size());
			
			
			
		}else if(id.equals("0")&&bugTool.equals("redmind")){
			
			//getting project list & set to simple array list
			List<ProjectDTO> projectList=null;
			//projectList=Dao.getProjectList(false);
			List<String> timeStamp = new ArrayList<String>();
			for(ProjectDTO dto:projectList){

				timeStamp.add(dto.getProjectName());

			}
			String[] arrayList = new String[ timeStamp.size() ];
			timeStamp.toArray( arrayList );
			arrayTimeStamp=arrayList;
			
			//getting array
			chartData=helper.getMaturityRedMindArrayForAll(timeStamp.size());
			
		}else{
			
			if(!id.equals("0")&&bugTool.equals("bugzilla")){
				
				//getting array
				chartData=helper.getMaturityBugZillaArray(new Integer(id));
				
			arrayTimeStamp=new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
				
			}else if(!id.equals("0")&&bugTool.equals("redmind")){
				
				//getting array
				chartData=helper.getMaturityRedMindArray(new Integer(id));
				arrayTimeStamp=new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
				
			}
			
		}
		
		//Old Code

		
		//write response in JSON	
		response.setContentType("application/json;charset=utf-8");

		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject member =  new JSONObject();

		member.put("arrayData", chartData);
		member.put("arrayTimeStamp", arrayTimeStamp);

		array.add(member);

		json.put("jsonArray", array);

		PrintWriter pw = response.getWriter(); 
		pw.print(json.toString());

		//System.out.println("json object :"+json.toString());

		pw.close();*/
	}



}
