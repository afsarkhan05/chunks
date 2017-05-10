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
	function editDeleteResource(id, type, path) {
		var frm = document.forms[0];
		var pageName = path + "/resource.do?method=" + type + "&resourceId="
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
			<h2>Resources List</h2>
			<table border="2" class="dataTable" id="resource">
				<thead>
					<tr>
						<th>Resource ID</th>
						<th>Resource Name</th>
						<th>Project Name</th>
						<th>BugZilla Name</th>
						<th>RedMind Name</th>
						<th>Active</th>
						<th>Modified Date</th>
						<th>Created Date</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="list" name="resourceList" scope="request">
						<tr>
							<td><bean:write name="list" property="resourceId" /></td>
							<td><bean:write name="list" property="resourceName" /></td>
							<td><bean:write name="list" property="projectName" /></td>
							<td><bean:write name="list" property="bugzillaName" /></td>
							<td><bean:write name="list" property="redmindName" /></td>
							<td><bean:write name="list" property="isActive" /></td>
							<td><bean:write name="list" property="modifiedDate" /></td>
							<td><bean:write name="list" property="createdDate" /></td>

							<td><input type="button"
								onClick="editDeleteResource('<bean:write name="list" property="resourceId"/>', 
'updateView','<%=request.getContextPath()%>')"
								value="update" /> <input type="button"
								onClick="editDeleteResource('<bean:write name="list" property="resourceId"/>', 
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