<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"
	media="screen" />
	
<link type="text/css" rel="stylesheet" href="css/jquery-ui.css"
	media="screen" />

<style type="text/css">
.ui-widget {
	font-family: Lucida Grande, Lucida Sans, Arial, sans-serif;
	font-size: 0.8em;
}
</style>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>



	
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
	<html:form action="/resource">
		<center>
			<h2>Add Resources</h2>
			<table class="table">

				<tr>
					<td>Resource Name:</td>
					<td><html:text property="resourceName" /></td>
				</tr>

				<tr>

					<td>Project Name:</td>
					<td><html:select property="projectId">
							<html:option value="0">Select Project</html:option>
							<html:optionsCollection name="projectList" label="projectName"
								value="id" />
						</html:select></td>

				</tr>
				<tr>
					<td>BugZilla Name:</td>
					<td><html:text property="bugzillaName" /></td>
				</tr>
				<tr>
					<td>RedMine Name:</td>
					<td><html:text property="redmindName" /></td>
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
					<td><html:text property="modifiedDate" styleId="modifiedDate" /></td>

				</tr>
				<tr>
					<td>Created Date:</td>
					<td><html:text property="createdDate" styleId="createdDate" /></td>

				</tr>
				<tr>
					<td><html:reset /></td>
					<td><html:submit value="add" property="method" /></td>

				</tr>
			</table>
		</center>
	</html:form>
	</html:html>
</body>
</html>