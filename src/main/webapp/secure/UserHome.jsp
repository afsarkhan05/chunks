<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="images/user_green.png" rel="shortcut icon">
<title>Resource Management</title>


</head>
<body class="body">
<html:form action="/login">
	<div class="menudiv" align="center">
		<table class="table">
			<tr>
				<th>
					<h3>Project Resource Management</h3>
				</th>
			
			<td valign="bottom" style="color: #F64A16; font-size: 18px;">Welcome, <bean:write name="userName" scope="session"/> </td>
			</tr>
		</table>
		<div class="menu">
			<table class="table">
				<tr>

					<td>
						<ul>
							<li><a href="<%=request.getContextPath() %>/secure/help.jsp"
								target="content">Home</a></li>
						</ul>
					</td>
					<td><ul>
							<li><a>Resource</a>
								<ul>

									<li><a href="<%=request.getContextPath() %>/viewResource.do" target="content">View Resources</a></li>

									<li><a href="<%=request.getContextPath() %>/resource.do?method=addResourceView"
										target="content"> Allocate Resources</a></li>
								</ul></li>
						</ul>
					<td>
						<ul>
							<li><a>Project</a>
								<ul>
									<li><html:link action="/viewProject" target="content">View Project</html:link></li>
									<li><a
										href="<%=request.getContextPath()%>/secure/project.jsp"
										target="content">Add Project</a></li>

								</ul></li>
						</ul>
					</td>
					<logic:match value="yes" name="LoginForm" property="isAdmin">
						<td>
						
						<ul>
							<li><a>User</a>
								<ul>
									<li><html:link action="/user.do?method=view" target="content">View users</html:link></li>
									<li><a
										href="<%=request.getContextPath()%>/secure/adduser.jsp"
										target="content">Add users</a></li>

								</ul></li>
						</ul>
						  
					</td>
					  </logic:match>
					<td>
						<ul>
							<li><a>Award</a>
								<ul>
									<li><a href="<%=request.getContextPath() %>/award.do?method=getAwards"
								target="content">View Awards</a></li>
								
									<li><a href="<%=request.getContextPath() %>/secure/awardform.jsp"
								target="content">Add Awards</a></li>

								
								<li><a href="<%=request.getContextPath() %>/award.do?method=getAwardsForMonth"
								target="content">View Results</a></li>
						

								</ul></li>
						</ul>
					</td>
					
					<td>
						<ul>
							<li><a>Bug</a>
								<ul>
									<li><a href="<%=request.getContextPath() %>/graph.do?method=getGraphForAdmin"
								target="content">Bug Chart</a></li>

									<li><a href="<%=request.getContextPath() %>/graph.do?method=getMaturityForAdmin"
								target="content">Quality Score</a></li>
								
								</ul></li>
						</ul>
					</td>
					
						<td><ul>
							<li><a>Result</a>
								<ul>
															
								<li><a href="<%=request.getContextPath()%>/secure/slideshow.jsp" target="_blank">
								SlideShow</a></li>
								
								<li><a href="<%=request.getContextPath() %>/award.do?method=getAwardsForMonth"
								target="content">View Award Results</a></li>

								</ul></li>
						</ul></td>
						
					<td><ul>
							<li><html:link action="/logout">Logout</html:link></li>
						</ul></td>

				</tr>
			</table>
		</div>
	</div>
	<div align="center">
		<table>
			<tr>
				<td><iframe name="content" width="1310" height="418" frameborder="0">

					</iframe></td>
			</tr>
		</table>
	</div>
	</html:form>
</body>