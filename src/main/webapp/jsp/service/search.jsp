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
<%@include file="/jsp/common/header.jsp" %>

<!-- START ALBUM -->
			<!--   해당 태그를 행으로 정의                mt-5 위쪽 마진  , mx-5 좌우 x축 마진 -->
      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-5 mt-5 mx-5">
      <!-- Attribute로 가져온 list rooms -->
 <c:if test="${not empty rooms}">
<c:forEach var="room" items="${requestScope.rooms}">
<!-- rooms가 비어있지 않으면 -->
        <!-- room의 멤버 roomImageList가 비어있지 않다면 -->
        <c:if test="${not empty room.roomImageList}">
       <!-- room을 참조해서 룸의 맴버 roomImage리스트를 참조하는 변수 image선언 -->
            <c:set var="image" value="${room.roomImageList[0]}" />
        </c:if>
      <!-- 앨범 한 개 시작 -->
        <div class="col-sm">
        <div class="card shadow-sm">
          <!-- 이미지를 눌렀을 때 이동하는 페이지 -->
          <a href="해당 페이지">
          <img class="bd-placeholder-img card-img-top" width="100%" height="225" src="${pageContext.request.contextPath}/${image.imagePath}" alt="${ image.imageName}">
          </a>
            <div class="card-body">
            <p class="card-title">${room.roomName}</p>
              <p class="overflow-y-hidden">${room.jibunAddress}</p>
            <p class="card-title">${room.roomPrice.rentPrice}원/1주일</p>
              <div class="d-flex justify-content-between align-items-center">
             </div>
                <small class="text-body-secondary">방 ${room.roomCount}</small>
                <small class="text-body-secondary">화장실 ${room.toiletCount}</small>
                <small class="text-body-secondary">거실 ${room.livingRoomCount}</small>
                <small class="text-body-secondary">주방 ${room.kitchenCount}</small>
              </div>
                <div class="card-footer">
                <c:set var="discount" value="${room.roomPrice.longTermDiscount + room.roomPrice.earlyCheckInDiscount}"/>
                <c:if test="${discount > 0 }">
        <small class="text-primary">장기계약 시 최대 ${discount }%할인</small>
                </c:if>
      </div>
          </div>
        </div>
        <!-- 앨범 한 개 끝 -->
</c:forEach>
 </c:if>
  </div>
longTermDiscount;
earlyCheckIn;
earlyCheckInDiscount;
<!-- END ALBUM -->
<!--  <div class="album py-5 bg-body-tertiary"> -->
<!--     <div class="container"> -->
<!--       <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3"> -->
<!-- </main> -->
 <!-- footer -->
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
 <%@include file="/jsp/common/footer.jsp" %>
</body>
</html>