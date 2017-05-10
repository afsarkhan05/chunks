<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link type="text/css" rel="stylesheet" href="css/styleForAward.css"
	media="screen" />
</head>
<body>
	<html:html>
	<html:form action="/award" enctype="multipart/form-data">

		<div>
			<table>
				<caption align="top">
					<font class="heading"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Employee
						of the Month - <font color=" #F64A16"><b><bean:write name="Awards" property="awardMonth" /></b></font>
					</font>
				</caption>
				<tr>
					<td><font class="empName"><bean:write name="Awards"
								property="name" /></font></td>
				</tr>
				<tr>
					<td><font class="empId">Emp ID: <bean:write
								name="Awards" property="empId" /></font></td>
				</tr>

				<tr>
					<td><img style="height: 350px; width: 270px;"
						src="<%=request.getContextPath()%>/award.do?method=getImage">
					</td>
					<td valign="top" align="justify" width="850px" class="descTd"><font
						class="description"><bean:write name="Awards"
								property="desc" /></font></td>
				</tr>
			</table>

		</div>

	</html:form>
	</html:html>
</body>
</html>