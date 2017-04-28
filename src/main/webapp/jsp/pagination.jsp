<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
		<%--For displaying Previous link except for the 1st page --%>
	<div style="100%">
	<c:if test="${currentPage != 1}">
		<a href="<%= request.getContextPath() %>${articleResult}${currentPage - 1}">Previous</a>
	</c:if>

	<c:forEach begin="1" end="${noOfPages}" var="i">
              <c:choose>
                  <c:when test="${currentPage eq i}">
                      ${i}
                  </c:when>
                  <c:otherwise>
                      <a href="<%= request.getContextPath() %>${articleResult}${i}">${i}</a>
                  </c:otherwise>
              </c:choose>
          </c:forEach>

	 <%--For displaying Next link --%>
	<c:if test="${currentPage lt noOfPages}">
		<a href="<%= request.getContextPath() %>${articleResult}${currentPage + 1}">Next</a>
	</c:if>