<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"
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
	<html:form action="/addArticleDoc"   enctype="multipart/form-data">
		<center>
			<h2>Upload Article .doc File</h2>
			<table class="table">

				<tr>
					<td>Article Name:</td>
					<td><html:text property="articleName" name="articleBean"/></td>
				</tr>

				<tr>

					<td>Article Header/Title:</td>
					<td><html:text property="articleTitle"  name="articleBean" /></td>

				</tr>
				<tr>
					<td>Your Doc File:</td>
					<td><html:file property="articleDoc" name="articleBean" /></td>
				</tr>
				
				<tr>
					<td><html:reset /></td>
					<td><html:submit value="add" property="method" >Add Article</html:submit></td>
				</tr>
			</table>
		</center>
	</html:form>
	</html:html>
</body>
</html>
