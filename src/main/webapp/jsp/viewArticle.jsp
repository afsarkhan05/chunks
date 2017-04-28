<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"  import="com.synechron.prm.util.Helper;" %>
<!DOCTYPE >
<html>
<head>
<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"
	media="screen" />

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/open/openTutorial.css"
	media="screen" />

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/open/open.css"
	media="screen" />

	
	
<style type="text/css">
.ui-widget {
	font-family: Lucida Grande, Lucida Sans, Arial, sans-serif;
	font-size: 0.8em;
}



</style>

<% String title = (String) request.getAttribute("articleTitle");
	String desc = (String) request.getAttribute("desc");
	String description = (String) request.getAttribute("description");
	
	if(title ==null){
		title = "My CHunks Without Title";
	}
	if(description ==null){
		description = "My CHunks Description ";
	}
	if(desc == null){
		RequestDispatcher rd = request.getRequestDispatcher("404.jsp");
	    	rd.forward(request,response);               
    	}
	Helper helper = new Helper();

%>


<title><%= title %></title>
 <META NAME="Description" CONTENT="<%= description %>">
</head>
<body class="body">


<div id="container" class="container">


	<%@ include file="header/header.jsp" %>

	<div id="menu" style="width:25%;float:left;">
		<% helper.getRelatedArticle(request, "3"); %>
		<div>
			<%@ include file="relatedArticle.jsp" %>
		</div>
	</div>



	<div id="content" style="width:45%;float:left;">
		<%=desc%>
		<%@ include file="pagination.jsp" %>
	</div>
							

</div>

	<div id="menu" style="width:25%;float:right;">
		<% helper.getLatestArticle(request, "3"); %>
		<%@ include file="latestArticle.jsp" %>
	</div>
	
	<div id="footer" style="clear:both;text-align:center;">
		<%@ include file="footer.jsp" %> 
	</div>

</body>
</html>
