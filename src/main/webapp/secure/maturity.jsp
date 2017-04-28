<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css"
	media="screen" />


<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/exporting.js"></script>



<script type="text/javascript">
var chart;

	function setChangeInProject(){
		
		$('option:selected', '#id').removeAttr('selected').next('option').attr('selected', 'selected');
			
		drawChart();
	
	}
	
	function setChangeInTracker(){
		
		$('option:selected', '#tool').removeAttr('selected').next('option').attr('selected', 'selected');
			
		drawChart();
	
	}
	
	setInterval(setChangeInProject, 10000);
	setInterval(setChangeInTracker, 5000);
	
function getProject(){
	
	var pid=document.getElementById("id");
	var id = pid.options[pid.selectedIndex].value;
	
	/* var timeStamp=document.getElementById("sort");
	var st= timeStamp.options[timeStamp.selectedIndex].value; */
	
	var tool=document.getElementById("tool");
	var bugTool= tool.options[tool.selectedIndex].value;
	drawChart(id, bugTool);
}

//Auto Refresh after 1 Min
//setInterval(drawChart, 60000);

		function drawChart(id, bugTool) {
			
			// Theme Selction
			/* var colors = Highcharts.getOptions().colors;
			$.each(colors, function(i, color) {
			    colors[i] = {
			        linearGradient: { x1: 0, y1: 0, x2: 1, y2: 0 },
			        stops: [
			            [0, '#0ECDFD'],
			            [0.3, '#F64A16'],
			            [1, color]
			        ]
			    };
			  
			}); */
			
			var nodeName = document.getElementById("id");
			var value = nodeName.selectedIndex;

			var sortName = document.getElementById("tool");
			var sortvalue = sortName.selectedIndex;

			document.getElementById("name").innerHTML = nodeName.options[value].text
					.toUpperCase();

			document.getElementById("sortBy").innerHTML = sortName.options[sortvalue].text
			.toUpperCase();
			
			
			  Highcharts.setOptions({
				     colors: ['#F64A16', '#0ECDFD',],
				     chart: {
					        zoomType: 'xy'
					    }
				    }); 
		
			
			chart = new Highcharts.Chart({
				chart : {
					renderTo : 'container',
					
				},
				title : {
					text : 'Quality Score'
				},
				xAxis : {
					label : 'Time',
				
				},
								    
				tooltip : {
					formatter : function() {
						var s;
						if (this.point.name) { 
							s = '' + this.point.name + ':<b> ' + this.y
									+ '</b> %';
						} else {
							s = '' + this.x + ':<b> ' + this.y
									+ '</b> %';
						}
						return s;
					}
				},
				
				series :  [{

					type : 'column',
					data : [],
					color : '#F64A16',					
					pointWidth : 28,
					

				} ]

			}, function getdata(chart) {
				var pid=document.getElementById("id");
				var id = pid.options[pid.selectedIndex].value;
			
				
				var tool=document.getElementById("tool");
				var bugTool= tool.options[tool.selectedIndex].value;
				
				$.ajax({
					
					url : "MaturityServlet?id=" + id +"&bugTool=" + bugTool,
					dataType : 'json',
					error : function() {
						
					},
					success : function(data) {
						var chartData = [];
						/* var arrayBugzilla = [];
						var arrayRedmind = [];*/
						var arrayTimeStamp=[]; 
						$.each(data.jsonArray, function(index) {
							$.each(data.jsonArray[index], function(key,
									value) {
								var point = [];

							if (key == "arrayData") {
									
								for(var i=0;i<value.length;i++){
										
										chartData.push(value[i]);
										
									}
									
									
								}  else if (key == "arrayTimeStamp") {
								
									for(var i=0;i<value.length;i++){
										
										arrayTimeStamp.push(value[i]);
									}
								}else {

									
								}

							});
						});
						
						
						chart.series[0].setData(chartData);
						//chart.series[1].setData(arrayRedmind);
						chart.xAxis[0].setCategories(arrayTimeStamp);
						//chart.series[1].setData([ 30, 45, 96, 38, 54 ]);
						//chart.series[2].setData([ 12, 33, 51, 37, 46 ]);
					}
					

				});

			});

		}
		

</script>


</head>
<body onload="drawChart(' ',' ');">

	<html:html>
	<html:form action="/graph" >
	<table>
			<tr>
				<td class="nodeLabel">Project Name</td>
				<td class="nodeName"><div id="name"></div></td>
				<td class="nodeLabel">Source</td>
				<td class="nodeName"><div id="sortBy"></div></td>
				
			</tr>
		</table>
		<table class="hiddenTable">
			<tr>
				<td>Project Name</td>
				<td><html:select property="projectName" onchange="getProject();" styleId="id">
						<html:option value="0">All</html:option>
						<html:optionsCollection name="projectList" label="projectName"
							value="id" />
					</html:select></td>
				
				<td>BugTracker</td>
				<td><SELECT id="tool" onchange="getProject();">
						<OPTION VALUE="bugzilla">BugZilla
						<OPTION VALUE="redmind">RedMind
				</SELECT></td>
			</tr>

		</table>

		<div id="container" style="left:300px; top:200px; height: 500px; width: 1300px;"></div>
			
	</html:form>
	</html:html>
</body>
</html>