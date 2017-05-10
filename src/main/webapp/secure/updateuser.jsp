<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
<head>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css"
	media="screen" />
<link
	href="css/jquery-ui.css"
	rel="stylesheet" type="text/css" />

<style type="text/css">
.ui-widget {
	font-family: Lucida Grande, Lucida Sans, Arial, sans-serif;
	font-size: 0.8em;
}
</style>

<script
	src="js/jquery.js"></script>
<script
	src="js/jquery-ui.js"></script>



<script type="text/javascript">
	$(function() {
		$('#modifiedDate').datepicker({
			dateFormat : "yy-mm-dd",

		}).val();
	});

	$(function() {
		$('#createdDate').datepicker({
			dateFormat : "yy-mm-dd"
		}).val();
	});
</script>

</head>
<body class="body">
	<html:html>
	<html:form action="/user">
		<center>
			<h2>Update user</h2>
			<table class="table">

				<tr>
					<td>User Name:</td>
					<td><html:text property="userName" name="User" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><html:password property="password" name="User"/></td>
				</tr>
				<tr>
					<td>First Name:</td>
					<td><html:text property="firstName" name="User"/></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><html:text property="lastName" name="User"/></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><html:text property="email" name="User"/></td>
				</tr>

				<tr>
					<td>Mobile:</td>
					<td><html:text property="mobile" name="User"/></td>
				</tr>
				<tr>
					<td>Active:</td>
					<td><html:select property="isActive" name="User">
							<html:option value="yes">Yes</html:option>
							<html:option value="no">No</html:option>
						</html:select></td>
				</tr>
				<tr>
					<td>Admin:</td>
					<td><html:select property="isAdmin" name="User">
							<html:option value="yes">Yes</html:option>
							<html:option value="no">No</html:option>
						</html:select></td>
				</tr>
				<tr>
					<td>Modified Date:</td>
					<td><html:text property="modifiedDate" styleId="modifiedDate" name="User"/></td>

				</tr>
				<tr>
					<td>Created Date:</td>
					<td><html:text property="createdDate" styleId="createdDate" name="User"/></td>

				</tr>
				<tr>
					<td><html:submit value="update" property="method" /></td>
					<td><html:reset /></td>
				</tr>
			</table>
		</center>
	</html:form>
	</html:html>
</body>
</html>