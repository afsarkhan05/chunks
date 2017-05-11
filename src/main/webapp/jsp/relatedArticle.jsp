<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<h3>Related Posts</h3>
	<ul>
	<c:forEach var="relatedArticle" items="${relatedArticle}">
		<li>
		<a class="wpp-post-title" title="${relatedArticle.title}" href="${relatedArticle.anchor}">
			${relatedArticle.title}
		</a>
		<span class="post-stats"></span>
		</li>
	</c:forEach>
	</ul>