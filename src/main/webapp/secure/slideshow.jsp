<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Slide Show</title>
<link href="../images/user_green.png" rel="shortcut icon">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css"
	media="screen" />
<link href="../css/slideshow.css" rel="stylesheet" type="text/css"
	media="screen" />
	
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">

function startSwitchPages(){
    setTimeout(pageOne, 2000);
};

var contextPath="${pageContext.request.contextPath}";

function pageOne(){
    var path=contextPath + '/graph.do?method=getGraph';
    document.getElementById('iframe').src = path;
    setTimeout(pageSecond, 100000);
};

function pageSecond(){
    var path=contextPath +'/graph.do?method=getMaturity';
    document.getElementById('iframe').src= path;
    setTimeout(pageThird, 20000);
};

function pageThird(){
    var path=contextPath +'/award.do?method=getAwardsForMonth';
    document.getElementById('iframe').src= path;
    setTimeout(pageOne, 30000);
};
function autoResize(id) {

		var newheight;
		newheight = document.getElementById(id).contentWindow.document.body.scrollHeight;
		newheight = newheight + 20; //20 px extra for all browser
		document.getElementById(id).height = (newheight) + "px";
	};


</script>
</head>
<body onload="startSwitchPages();"  style="margin:0px;padding:0px;overflow:hidden;">

<iframe name="content" frameborder="0"  style=" margin-left: 100px; margin-top: 175px; height:2000px; width:1300px;" scrollbar='no' height="5000px" width="1500px" id="iframe" >

</iframe>


</body>
</html>