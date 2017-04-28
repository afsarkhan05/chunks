$(document).ready(function() {
		$("#resource").dataTable();
	});


function editDeleteResource(id, type, path) {
	var frm = document.forms[0];
	var pageName = path + "/resource.do?method=" + type + "&resourceId="
			+ id;
	frm.action = pageName;
	frm.submit();
}