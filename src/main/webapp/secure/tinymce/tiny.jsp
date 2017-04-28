<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
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
