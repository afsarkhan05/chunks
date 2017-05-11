<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<h3>Latest Posts</h3>
<ul>
	<c:forEach var="articleResult" items="${articleResultList}">
		<li>
		<a class="wpp-post-title" title="${articleResult.title}" href="${articleResult.anchor}">
			${articleResult.title}
		</a>
		<span class="post-stats"></span>
		</li>
	</c:forEach>

</ul>