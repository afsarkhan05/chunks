<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
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
<html:form action="/resource">
<center>
	<h2>Update Resources</h2>
	<table>
	
		<tr>
			<td>Resource Name:</td>
			<td><html:text property="resourceName" name="Resource"/></td>
		</tr>
		
		<tr>
			<td>Project ID:</td>
			<td><html:select property="projectId">
							<html:option value="0">Select Project</html:option>
							<html:optionsCollection name="projectList" label="projectName"
								value="id" />
						</html:select></td>
		</tr>
		<tr>
			<td>BugZilla Name:</td>
			<td><html:text property="bugzillaName" name="Resource"/></td>
		</tr>
		<tr>
			<td>RedMine Name:</td>
			<td><html:text property="redmindName" name="Resource"/></td>
		</tr>
		<tr>
			<td>Active:</td>
			<td><html:select property="isActive" name="Resource">
			<html:option value="yes">Yes</html:option>
			<html:option value="no">No</html:option>
			</html:select>
			</td>
		</tr>
		<tr>
			<td>Modified Date:</td>
			<td><html:text property="modifiedDate" name="Resource" styleId="modifiedDate"/></td>
			
		</tr>
		<tr>
			<td>Created Date:</td>
			<td><html:text property="createdDate" name="Resource" styleId="createdDate"/></td>
			
		</tr>
		<tr>
			<td><html:submit value="update" property="method"/></td>
			<td><html:reset /></td>
		</tr>
	</table>
	</center>
</html:form>
</html:html>
</body>
</html>