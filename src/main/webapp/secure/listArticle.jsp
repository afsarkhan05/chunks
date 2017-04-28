<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>

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
	function editDeleteArticle(id, type, path) {
		var frm = document.forms[0];
		var pageName = path + "/chunks/secure/addArticle/" + type + "/" + id;
		frm.action = pageName;
		frm.submit();
	}
</script>
</head>
<body>


<div id="container" class="container">


	<%@ include file="../jsp/header/header.jsp" %>

	<div id="content" style="width:100%;float:left;">

	<html:form action="/resource">
		<center>
			<h2>User List</h2>
			<table border="2" class="dataTable" id="resource">
				<thead>
					<tr>
						<th>Article Name</th>
						<th>Article Description</th>
						<th>Article Title</th>
						<th>Status</th>
						<th>Is Active</th>
						<th>Is Feature</th>
						<th>Category ID</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="list" name="articleList" scope="request">
						<tr>
							<td><bean:write name="list" property="articleName" /></td>
							<td><bean:write name="list" property="articleDesc" /></td>
							<td><bean:write name="list" property="articleTitle" /></td>
							<td><bean:write name="list" property="status" /></td>
							<td><bean:write name="list" property="isActive" /></td>
							<td><bean:write name="list" property="isFeature" /></td>
							<td><bean:write name="list" property="categoryId" /></td>
							
							
							<logic:equal value="no" name="list" property="isFile" >
							<td>
								<input type="button"
									onClick="editDeleteArticle('<bean:write name="list" property="articleId"/>', 
														'updateView','<%=request.getContextPath()%>')"
									value="update" />
							</td>
							<td>
							<input type="button"
									onClick="editDeleteArticle('<bean:write name="list" property="articleId"/>', 
													'delete','<%=request.getContextPath()%>')"
									value="delete" />
							</td>
							</logic:equal>
							<logic:equal value="yes" name="list" property="isFile" >
								<td>&nbsp;</td><td>&nbsp;</td>
							</logic:equal>


						</tr>
					</logic:iterate>

				</tbody>
			</table>

		</center>
	</html:form>
	</div>
							

</div>


	<div id="footer" style="clear:both;text-align:center;">
		<%@ include file="../jsp/footer.jsp" %> 
	</div>



</body>
</html>