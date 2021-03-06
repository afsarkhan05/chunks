var chart;
	function getProject(){
		
		var pid=document.getElementById("id");
		var id = pid.options[pid.selectedIndex].value;
		
		var timeStamp=document.getElementById("list");
		var st= timeStamp.options[timeStamp.selectedIndex].value;
		drawChart(id, st);
	}
	
		
			function drawChart( id, st) {
				
				chart = new Highcharts.Chart({
					chart : {
						renderTo : 'container',

					},
					title : {
						text : 'Bug chart'
					},
					xAxis : {
						label : 'Time',
					
					},
				
					tooltip : {
						formatter : function() {
							var s;
							if (this.point.name) { // the pie chart
								s = '' + this.point.name + ': ' + this.y
										+ ' Bugs';
							} else {
								s = '' + this.x + ': ' + this.y
										+ ' Bugs';
							}
							return s;
						}
					},
					labels : {
						items : [ {
							html : 'Total Bugs',
							style : {
								left : '65px',
								top : '80px',
								color : 'black'
							}
						} ]
					},
					series : [ {

						type : 'pie',
						data : [],
						center : [ 500, 10 ],
						size : 80,
						showInLegend : false,
						dataLabels : {
							enabled : false,
									},
					}, {

						type : 'column',
						name : 'BugZilla',
						data : [],
						color : '#F64A16',
						pointWidth : 28,

					}, {
						type : 'column',
						name : 'RedMind',
						data : [],
						color : '#0ECDFD',
						pointWidth : 28,
					}, ]

				}, function getdata(chart) {
					var pid=document.getElementById("id");
					var id = pid.options[pid.selectedIndex].value;
					var timeStamp=document.getElementById("list");
					var st= timeStamp.options[timeStamp.selectedIndex].value;
					
					$.ajax({
						
						url : "http://localhost:8080/PRM/GraphServlet?id=" + id +"&timeStamp=" + st,
						dataType : 'json',
						error : function() {
							alert("error occured!!!");
						},
						success : function(data) {
							var chartData = [];
							var arrayBugzilla = [];
							var arrayRedmind = [];
							var arrayTimeStamp=[];
							$.each(data.jsonArray, function(index) {
								$.each(data.jsonArray[index], function(key,
										value) {
									var point = [];

									if (key == "Bugzilla") {
										point.push(key);
										point.push(value);
										chartData.push(point);
										
									} else if (key == "Redmind") {
										point.push(key);
										point.push(value);
										chartData.push(point);
										
									} else if (key == "arrayBugzilla") {
										
									for(var i=0;i<value.length;i++){
											
											arrayBugzilla.push(value[i]);
											
										}
										
										
										
										//alert(value[0]);
									} else if (key == "arrayRedmind") {
									
										for(var i=0;i<value.length;i++){
											
											arrayRedmind.push(value[i]);
										}
									
										
									}  else if (key == "arrayTimeStamp") {
									
										for(var i=0;i<value.length;i++){
											
											arrayTimeStamp.push(value[i]);
										}
									}else {

										alert("Nothing");
									}

								});
							});
							
							chart.series[0].setData(chartData);
							chart.series[1].setData(arrayBugzilla);
							chart.series[2].setData(arrayRedmind);
							chart.xAxis[0].setCategories(arrayTimeStamp);
							//chart.series[1].setData([ 30, 45, 96, 38, 54 ]);
							//chart.series[2].setData([ 12, 33, 51, 37, 46 ]);
						}

					});

				});

			}