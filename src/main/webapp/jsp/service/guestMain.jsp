<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
 
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>1평 - 단기임대, 1평에서 찾아보세요</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/guestMain.css"/>
</head>
<body>


<jsp:include page="/jsp/common/header.jsp"></jsp:include>

<%-- 
<c:import url="/jsp/common/header.jsp" />
--%>
 
<div class="hero-section text-center">
    <h1 class="hero-title">단기임대, 1평에서 찾아보세요</h1>
</div>

<div class="container my-5">
    <!-- 검색 폼 -->
   <div class="container d-flex justify-content-center m-5">
        <form action="${pageContext.request.contextPath}/host/search" id="searchForm" name="searchForm" method="get" class="col-12 col-lg-auto mb-3 mb-lg-0 w-75 h- d-flex justify-content-center" role="search">
            <input type="search" id="searchInput" name="searchWord" class="form-control w-75 h-70" placeholder="지역 또는 건물명을 입력 하세요" aria-label="Search">
        <button type="submit" class="btn btn-outline-info btn-lg px-3 rounded-pill mt-3 w-25 ms-5" id="searchButton">
            검색
        </button>
        </form>
</div> 

    <!-- 카드 뷰로 방 목록 표시 -->
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
        <c:forEach var="room" items="${roomList}">
            <div class="col">
            	 <!-- 카드 전체를 링크로 감쌉니다 -->
            	 <a href="${pageContext.request.contextPath}/service/roomDetail?roomId=${room.id}" class="card-link">
	                <div class="card shadow-sm border-light rounded-3">
                        <img src="${pageContext.request.contextPath}${room.imagePath}" class="card-img-top" alt="${room.roomName}">
                    <div class="card-body">
                        <h5 class="card-title">${room.roomName}</h5>
                        <p class="card-text">${room.streetAddress}</p>
                        <p class="card-text">
                            <span class="rent-price">${room.rentPrice}</span>
                            <span class="rent-info">원/1주</span>
                        </p>
                    </div>
                    <div class="card-footer text-center">
                        <small class="text-primary">장기계약 시 최대 60% 할인</small>
                    </div>
                </div>
	            </a>
            </div>
        </c:forEach>
    </div>

    <!-- 페이지 네이션 -->
<!--     <nav aria-label="Page navigation"> -->
<!--         <ul class="pagination justify-content-center mt-4"> -->
<%--             <c:if test="${startPage > 1}"> --%>
<!--                 <li class="page-item"> -->
<%--                     <a href="${pageContext.request.contextPath}/guestMain?pageNum=${startPage - 1}" class="page-link" aria-label="Previous"> --%>
<!--                         <span aria-hidden="true">&laquo;</span> -->
<!--                     </a> -->
<!--                 </li> -->
<%--             </c:if> --%>
<%--             <c:forEach var="num" begin="${startPage}" end="${endPage}"> --%>
<%--                 <li class="page-item <c:if test="${pageNum == num}">active</c:if>"> --%>
<%--                     <a href="${pageContext.request.contextPath}/guestMain?pageNum=${num}" class="page-link">${num}</a> --%>
<!--                 </li> -->
<%--             </c:forEach> --%>
<%--             <c:if test="${endPage < pageCount}"> --%>
<!--                 <li class="page-item"> -->
<%--                     <a href="${pageContext.request.contextPath}/guestMain?pageNum=${endPage + 1}" class="page-link" aria-label="Next"> --%>
<!--                         <span aria-hidden="true">&raquo;</span> -->
<!--                     </a> -->
<!--                 </li> -->
<%--             </c:if> --%>
<!--         </ul> -->
<!--     </nav> -->
 
</div>

<jsp:include page="/jsp/common/footer.jsp"></jsp:include>
 
</body>
<script type="text/javascript">
    window.onload = function() {
      document.getElementById('searchForm').addEventListener('submit', function(event) {
        let data = document.getElementById('searchInput').value.trim();

        if (data === "") {
          event.preventDefault(); 
        } else if (data.length < 2) {
          alert('두 자 이상 입력해주세요.');
          event.preventDefault();
        }
      });
    }
  </script>
</html>