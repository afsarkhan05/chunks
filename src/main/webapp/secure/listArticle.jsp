

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html>
<head>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="css/jquery.dataTables_themeroller.css" rel="stylesheet" type="text/css"
	media="screen" />

<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#resource").dataTable();
	});
</script>

<script type="text/javascript">
	function editDeleteArticle(id, type, path) {
		var frm = document.forms[0];
		var pageName = path + "/addArticle.do?method=" + type + "&articleId="
				+ id;
		frm.action = pageName;
		frm.submit();
	}
</script>
</head>
<body>
	<html:html>
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
						<th>Actions</th>
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
							
							<td>
							<logic:equal value="no" name="list" property="isFile" >
								<input type="button"
									onClick="editDeleteArticle('<bean:write name="list" property="articleId"/>', 
														'updateView','<%=request.getContextPath()%>')"
									value="update" /> <input type="button"
									onClick="editDeleteArticle('<bean:write name="list" property="articleId"/>', 
													'delete','<%=request.getContextPath()%>')"
									value="delete" />
							</logic:equal>&nbsp;
								</td>

						</tr>
					</logic:iterate>

				</tbody>
			</table>

		</center>
	</html:form>
	</html:html>
</body>
</html>