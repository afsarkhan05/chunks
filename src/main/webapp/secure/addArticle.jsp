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
	
<style type="text/css">
.ui-widget {
	font-family: Lucida Grande, Lucida Sans, Arial, sans-serif;
	font-size: 0.8em;
}
</style>


<script type="text/javascript" src="tinymce.min.js"></script>


<script type="text/javascript">
	tinymce.init({
	    selector: "textarea",
		theme: "modern",
	    plugins: [
	        "advlist autolink lists link image charmap print preview anchor",
	        "searchreplace visualblocks code fullscreen",
	        "insertdatetime media table contextmenu paste"
	    ],
	    toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image"
	});
	
	function showAlert(){
	var mycontent = tinymce.get('content').getContent();
	// Get the HTML contents of the currently active editor
	//tinymce.activeEditor.getContent();
	
	// Get the raw contents of the currently active editor
	//tinymce.activeEditor.getContent({format : 'raw'});
	
	// Get content of a specific editor:
	//tinymce.get('content id').getContent()
	
	
	alert(mycontent);
	}
</script>

</head>
<body class="body">
	<html:html>
	<html:form action="/addFoto"  enctype="multipart/form-data">
		<center>
			<h2>Add Article Details</h2>
		</center>
		<html:submit value="add" property="method" />
	</html:form>		
	</html:html>
	<html:html>
	<html:form action="/chunks/secure/addArticle">
		<center>
			<h2>Add Article Details</h2>
			<table class="table">

				<tr>
					<td>Article Name:</td>
					<td><html:text property="articleName" value="" /></td>
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
