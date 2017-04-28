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


<link href="<%=request.getContextPath()%>/css/jquery.dataTables.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="<%=request.getContextPath()%>/css/jquery.dataTables_themeroller.css" rel="stylesheet" type="text/css"
	media="screen" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#resource").dataTable();
	});
</script>

<script type="text/javascript">
	function editDeleteCategory(id, type, path) {
		var frm = document.forms[0];
		//var pageName = path + "/category.do?method=" + type + "&categoryId=" 	+ id;
		var pageName = path + "/chunks/secure/category/" + type + "/" + id;
		frm.action = pageName;
		frm.submit();
	}
</script>
</head>
<body>


<div id="container" class="container">


	<%@ include file="../jsp/header/header.jsp" %>


	<div id="menu" style="width:25%;float:left;">
		<% helper.getLatestArticle(request, "3"); %>
		<div>
			<%@ include file="../jsp/latestArticle.jsp" %>
		</div>
	</div>



	<div id="content" style="width:45%;float:left;">

	<html:form action="/resource">
		<center>
			<h2>User List</h2>
			<table border="2" class="dataTable" id="resource">
				<thead>
					<tr>
						<th>Category Name</th>
						<th>Category Description</th>
						<th>Is Active</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="list" name="categoryList" scope="request">
						<tr>
							<td><bean:write name="list" property="categoryName" /></td>
							<td><bean:write name="list" property="categoryDesc" /></td>
							<td><bean:write name="list" property="isActive" /></td>

							<td><input type="button"
								onClick="editDeleteCategory('<bean:write name="list" property="categoryId"/>', 
													'updateView','<%=request.getContextPath()%>')"
								value="update" />
							</td>
							<td>
								<input type="button"
								onClick="editDeleteCategory('<bean:write name="list" property="categoryId"/>', 
												'delete','<%=request.getContextPath()%>')"
								value="delete" />
							</td>

						</tr>
					</logic:iterate>

				</tbody>
			</table>

		</center>
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