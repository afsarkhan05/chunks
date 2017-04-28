<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<h3>Latest Posts</h3>
<ul>
	<c:forEach var="latestArticle" items="${latestArticle}">
		<li>
		<a class="wpp-post-title" title="${latestArticle.title}" href="${latestArticle.anchor}">
			${latestArticle.title}
		</a>
		<span class="post-stats"></span>
		</li>
	</c:forEach>
</ul>