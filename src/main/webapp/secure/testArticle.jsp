<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



		<%@ include file="/secure/upload/upload.jsp" %>
		
		<html:form action="/addArticle">
		<div id="errorDiv"  style="color: #0000ff;">
		</div>


		Article Name: 
		<html:text property="articleName" name="articleBean" styleId="articleName"/>

		Article Title: &nbsp;
		<html:text property="articleTitle" name="articleBean" styleId="articleTitle"/>

		Category ID: 
		<html:select styleId="categoryId" property="categoryId">
			<html:optionsCollection name="categoryList" value="id" label="label" />
		</html:select>



		<html:textarea property="articleDesc" styleId="content" name="articleBean"></html:textarea>

		<html:hidden property="articleDesc" name="articleBean" styleId="articleDesc" />

		<html:submit value="addArticle" property="method" styleId="method" onclick="return setArticleDesc();">
		
		</html:submit>



	</html:form>

<script type="text/javascript">

function setArticleDesc(){
	var mycontent = tinymce.get('content').getContent();
	// Get the HTML contents of the currently active editor
	//tinymce.activeEditor.getContent();
	
	// Get the raw contents of the currently active editor
	//tinymce.activeEditor.getContent({format : 'raw'});
	
	// Get content of a specific editor:
	//tinymce.get('content id').getContent()
	
	//var divObj = document.getElementById("myHtml"+id);
	//divObj.innerHTML = mycontent;
	//divObj.display = "block";
	
	
	//alert(mycontent);

	var errorDiv = document.getElementById("errorDiv");

	errorDiv.innerHTML = "";
	errorDiv.display = "none";
	if(! document.getElementById("articleName").value){
		
		errorDiv.innerHTML = "Please enter Article Name";
		errorDiv.display = "block";
		return false;
	}
	if(! document.getElementById("articleTitle").value){
		
		errorDiv.innerHTML = "Please enter Article Title";
		errorDiv.display = "block";
		return false;
	}
	if(! mycontent){
		errorDiv.innerHTML = "Please enter Article Description";
		errorDiv.display = "block";
		return false;
	}
	
	
	document.getElementById("articleDesc").value = mycontent;
	//document.getElementById("method").value = "addArticle";
	
	//var frm = document.forms[1];
	//frm.submit();

}
</script>

<script type="text/javascript" src="<%= request.getContextPath() %>/js/tinymce/tinymce.min.js"></script>

<script type="text/javascript">

tinymce.init({
       selector: "textarea",
	theme: "modern",
	//strict_loading_mode :true,
	tinymce : true,
	ie7_compat : false,
	//toolbar working one
	//toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
	toolbar1: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
    	toolbar2: "print preview media | forecolor backcolor emoticons",
    	image_advtab: true,
	plugins: [
	//old
	// "advlist autolink lists link image charmap print preview anchor searchreplace visualblocks code fullscreen insertdatetime media table contextmenu paste"
	
	//below most working
	 // "advlist autolink lists link image charmap print preview anchor searchreplace visualchars  code insertdatetime media table contextmenu paste wordcount visualblocks autoresize  autosave directionality emoticons   hr   layer legacyoutput    nonbreaking noneditable pagebreak    save  tabfocus  template textcolor"
	  //fullscreen bbcode"
	  
	  //full functionality
	  "advlist autolink lists link image charmap print preview hr anchor pagebreak",
        "searchreplace wordcount visualblocks visualchars code fullscreen",
        "insertdatetime media nonbreaking save table contextmenu directionality",
        "emoticons template paste textcolor"
	 
    	]
	
});

</script>

</body>
</html>