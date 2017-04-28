<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
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
	<html:form action="/registerUser">
		<center>
			<h2>Add user</h2>
			<table class="table">

				<tr>
					<td>User Name:</td>
					<td><html:text property="userName" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><html:password property="password" /></td>
				</tr>
				<tr>
					<td>First Name:</td>
					<td><html:text property="firstName" /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><html:text property="lastName" /></td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td><html:select property="gender">
							<html:option value="male">Male</html:option>
							<html:option value="female">Female</html:option>
						</html:select></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><html:text property="email" /></td>
				</tr>

				<tr>
					<td>Mobile:</td>
					<td><html:text property="mobile" /></td>
				</tr>
				
				<tr>
					<td>Address:</td>
					<td><html:text property="address" /></td>
				</tr>	
				<tr>
					<td>City:</td>
					<td><html:text property="city" /></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><html:text property="state" /></td>
				</tr>
				<tr>
					<td>Country:</td>
					<td><html:text property="country" /></td>
				</tr>
				<tr>
					<td>Security Question:</td>
					<td><html:text property="securityQuestion" /></td>
				</tr>
				<tr>
					<td>Security Answer:</td>
					<td><html:text property="securityAnswer" /></td>
				</tr>

				<tr>
					<td><img id="captcha" src="<c:url value="../simpleCaptcha.jpg"  />" width="150"></td>
					<td><input type="text" name="answer" /><br></td>
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