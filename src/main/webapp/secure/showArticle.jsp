<%@page contentType="text/html" pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"   
"http://www.w3.org/TR/html4/loose.dtd">  
  
<%@ page import="java.lang.*" import="java.util.Scanner"  
    import="java.io.FileReader" import="java.net.*"%>  
<%@page import="com.bigdata.chunks.util.DisplayArticle"%>
  
<%  
String articleId = request.getParameter("articleId");
DisplayArticle log = new DisplayArticle(); 
String path = "D:\\Workspace\\MyChunks\\uploaded\\Abrar_Khan_Resume_-_Production.doc";
log.display(path);  
%>  
  
<html>  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>JSP Page</title>  
    </head>  
    <body bgcolor="blue">  
  
        <tr>  
            <CENTER>  
                <img src="images/logo.jpg" width="50" height="60" alt="logo" />  
                <h1>  
                    Project  
                </h1>  
            </CENTER>  
        </tr>  
        <center>  
            <h2>  
                Debug Mode  
            </h2>  
        </center>  
        <HR WIDTH="100%" SIZE="3" NOSHADE>  
        <FORM METHOD=post ACTION="/cgi-bin/example.cgi">  
            <!-- <textarea name="txtAreaLog" cols="30" rows="20" readonly="readonly" title="log area"> </textarea> -->  
            <textarea rows="20" cols="62" readonly=" readonly" WRAP="off"><%=log.getFileOutput()%></textarea>  
        </FORM>  
  
        <FORM METHOD=post ACTION="/cgi-bin/example.cgi">  
            Enter file name:  
            <BR>  
            <INPUT type="text" size="10" maxlength="30">  
            <INPUT type="Submit" VALUE="Submit">  
        </FORM>  
        <div align="right">  
            [  
            <A href="logout.jsp" style=""><font  
                style="font-family: verdana; font-size: 70%;" color="blue"><b>L</b>ogout</font>  
            </A> ]  
        </div>  
    </body>  
</html> 