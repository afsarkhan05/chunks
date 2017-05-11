<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html:html>


<logic:messagesPresent message="true" property="error.username.required">
		<div style="color:red">
			<html:messages message="true" id="msg">
				<bean:write name="username" ignore="true"/>
			</html:messages>
		</div>
		<div style="color:green">
			<html:messages property="error.password.required" id="message">
				<bean:write name="password" />
			</html:messages>
		</div>
	</logic:messagesPresent>
<html:form action="/login">
	<div>

		<table border="0">
			<tr>
				<th>UserName</th>
				<td><html:text property="username" /></td>
			</tr>
			<tr>
				<th>PassWord</th>
				<td><html:password property="password" /></td>
			</tr>

			<tr>
				<td colspan="2" align="center"><html:submit value="Login" /></td>
			</tr>
		</table>

	<!-- 
	<div style="float: left; margin-top: 1px;" >
		<table border="0" align="center">
			<tr>
				<th>UserName</th>
				<td><html:text property="username" /></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><html:password property="password" /></td>
			</tr>
			<tr>
				<th>FirstName</th>
				<td><html:text property="firstname" /></td>
			</tr>
			<tr>
				<th>Last Name</th>
				<td><html:text property="lastname" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><html:submit value="signup" />
					<html:submit value="reset" /></td>

			</tr>
		</table>

	</div> -->
	</div>
</html:form>
</html:html>