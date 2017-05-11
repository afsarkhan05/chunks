<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE >
<html>
<head>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
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

<%     String title = (String) request.getAttribute("title");
	String description = (String) request.getAttribute("description");
	
	if(title ==null){
		title = "My CHunks Without Title";
	}
	if(description ==null){
		description = "My CHunks Description ";
	}


%>


<title><%= title %></title>
 <META NAME="Description" CONTENT="<%= description %>">
</head>
<body class="body">


<div id="container" class="container">


	<%@ include file="header/header.jsp" %>


	<div id="menu" style="width:25%;float:left;">
		<div>
			<%@ include file="relatedArticle.jsp" %>
		</div>
	</div>



	<div id="content" style="width:45%;float:left;">
		<c:forEach var="articleResult" items="${articleResultList}">
			<div class="title"  style="width 100%"> <a href="${articleResult.anchor}" rel="bookmark"  target="_blank"> ${articleResult.title} </a> </div>
			<div class="spoiler"  style="width 100%"> <p> ${articleResult.description}...
			<a href="${articleResult.anchor}" rel="bookmark" target="_blank">View Detail </a>  </p>
			</div>
	        </c:forEach>
		<%@ include file="pagination.jsp" %>
	</div>
							

</div>

	<div id="menu" style="width:25%;float:right;">
		<%@ include file="latestArticle.jsp" %>
	</div>

	<div id="footer" style="clear:both;text-align:center;">
		<%@ include file="footer.jsp" %> 
	</div>

</body>
</html>
