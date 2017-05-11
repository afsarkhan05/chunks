<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html>
<head>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"
	media="screen" />


<style type="text/css">
.ui-widget {
	font-family: Lucida Grande, Lucida Sans, Arial, sans-serif;
	font-size: 0.8em;
}
</style>


</head>
<body class="body">
	<html:html>
	<html:form action="/category">
		<center>
			<h2><logic:equal value="yes" property="edit" name="categoryFormBean" >Modify Category Detail</logic:equal>
			    <logic:equal value="no" property="edit" name="categoryFormBean" >Add Category</logic:equal>
			</h2>
			<table class="table">

				<tr>
					<td>Category Name:</td>
					<td><html:text property="categoryName" name="categoryFormBean"/></td>
				</tr>
				<tr>
					<td>Category Description:</td>
					<td><html:textarea property="categoryDesc" name="categoryFormBean"/></td>
				</tr>
				<tr>
					<td>Is Active:</td>
					<td><html:select property="isActive"  name="categoryFormBean">
							<html:option value="yes">Yes</html:option>
							<html:option value="no">No</html:option>
						</html:select></td>
				</tr>
				
				
				<tr>
					<td><html:reset /></td>
					<td>
						<logic:equal value="no" property="edit" name="categoryFormBean" >
							<html:submit value="add" property="method" >
							Add Category </html:submit>
						</logic:equal>
						<logic:equal value="yes" property="edit" name="categoryFormBean" >
							<html:submit value="update" property="method" >
							Edit Category</html:submit>
						</logic:equal>
					</td>
					
				</tr>
			</table>
		</center>
		<html:hidden property="categoryId" name="categoryFormBean"/>
	</html:form>
	</html:html>
</body>
</html>