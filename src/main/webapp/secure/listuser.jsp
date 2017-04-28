

<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>

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
	function editDeleteUser(id, type, path) {
		var frm = document.forms[0];
		var pageName = path + "/user.do?method=" + type + "&userId="
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
						<th>User ID</th>
						<th>User Name</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Mobile</th>
						<th>Active</th>
						<th>Admin</th>
						<th>Modified Date</th>
						<th>Created Date</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="list" name="userList" scope="request">
						<tr>
							<td><bean:write name="list" property="userId" /></td>
							<td><bean:write name="list" property="userName" /></td>
							<td><bean:write name="list" property="firstName" /></td>
							<td><bean:write name="list" property="lastName" /></td>
							<td><bean:write name="list" property="email" /></td>
							<td><bean:write name="list" property="mobile" /></td>
							<td><bean:write name="list" property="isActive" /></td>
							<td><bean:write name="list" property="isAdmin" /></td>
							<td><bean:write name="list" property="modifiedDate" /></td>
							<td><bean:write name="list" property="createdDate" /></td>

							<td><input type="button"
								onClick="editDeleteUser('<bean:write name="list" property="userId"/>', 
'updateView','<%=request.getContextPath()%>')"
								value="update" /> <input type="button"
								onClick="editDeleteUser('<bean:write name="list" property="userId"/>', 
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