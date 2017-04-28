<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    
<h3>Featured Posts</h3>
<ul>
	<c:forEach var="featuredArticle" items="${featuredArticle}">
		<li>
		<a class="wpp-post-title" title="${featuredArticle.title}" href="${featuredArticle.anchor}">
			${featuredArticle.title}
		</a>
		<span class="post-stats"></span>
		</li>
	</c:forEach>
</ul>