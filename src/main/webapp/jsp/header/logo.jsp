<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="floatLeft"><img src="<%= request.getContextPath() %>/images/jc1.png" /></div>
<div  class="floatRight">
<% 
	//String userId = (String) session.getAttribute("userId");
	String userName =(String) session.getAttribute("userName");
	if(userName == null){


%>
	<a href="<%= request.getContextPath()%>/login.do">Sign In </a>
	<span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
	<a href="<%= request.getContextPath()%>/jsp/registerUser.jsp">Register</a>
	
<% }else{ %>
	<span class="textColor">Hello, </span><a href="<%= request.getContextPath()%>/editUser.do"><%=userName%></a> &nbsp;
	<a href="<%= request.getContextPath()%>/logout.do">Sign Out </a>
	<span>&nbsp;&nbsp;|&nbsp;&nbsp;</span>
	<a href="<%= request.getContextPath()%>/editUser.do">Setting</a>

<% } %>
</div>