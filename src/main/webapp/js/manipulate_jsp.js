

function switchPages(){
	
	setTimeout(pageOne, 3000);
	
	/*setInterval(pageOne, 10000);
	setInterval(pageSecond, 20000);*/
	
}

function pageOne(){
	
	var path='graph.do?method=getGraph';
	alert('calling ' +path);
	window.location.href = path;
	setTimeout(pageSecond, 3000);
	
}





