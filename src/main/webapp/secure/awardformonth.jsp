<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css"
	media="screen" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function viewAward() {
		var pid = document.getElementById("id");
		var id = pid.options[pid.selectedIndex].value;
		var pageName = 'award.do?method=viewPerticularAward&id=' + id;
		document.getElementById('content').src = pageName;
	}
	
	setInterval(setChangeInWinner, 6000);
	
	function setChangeInWinner() {

		$('option:selected', '#id').removeAttr('selected').next('option').attr(
				'selected', 'selected');

		viewAward();

	}
</script>

</head>
<body>
	<html:form action="/award">
		<table class="hiddenTable">
			<tr>
				<td><html:select property="name" onchange="viewAward();"
						styleId="id">
						<html:optionsCollection name="winnerList" label="name" value="id" />
					</html:select></td>

			</tr>

		</table>
		<logic:empty name="winnerList" scope="request"><div>No Employee for Month</div></logic:empty>
		<iframe id="content" name="content" width="1500px" style="height:1800px; width:1300px;" height="418"
			frameborder="0" scrollbar='no'></iframe>
	</html:form>
</body>
</html>