<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



		<%@ include file="/secure/upload/upload.jsp" %>
		
		<html:form action="/chunks/secure/addArticle">
		<div id="errorDiv"  style="color: #0000ff;">
		</div>

		<div>
		Article Name: 
		<html:text property="articleName" name="articleBean" styleId="articleName"/>

		Article Title: &nbsp;
		<html:text property="articleTitle" name="articleBean" styleId="articleTitle"/>

		Category ID: 
		<html:select styleId="categoryId" property="categoryId" name="articleBean">
			<html:optionsCollection name="categoryList" value="id" label="label" />
		</html:select>
		</div>
		
		<logic:equal value="yes" property="edit" name="articleBean" >	
		<div>
			Status: 
			<html:select styleId="status" property="status" name="articleBean">
				<html:option value="Pending">Pending</html:option>
				<html:option value="In Process">In Process</html:option>
				<html:option value="Approved">Approved</html:option>
				<html:option value="Rejected">Rejected</html:option>
			</html:select>
			
			Feature:
			<html:select styleId="isFeature" property="isFeature" name="articleBean">
				<html:option value="yes">Yes</html:option>
				<html:option value="no">No</html:option>
			</html:select>
			
			Is Active:
			<html:select styleId="isActive" property="isActive" name="articleBean">
				<html:option value="yes">Yes</html:option>
				<html:option value="no">No</html:option>
			</html:select>
			
		</div>
		</logic:equal>

		<html:textarea property="articleDesc" styleId="content" name="articleBean"></html:textarea>

		<html:hidden property="articleId" name="articleBean" styleId="articleId" />

		<logic:equal value="no" property="edit" name="articleBean" >	

			<html:submit value="addArticle" property="method" styleId="method" onclick="return setArticleDesc();">
			</html:submit>
		
		</logic:equal>
		
		<logic:equal value="yes" property="edit" name="articleBean" >	

			<html:submit value="update" property="method" styleId="method" onclick="return setArticleDesc();">
			</html:submit>
		
		</logic:equal>



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
	
	
	document.getElementById("content").value = mycontent;
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