<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"  import="com.synechron.prm.util.Helper;" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
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


	<%@ include file="../jsp/header/header.jsp" %>


	<div id="menu" style="width:25%;float:left;">
		<% helper.getLatestArticle(request, "3"); %>
		<div>
			<%@ include file="../jsp/latestArticle.jsp" %>
		</div>
	</div>



	<div id="content" style="width:45%;float:left;">

	<html:form action="/chunks/secure/category">
		<center>
			<h2><logic:equal value="yes" property="edit" name="categoryFormBean" >Modify Category Detail</logic:equal>
			    <logic:equal value="no" property="edit" name="categoryFormBean" >Add Category</logic:equal>
			</h2>
			<table class="table">

				<tr>
					<td>Category Name:</td>
					<td><html:text property="categoryName" name="categoryFormBean"/></td>
				</tr>
				<tr>
					<td>Category Description:</td>
					<td><html:textarea property="categoryDesc" name="categoryFormBean"/></td>
				</tr>
				<tr>
					<td>Is Active:</td>
					<td><html:select property="isActive"  name="categoryFormBean">
							<html:option value="yes">Yes</html:option>
							<html:option value="no">No</html:option>
						</html:select></td>
				</tr>
				
				
				<tr>
					<td><html:reset /></td>
					<td>
						<logic:equal value="no" property="edit" name="categoryFormBean" >
							<html:submit value="add" property="method" >
							Add Category </html:submit>
						</logic:equal>
						<logic:equal value="yes" property="edit" name="categoryFormBean" >
							<html:submit value="update" property="method" >
							Edit Category</html:submit>
						</logic:equal>
					</td>
					
				</tr>
			</table>
		</center>
		<html:hidden property="categoryId" name="categoryFormBean"/>
	</html:form>

	</div>
							

</div>

	<div id="menu" style="width:25%;float:right;">
		<% helper.getFeatureArticle(request, "3"); %>
		<%@ include file="../jsp/featuredArticle.jsp" %>
	</div>

	<div id="footer" style="clear:both;text-align:center;">
		<%@ include file="../jsp/footer.jsp" %> 
	</div>



</body>
</html>

