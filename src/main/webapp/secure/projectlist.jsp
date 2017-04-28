<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>


<html>
<head>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="css/jquery.dataTables_themeroller.css" rel="stylesheet"
	type="text/css" media="screen" />

<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#project").dataTable();
	});
</script>

<script type="text/javascript">
	function editDeleteProject(id, type, path) {
		var frm = document.forms[0];
		var pageName = path + "/project.do?method=" + type + "&id=" + id;
		frm.action = pageName;
		frm.submit();
	}
</script>
</head>
<body>
	<html:html>
	<center>
		<html:form action="/project">
			<h2>Project List</h2>
			<table border="2" class="dataTable" id="project">
				<thead>
					<tr>
						<th>Project ID</th>
						<th>Project Name</th>
						<th>Active</th>
						<th>Modified Date</th>
						<th>Created Date</th>
						<th>RedMind Project</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="list" name="projectList">
						<tr>

							<td><bean:write name="list" property="id" /></td>
							<td><bean:write name="list" property="projectName" /></td>
							<td><bean:write name="list" property="isActive" /></td>
							<td><bean:write name="list" property="modifiedDate" /></td>
							<td><bean:write name="list" property="createdDate" /></td>
							<td><bean:write name="list" property="isRedmindProject" /></td>

							<td><input type="button"
								onClick="editDeleteProject('<bean:write name="list" property="id"/>', 
'updateView','<%=request.getContextPath()%>')"
								value="update" /> <input type="button"
								onClick="editDeleteProject('<bean:write name="list" property="id"/>', 
'delete','<%=request.getContextPath()%>')"
								value="delete" /></td>

						</tr>
					</logic:iterate>

				</tbody>
			</table>
		</html:form>
	</center>
	</html:html>
</body>
</html>