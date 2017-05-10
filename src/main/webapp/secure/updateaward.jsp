<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"
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
		$('#datePicker').datepicker({
			dateFormat : "MM, yy"
		}).val();
	});
</script>
</head>
<body class="body">
	<html:html>
	<html:form action="/award" enctype="multipart/form-data">
		<center>
			<h2>Add Award Details</h2>
			<table class="table">

				<tr>
					<td>Employee ID:</td>
					<td><html:text property="empId" /></td>
				</tr>

				<tr>

					<td>Employee Name:</td>
					<td><html:text property="name" /></td>

				</tr>
				<tr>
					<td>Image:</td>
					<td><html:file property="image" /></td>
				</tr>
				<tr>
					<td>Month of the Award:</td>
					<td><html:text property="awardMonth" styleId="datePicker" /></td>
				</tr>
				<tr>
					<td>Is Active:</td>
					<td><html:select property="isActive">
							<html:option value="yes">Yes</html:option>
							<html:option value="no">No</html:option>
						</html:select></td>
				</tr>
				<tr>
					<td>Description</td>
					<td><html:textarea property="desc" /></td>
				</tr>
				<tr>
					<td><html:reset /></td>
					<td><html:submit property="method" value="update" /></td>

				</tr>
			</table>
		</center>
	</html:form>
	</html:html>
</body>
</html>
