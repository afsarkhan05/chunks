<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>

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
	<html:form action="/registerUser">
		<center>
			<h2><logic:equal value="yes" property="edit" name="User" >Modify User Detail</logic:equal>
			    <logic:equal value="no" property="edit" name="User" >Add User Registration</logic:equal>
			</h2>
			<table class="table">

				<tr>
					<td>User Name:</td>
					<td><html:text property="userName" name="User"/></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><html:password property="password" name="User"/></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><html:text property="email" name="User"/></td>
				</tr>
				<logic:equal value="yes" property="edit" name="User" >
				<tr>
					<td>First Name:</td>
					<td><html:text property="firstName" name="User"/></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><html:text property="lastName" name="User"/></td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td><html:select property="gender">
							<html:option value="male">Male</html:option>
							<html:option value="female">Female</html:option>
						</html:select></td>
				</tr>
				<tr>
					<td>Mobile:</td>
					<td><html:text property="mobile"  name="User" /></td>
				</tr>
				
				<tr>
					<td>Address:</td>
					<td><html:text property="address"  name="User" /></td>
				</tr>	
				<tr>
					<td>City:</td>
					<td><html:text property="city"  name="User" /></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><html:text property="state"  name="User" /></td>
				</tr>
				<tr>
					<td>Country:</td>
					<td><html:text property="country"  name="User" /></td>
				</tr>
				</logic:equal>
				<tr>
					<td>Security Question:</td>
					<td><html:text property="securityQuestion"  name="User" /></td>
				</tr>
				<tr>
					<td>Security Answer:</td>
					<td><html:text property="securityAnswer"  name="User" /></td>
				</tr>

				<tr>
					<td>
					<logic:equal value="no" property="edit" name="User" >
						<html:img styleId="captcha" src="../simpleCaptcha.jpg" width="150"/>
					</logic:equal>
					<logic:equal value="yes" property="edit" name="User" >
						<html:img styleId="captcha" src="simpleCaptcha.jpg" width="150"/>
					</logic:equal>
					</td>
					<td><input type="text" name="answer"  name="User"/><br></td>
				</tr>
				<tr>
					<td><html:reset /></td>
					<td>
						<logic:equal value="no" property="edit" name="User" >
							<html:submit value="add" property="method" >
							Add Me </html:submit>
						</logic:equal>
						<logic:equal value="yes" property="edit" name="User" >
							<html:submit value="update" property="method" >
							Edit Me</html:submit>
						</logic:equal>
					</td>
					
				</tr>
			</table>
		</center>
	</html:form>
	</html:html>
</body>
</html>