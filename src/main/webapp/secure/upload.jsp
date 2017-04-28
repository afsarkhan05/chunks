
<!-- script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-form.js"></script-->

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>


<script type="text/javascript" >
$(document).ready(function()
{
$('#photoimg').change(function()
{
$("#imageform").ajaxForm(
{
target: '#preview'
}).submit();
});
});
</script>


<form action="ajaxFile.jsp" id="imageform" method="post" enctype="multipart/form-data">

Upload image from your computer<input type="file" id="photoimg" name="photoimg"/>

</form>


//div to display uploaded file
<div id='preview'></div>