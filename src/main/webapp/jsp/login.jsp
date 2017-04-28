<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"  import="com.synechron.prm.util.Helper;" %>
<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%

Helper helper = new Helper();

%>

<html>
<head>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css"
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


</head>
<body class="body">


<div id="container" class="container">


	<%@ include file="header/header.jsp" %>


	<div id="menu" style="width:25%;float:left;">
		<% helper.getLatestArticle(request, "3"); %>
		<div>
			<%@ include file="latestArticle.jsp" %>
		</div>
	</div>



	<div id="content" style="width:45%;float:left;">

		<logic:messagesPresent message="true" property="error.username.required">
		<div style="color:red">
			<html:messages message="true" id="msg">
				<bean:write name="username" ignore="true"/>
			</html:messages>
		</div>
		<div style="color:green">
			<html:messages property="error.password.required" id="message">
				<bean:write name="password" />
			</html:messages>
		</div>
	</logic:messagesPresent>
	<html:form action="/login">
	<div>

		<table border="0">
			<tr>
				<th>UserName</th>
				<td><html:text property="username" /></td>
			</tr>
			<tr>
				<th>PassWord</th>
				<td><html:password property="password" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><html:submit value="Login" /></td>
			</tr>
		</table>


	</div>
	</html:form>

	</div>
							

</div>

	<div id="menu" style="width:25%;float:right;">
		<% helper.getFeatureArticle(request, "3"); %>
		<%@ include file="featuredArticle.jsp" %>
	</div>

	<div id="footer" style="clear:both;text-align:center;">
		<%@ include file="footer.jsp" %> 
	</div>



</body>
</html>