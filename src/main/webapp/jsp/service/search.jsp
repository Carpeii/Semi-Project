    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
  <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
</head>
<body>
<!-- 
 검색된 결과들 보이기 
asdasdsadas
 결과중 하나 선택 
<button onclick="location.href='../service/detail.jsp'">이태원루프탑 STAY</button>
 -->
 <p>Total Page: ${totalpage}</p>
<p>Page Number: ${pagenum}</p>
<p>searchWord: ${searchWord}</p>
 <table>
 <c:forEach var="room" items="${requestScope.rooms}">
			<tr>
			<td>방 주소 : ${room.jibunAddress }....${room.roomName}....${room.roomPrice.rentPrice}...............${pagenum}...</td> 
			</tr>
			<td>
			<div class="col-md">
         <div class="card shadow-sm">
          <a href="해당 페이지">
        <c:if test="${not empty rooms}">
        <c:set var="firstRoom" value="${rooms[0]}" />
        <c:if test="${not empty firstRoom.roomImageList}">
            <c:set var="firstImage" value="${firstRoom.roomImageList[0]}" />
            <div>
                <p>First Image Path: ${pageContext.request.contextPath}/upload/img.jpg</p>
                <img src="${pageContext.request.contextPath}/upload/img.jpg" alt="Image" />
            </div>
        </c:if>
    </c:if>
          </a>
          </div>
        </div>
			</td>
		 </c:forEach>
		 
		 <tr>
		 <c:if test="${pagenum > 1 }">
		 <td>
 <a href="${pageContext.request.contextPath }/host/search?searchWord=${searchWord}&pageNum=${pagenum-1}">이전페이지</a>
		 </td>
		 </c:if>
		 <c:if test="${totalpage > pagenum }">
		 <td>
 <a href="${pageContext.request.contextPath }/host/search?searchWord=${searchWord}&pageNum=${pagenum+1}">다음페이지</a>
		 </td>
		 </c:if>
 </table>
 
<nav aria-label="Page navigation example">
  <ul class="pagination">
    <li class="page-item">
	 <c:choose>
	 <c:when test="${pagenum > 5 }">
      <a class="page-link" href="${pageContext.request.contextPath }/host/search?searchWord=${searchWord}&pageNum=${pagenum-5}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
 	</c:when>
 	<c:otherwise>
      <a class="page-link" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
 	</c:otherwise>
 </c:choose>
    </li>
    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/host/search?searchWord=${searchWord}&pageNum=${pagenum}">${pagenum}</a></li>
    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/host/search?searchWord=${searchWord}&pageNum=${pagenum+1}">${pagenum+1}</a></li>
    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/host/search?searchWord=${searchWord}&pageNum=${pagenum+2}">${pagenum+2}</a></li>
    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/host/search?searchWord=${searchWord}&pageNum=${pagenum+3}">${pagenum+3}</a></li>
    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath }/host/search?searchWord=${searchWord}&pageNum=${pagenum+4}">${pagenum+4}</a></li>
    <li class="page-item">
     <c:choose>
	 <c:when test="${5+pagenum < totalpage}">
      <a class="page-link" href="${pageContext.request.contextPath }/host/search?searchWord=${searchWord}&pageNum=${pagenum+5}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
 	</c:when>
 	<c:otherwise>
     <a class="page-link" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
 	</c:otherwise>
 </c:choose>
    </li>
  </ul>
</nav>

</body>
</html>