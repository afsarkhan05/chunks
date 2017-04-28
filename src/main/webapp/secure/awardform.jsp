<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>

<link type="text/css" rel="stylesheet" href="../css/style.css"
	media="screen" />
	
<link type="text/css" rel="stylesheet" href="../css/jquery-ui.css"
	media="screen" />

<style type="text/css">
.ui-widget {
	font-family: Lucida Grande, Lucida Sans, Arial, sans-serif;
	font-size: 0.8em;
}
</style>

<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>

	
	
<script	src="../js/jquery.rte.min.js"></script>

<script type="text/javascript">
	$(function() {
		$('#datePicker').datepicker({
			
		 dateFormat: 'MM, yy',
	
		}).val();
	});

	$(document).ready(function() {
		
		  $('.#desc').rte("css/rte.css", "images/rte/");


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
					<td><html:text property="empId" value="" /></td>
				</tr>

				<tr>

					<td>Employee Name:</td>
					<td><html:text property="name" value="" /></td>

				</tr>
				<tr>
					<td>Image:</td>
					<td><html:file property="image" value="" /></td>
				</tr>
				<tr>
					<td>Month of the Award:</td>
					<td><html:text property="awardMonth" styleId="datePicker"
							value="" /></td>
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
					<td><html:textarea property="desc" value="" styleId="desc" /></td>
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
