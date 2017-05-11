

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
	function editDeleteCategory(id, type, path) {
		var frm = document.forms[0];
		var pageName = path + "/category.do?method=" + type + "&categoryId="
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
						<th>Category Name</th>
						<th>Category Description</th>
						<th>Is Active</th>
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
								value="update" /> <input type="button"
								onClick="editDeleteCategory('<bean:write name="list" property="categoryId"/>', 
												'delete','<%=request.getContextPath()%>')"
								value="delete" /></td>

						</tr>
					</logic:iterate>

				</tbody>
			</table>

		</center>
	</html:form>
	</html:html>
</body>
</html>