<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE >
<html>
<head>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"
	media="screen" />

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/open/openTutorial.css"
	media="screen" />

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/css/open/open.css"
	media="screen" />

	
	
<style type="text/css">
.ui-widget {
	font-family: Lucida Grande, Lucida Sans, Arial, sans-serif;
	font-size: 0.8em;
}



</style>

<% String title = (String) request.getAttribute("articleTitle");
	String desc = (String) request.getAttribute("desc");
	String description = (String) request.getAttribute("description");

	if(title ==null){
		title = "My CHunks Without Title";
	}
	if(description ==null){
		description = "My CHunks Description ";
	}
	/*if(desc == null){
		RequestDispatcher rd = request.getRequestDispatcher("404.jsp");
	    	rd.forward(request,response);               
    	}*/

%>


<title><%= title %></title>
 <META NAME="Description" CONTENT="<%= description %>">
</head>
<body class="body">


<div id="container" class="container">


	<%@ include file="../header/header.jsp" %>

<!--div id="leftDiv"-->
	<div id="menu" style="width:25%;float:left;">
		<div style="top:0;">
			<% helper.getFeatureArticle(request, "3"); %>
			<div>
				<%@ include file="featuredArticle.jsp" %>
			</div>
		</div>

	<!--/div-->
		<div style="bottom:0;">
		<!--div id="menu" style="width:25%;float:left;"-->
		<% helper.getRelatedArticle(request, "3"); %>
		<div>
			<%@ include file="relatedArticle.jsp" %>
		</div>
		</div>
	</div>



	<div id="content" style="width:45%;float:left;">
		This is my JSP content to show
		<h1>this is H1 content</h1>
		<div id='content'>
        <pre class='brush: js'>
              package tutorial;
              import com.opensymphony.xwork2.ActionSupport;
              public class HelloWorld extends ActionSupport {
              	private String name;
              	public String getName() {
              	return name;
              	}
              	public void setName(String name) {
              		this.name = name;
              	}
              	public String execute() {
              		name = "Hello, " + name + "!";
              		return SUCCESS;
              	}
              }
              </pre>
        </div>
	</div>
							

</div>

	<div id="menu" style="width:25%;float:right;">
		<% helper.getLatestArticle(request, "3"); %>
		<%@ include file="latestArticle.jsp" %>
	</div>
	
	<div id="footer" style="clear:both;text-align:center;">
		<%@ include file="../footer.jsp" %>
	</div>

</body>
  <link href="<%= request.getContextPath() %>/css/sh/shCore.css" rel='stylesheet' type='text/css'>
  <link href="<%= request.getContextPath() %>/css/sh/shThemeDefault.css" rel='stylesheet' type='text/css'>
  <script src="<%= request.getContextPath() %>/js/sh/shCore.js" type='text/javascript'></script>
  <script src="<%= request.getContextPath() %>/js/sh/shBrush.js" type='text/javascript'></script>
<!-- Finally, to actually run the highlighter, you need to include this JS on your page -->
<script type="text/javascript">
     SyntaxHighlighter.all()
</script>
</html>
