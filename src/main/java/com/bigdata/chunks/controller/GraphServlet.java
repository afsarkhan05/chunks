package com.bigdata.chunks.controller;

import com.bigdata.chunks.dao.Dao;
import com.bigdata.chunks.dao.GraphDTO;
import com.bigdata.chunks.dao.ProjectDTO;
import com.bigdata.chunks.util.Helper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class GraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GraphDTO dto=new GraphDTO();
	Dao dao=new Dao();
	Helper helper=new Helper();

	public GraphServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		String id=request.getParameter("id");
		String st=request.getParameter("timeStamp");

		dto.setBugzillaCount(dao.getTotalBugCountForPieBugzilla());
		dto.setRedmindCount(dao.getTotalBugCountForPieRedmind());
		int totalBugzillaCount=0;
		int totalRedmindCount=0;
		int []bugzilla=null;
		int []redmind=null;
		String[] arrayTimeStamp=null;

		

		if(id.equals("0")&&st.equals("all")){

			totalBugzillaCount=dto.getBugzillaCount();
			totalRedmindCount=dto.getRedmindCount();

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

			//getting bugcount of each
			bugzilla=helper.getBugZillaArrayForAll(timeStamp.size(), st);
			redmind=helper.getRedMindArrayForAll(timeStamp.size(), st);


			

		}else if(id.equals("0")&&!st.equals("all")){

			totalBugzillaCount=dto.getBugzillaCount();
			totalRedmindCount=dto.getRedmindCount();
			
			if(st.equals("daily")){

				bugzilla=helper.getBugZillaArray(new Integer(id), st);
				redmind=helper.getRedMindArray(new Integer(id), st);
				arrayTimeStamp=new String[]{"12-2 AM","2-4 AM","4-6 AM","6-8 AM","8-10 AM","10-12 PM",
						"12-2 PM","2-4 PM","4-6 PM", "6-8 PM", " 8-10 PM", "10-12 AM"};

			}else if(st.equals("week")){

				bugzilla=helper.getBugZillaArray(new Integer(id), st);
				redmind=helper.getRedMindArray(new Integer(id), st);
				arrayTimeStamp=new String[]{"Week 1","Week 2","Week 3","Week 4","Week 5"};
			}else if(st.equals("weekly")){

				bugzilla=helper.getBugZillaArray(new Integer(id), st);
				redmind=helper.getRedMindArray(new Integer(id), st);
				arrayTimeStamp=new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
			}
			else if(st.equals("monthly")){

				bugzilla=helper.getBugZillaArray(new Integer(id), st);
				redmind=helper.getRedMindArray(new Integer(id), st);
				arrayTimeStamp=new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
			}


		}else if(!id.equals("0")&&st.equals("all")){


			System.out.println("get count for id:: " + id + " sort by:: Alll ");

			totalBugzillaCount=helper.getBugZillaCount(Integer.parseInt(id));
			totalRedmindCount=helper.getRedMindCount(Integer.parseInt(id));


		}else if(!id.equals("0")&&!st.equals("all")){


			System.out.println("get count for id:: " + id + " sort by:: " + st);

			totalBugzillaCount=helper.getBugZillaCount(Integer.parseInt(id));
			totalRedmindCount=helper.getRedMindCount(Integer.parseInt(id));



			if(st.equals("daily")){

				bugzilla=helper.getBugZillaArray(new Integer(id), st);
				redmind=helper.getRedMindArray(new Integer(id), st);
				arrayTimeStamp=new String[]{"12-2 AM","2-4 AM","4-6 AM","6-8 AM","8-10 AM","10-12 PM",
						"12-2 PM","2-4 PM","4-6 PM", "6-8 PM", " 8-10 PM", "10-12 AM"};

			}else if(st.equals("week")){

				bugzilla=helper.getBugZillaArray(new Integer(id), st);
				redmind=helper.getRedMindArray(new Integer(id), st);
				arrayTimeStamp=new String[]{"Week 1","Week 2","Week 3","Week 4","Week 5"};
			}else if(st.equals("weekly")){

				bugzilla=helper.getBugZillaArray(new Integer(id), st);
				redmind=helper.getRedMindArray(new Integer(id), st);
				arrayTimeStamp=new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
			}
			else if(st.equals("monthly")){

				bugzilla=helper.getBugZillaArray(new Integer(id), st);
				redmind=helper.getRedMindArray(new Integer(id), st);
				arrayTimeStamp=new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
			}
		}




		//write response in JSON	
		response.setContentType("application/json;charset=utf-8");

		/*JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		JSONObject member =  new JSONObject();

		member.put("arrayBugzilla", bugzilla);
		member.put("arrayRedmind", redmind);

		member.put("Bugzilla",  totalBugzillaCount);
		member.put("Redmine", totalRedmindCount);

		member.put("arrayTimeStamp", arrayTimeStamp);

		array.add(member);

		json.put("jsonArray", array);

		PrintWriter pw = response.getWriter(); 
		pw.print(json.toString());

		

		pw.close();*/
	}



}
