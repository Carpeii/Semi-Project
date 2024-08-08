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
 <div class="container d-flex justify-content-center align-items-center">
 <div class="container d-flex justify-content-center m-5 w-100">
        <form action="${pageContext.request.contextPath}/host/search" name="searchForm" method="get" class="col-12 col-lg-auto mb-3 mb-lg-0 w-100 h- d-flex justify-content-center" role="search">
            <input type="search" name="searchWord" class="form-control w-100 h-70" placeholder="지역 또는 건물명을 입력 하세요" aria-label="Search">
        <button class="btn btn-outline-info btn-lg px-3 rounded-pill mt-3 w-25 ms-5" id="searchButton" type="submit">
            검색
        </button>
        </form>
</div>
 </div>
 <c:if test="${empty rooms}">
<div class="text-center" style="font-size:2vw;">
<p>검색 결과가 없습니다.</p>
<svg xmlns="http://www.w3.org/2000/svg" width="80" height="80" fill="currentColor" class="bi bi-emoji-frown" viewBox="0 0 16 16">
  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"></path>
  <path d="M4.285 12.433a.5.5 0 0 0 .683-.183A3.5 3.5 0 0 1 8 10.5c1.295 0 2.426.703 3.032 1.75a.5.5 0 0 0 .866-.5A4.5 4.5 0 0 0 8 9.5a4.5 4.5 0 0 0-3.898 2.25.5.5 0 0 0 .183.683M7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5m4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5"></path>
</svg>
</div>
</c:if>
<!-- START ALBUM -->
			<!--   해당 태그를 행으로 정의                mt-5 위쪽 마진  , mx-5 좌우 x축 마진 -->
      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-5 mt-5 mx-5">
      <!-- Attribute로 가져온 list rooms -->
 <c:if test="${not empty rooms}">
    <c:forEach var="room" items="${requestScope.rooms}">
        <!-- room의 멤버 roomImageList가 비어있지 않다면 -->
        <c:if test="${not empty room.roomImageList}">
            <!-- room을 참조해서 룸의 맴버 roomImage리스트를 참조하는 변수 image선언 -->
            <c:set var="image" value="${room.roomImageList[0]}" />
        </c:if>
        <!-- 앨범 한 개 시작 -->
        <div class="col-sm">
            <a href="${pageContext.request.contextPath}/service/roomDetail?roomId=${room.id}" class="card shadow-sm" style="text-decoration: none; color: inherit;">
                <img class="bd-placeholder-img card-img-top" width="75%" height="200" 
                     src="${pageContext.request.contextPath}/${image.imagePath}" alt="${image.imageName}">
                <div class="card-body">
                    <p class="card-title">${room.roomName}</p>
                    <p class="overflow-y-hidden">${room.jibunAddress}</p>
                    <p class="card-title">${room.roomPrice.rentPrice}원/1주일</p>
                    <small class="text-body-secondary">방 ${room.roomCount}</small>
                    <small class="text-body-secondary">화장실 ${room.toiletCount}</small>
                    <small class="text-body-secondary">거실 ${room.livingRoomCount}</small>
                    <small class="text-body-secondary">주방 ${room.kitchenCount}</small>
                </div>
                <div class="card-footer">
                        <p>
                    <c:if test="${room.roomPrice.longTermDiscount > 0}">
                        <span class="text-primary">장기계약 시 최대 ${room.roomPrice.longTermDiscount}% 할인</span>
                    </c:if>
                        </p>
                        <p>
                       <c:if test="${room.roomPrice.earlyCheckInDiscount > 0} ">
                        <span class="text-primary">장기계약 시 최대 ${room.roomPrice.earlyCheckInDiscount}% 할인</span>
                    </c:if>
                        </p>
                </div>
            </a>
        </div>
        <!-- 앨범 한 개 끝 -->
    </c:forEach>
</c:if>
  </div>
  
  <!-- 
longTermDiscount;
earlyCheckIn;
earlyCheckInDiscount;
   -->
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
 <jsp:include page="/jsp/common/footer.jsp"></jsp:include>
 
</body>
</html>