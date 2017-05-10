<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<link href="images/user_green.png" rel="shortcut icon">
<title>Resource Management</title>
</head>
<body>

	<div align="center" class="div">
		<table class="table">
			<tr>
				<th>
					<h3>Project Resource Management</h3>
				</th>
			</tr>
		</table>
		<br> <br> <br>

		<div class="div" align="center">
			<jsp:include page="/jsp/login.jsp" />
		</div>
	</div>


</body>
</html>