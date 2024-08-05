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
		 
		
 </table>
          <!-- --------------------------------------------------------페이징----------------------------------------------------- -->
          <!-- --------------------------------------------------------페이징----------------------------------------------------- -->
          <!-- --------------------------------------------------------페이징----------------------------------------------------- -->
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<li class="page-item"><c:choose>
					<c:when test="${startBlock > 5 }">
						<a class="page-link"
							href="${pageContext.request.contextPath }/host/search?searchWord=${searchWord}&pageNum=${endBlock - blockPerPage}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a>
					</c:when>
					<c:otherwise>
						<a class="page-link text-secondary" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
						</a>
					</c:otherwise>
				</c:choose></li>
			<c:forEach var="page" begin="${startBlock}" end="${endBlock}">
				<c:if test="${page == pageNum}">
					<li class="page-item"><a class="page-link">[${page}]</a></li>
				</c:if>
				<!--  endBlock -> 9 , totalPage 7 ,  -->
			<c:if test="${page <=  totalPage }">
				<c:if test="${page != pageNum}">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath }/host/search?searchWord=${searchWord}&pageNum=${page}">${page}</a></li>
				</c:if>
			</c:if>
			</c:forEach>

			<li class="page-item"><c:choose>
					<c:when test="${endBlock < totalPage}">
						<a class="page-link"
							href="${pageContext.request.contextPath }/host/search?searchWord=${searchWord}&pageNum=${startBlock + blockPerPage}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a>
					</c:when>
					<c:otherwise>
						<a class="page-link text-secondary" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
						</a>
					</c:otherwise>
				</c:choose></li>
		</ul>
	</nav>

</body>
</html>