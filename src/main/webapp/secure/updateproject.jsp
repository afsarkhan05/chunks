<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	<html:form action="/project">
		<center>
			<h2>Add Project</h2>
			<table class="table">

				<tr>
					<td>Project Name:</td>
					<td><html:text property="projectName" name="Project" /></td>
				</tr>
				<tr>
					<td>Bugzilla Name:</td>
					<td><html:text property="bugzillaName" /> {Name of project registered in Bugzilla}</td>
				</tr>
				<tr>
					<td>Redmine Name:</td>
					<td><html:text property="redmindName" /> {Name of project registered in Redmine}</td>
				</tr>
				<tr>
					<td>Active:</td>
					<td><html:select property="isActive">
							<html:option value="yes">Yes</html:option>
							<html:option value="no">No</html:option>
						</html:select></td>
				</tr>
				<tr>
					<td>Modified Date:</td>
					<td><html:text property="modifiedDate" name="Project" styleId="modifiedDate"/></td>

				</tr>
				<tr>
					<td>Created Date:</td>
					<td><html:text property="createdDate" name="Project" styleId="createdDate" /></td>

				</tr>
				<tr  style="display:none;">
					<td>Is this Redmine/Bugzilla Project:</td>
					<td><html:select property="isRedmindProject">
							<html:option value="Bugzilla">Bugzilla</html:option>
							<html:option value="Redmine">Redmine</html:option>
						</html:select></td>
				</tr>
				<tr>
					<td><html:submit property="method" value="update" /></td>
					<td><html:reset /></td>
				</tr>
			</table>
		</center>
	</html:form>
	</html:html>

</body>
</html>