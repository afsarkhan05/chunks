<!DOCTYPE html>
<html xmlns='http://www.w3.org/1999/xhtml'>
<head>
<title>Test SyntaxHighlighter - Java Example</title>
<meta charset='UTF-8'>
</head>
<body class='no_sponsor'>
<div id='title'>
<h1>
Test
</h1>
<div id='blurb'>
<em>Test is an open source Java Script client side code syntax highlighter.</em>
</div>
<h2>S Java Example</h2>
</div>
<div id='content'>
<pre class='brush: js'>
      package tutorial;
      import com.opensymphony.xwork2.ActionSupport;
      public class HelloWorld extends ActionSupport {
      	private String name;
      	public String getName() {
      	return name;
      	}
      	public void setName(String name) {
      		this.name = name;
      	}
      	public String execute() {
      		name = "Hello, " + name + "!";
      		return SUCCESS;
      	}
      }
      </pre>
</div>
<div id='clear'></div>
</div>
  </body>
  <link href="<%= request.getContextPath() %>/css/sh/shCore.css" rel='stylesheet' type='text/css'>
  <link href="<%= request.getContextPath() %>/css/sh/shThemeDefault.css" rel='stylesheet' type='text/css'>
  <script src="<%= request.getContextPath() %>/js/sh/shCore.js" type='text/javascript'></script>
  <script src="<%= request.getContextPath() %>/js/sh/shBrush.js" type='text/javascript'></script>
<!-- Finally, to actually run the highlighter, you need to include this JS on your page -->
<script type="text/javascript">
     SyntaxHighlighter.all()
</script>
</html>
