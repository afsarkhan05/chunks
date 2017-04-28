<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simple CAPTCHA Example</title>
<link href="styles.css" type="text/css" rel="stylesheet" />
</head>
<body>
<center>
<h3>Simple CAPTCHA Example</h3>
<img id="captcha" src="<c:url value="simpleCaptcha.jpg"  />" width="150">
<form action="captchasubmit.jsp" method="post"><input type="text"
	name="answer" /><br>
<input type="submit" value="Submit"></form>
</center>
</body>
</html>
