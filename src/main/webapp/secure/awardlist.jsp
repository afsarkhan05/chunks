

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html>
<head>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="css/jquery.dataTables.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="css/jquery.dataTables_themeroller.css" rel="stylesheet"
	type="text/css" media="screen" />

<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#resource").dataTable();
	});
</script>
<script type="text/javascript">
	function viewAward(id, path) {
		var frm = document.forms[0];
		var pageName = path + "/award.do?method=viewPerticularAward&id="
				+ id;
		frm.action = pageName;
		frm.submit();
	}
	
	function editDeleteAward(id, method, path){
	
		var frm = document.forms[0];
		var pageName = path + "/award.do?method=" + method + "&id="
				+ id;
		frm.action = pageName;
		frm.submit();
		
	}
</script>

</head>
<body>
	<html:html>
	<html:form action="/award">
		<h2 align="center">Winner List</h2>
		<table border="2" class="dataTable" id="resource">
			<thead>
				<tr>
					<th>Employee ID</th>
					<th>Resource Name</th>
					<th>Month of Award</th>
					<th>Active</th>
					<th>Manipulate</th>

				</tr>
			</thead>
			<tbody>
				<logic:iterate id="list" name="awardslist" scope="request">
					<tr>

						<td><a href="#"
							onclick="viewAward('<bean:write name="list" property="id"/>','<%=request.getContextPath()%>')"><bean:write
									name="list" property="empId" /></a></td>
									
						<td><a href="#"
							onclick="viewAward('<bean:write name="list" property="id"/>','<%=request.getContextPath()%>')"><bean:write
									name="list" property="name" /></a></td>

						<td><bean:write name="list" property="awardMonth" /></td>
						
						<td><bean:write name="list" property="isActive" /></td>
						
						<td><input type="button"
							onClick="editDeleteAward('<bean:write name="list" property="id"/>','updateView',
								'<%=request.getContextPath()%>');"
							value="update" /> <input type="button"
							onClick="editDeleteAward('<bean:write name="list" property="id"/>', 'delete',
								'<%=request.getContextPath()%>');"
							value="delete" /></td>
					</tr>
				</logic:iterate>

			</tbody>
		</table>
	</html:form>
	</html:html>
</body>
</html>