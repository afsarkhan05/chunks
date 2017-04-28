<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<link href="<%= request.getContextPath() %>/css/menu/menuTemplate4.css" rel="stylesheet" type="text/css" />
<ul class="menuTemplate4 decor4_1">
    <li><a href="#CSS-Menu">Home</a></li>
    <li class="separator"></li>
    <li><a href="#Horizontal-Menus" class="arrow">Tutorials</a>
        <div class="drop decor4_2" style="width: 300px;">
            <div class='left'>
                <b>Java Web</b>
                <div>
                    <a href="#">JSP</a><br />
                    <a href="#">Servlet</a><br />
					<a href="#">Ajax</a><br />
                    <a href="#">SEO menu</a>
                </div>
            </div>
            <div class='left'>
                <b>Web Frameworks</b>
                <div>
                    <a href="#">Struts 1.x</a><br />
                    <a href="#">Struts 2.x</a><br />
                    <a href="#">Spring</a><br />
		      <a href="#">JSF</a><br />
		      <a href="#">Hibernate</a>
                </div>
            </div>
            <div style='clear: both;'></div>
        </div>
    </li>
    <li class="separator"></li>
    
	<li><a href="#Horizontal-Menus" class="arrow">Java Novice</a>
        <div class="drop decor4_2" style="width: 300px;">
            <div class='left'>
                <b>Java</b>
                <div>
                    <a href="#">How to setup Java</a><br />
                    <a href="#">How to run servlet</a>
                </div>
            </div>
            <div class='left'>
                <b>Eclipse</b>
                <div>
                    <a href="#">Setup Eclipse</a>
                </div>
            </div>
            <div style='clear: both;'></div>
        </div>
    </li>


	<li><a href="#Horizontal-Menus" class="arrow">Database</a>
        <div class="drop decor4_2" style="width: 100px;">
            <div class='left'>
			 <b>Java</b>
                <div>
                    <a href="#">MySql</a><br />
                    <a href="#">Oracle</a><br />
					<a href="#">Netezza</a><br />
					<a href="#">Hadoop</a>
                </div>
            </div>
            <div style='clear: both;'></div>
        </div>
    </li>

    <li class="separator"></li>
    <li><a href="#Horizontal-Menu-CSS">Interview Questions</a></li>

<% 
	if(userName != null){
%>
	<li><a href="<%= request.getContextPath() %>/chunks/secure/">Post Tutorials</a></li>
<%} if(admin !=null && admin.equalsIgnoreCase("yes")){ %>


	<li><a href="#Horizontal-Menus" class="arrow">Manage Article/Category</a>
        <div class="drop decor4_2" style="width: 320px;">
            <div class='left'>
			 <b>Manage Article</b>
                <div>

                     <a href="<%= request.getContextPath() %>/chunks/secure/addArticle/list/1" >List Article</a><br/>
                    <a href="<%= request.getContextPath() %>/chunks/secure/uploadArticle.do">Add Article</a>	              
                </div>
            </div>
			<div class='left'>
				<b>Manage Category</b>
						<div>
							<a href="<%= request.getContextPath() %>/chunks/secure/category/list/1" >List Category</a><br/>
							<a href="<%= request.getContextPath() %>/secure/addCategory.jsp">Add Category</a>							
						</div>
            </div>
            <div style='clear: both;'></div>
        </div>
    </li>

	
<% }%>


    


</ul>
  
