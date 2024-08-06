<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
 
<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>호스트 메인</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .hero-section {
            background: url('${pageContext.request.contextPath}/images/hero-bg.jpg') no-repeat center center;
            background-size: cover;
            padding: 4rem 0;
            color: #fff;
        }
        .hero-title {
            font-size: 2.5rem;
            font-weight: bold;
            color: #0d6efd; /* 변경된 텍스트 색상 */
        }
        .search-form {
            max-width: 600px;
            margin: 0 auto;
        }
        .card {
            transition: transform 0.3s;
        }
        .card:hover {
            transform: scale(1.05);
        }
        .pagination .page-item.active .page-link {
            background-color: #0d6efd;
            border-color: #0d6efd;
        }
        .pagination .page-link {
            border-radius: 0.375rem;
        }
    </style>
</head>
<body>
 
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
 
<div class="hero-section text-center">
    <h1 class="hero-title">단기임대, 1평에서 찾아보세요</h1>
</div>

<div class="container my-5">
    <!-- 검색 폼 -->
    <div class="d-flex justify-content-center mb-5">
        <form action="${pageContext.request.contextPath}/host/search" method="get" class="search-form">
            <div class="input-group">
                <input type="search" name="searchWord" class="form-control" placeholder="지역 또는 건물명을 입력하세요" aria-label="Search">
                <button class="btn btn-outline-info" type="submit">검색</button>
            </div>
        </form>
    </div>

    <!-- 카드 뷰로 방 목록 표시 -->
    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
        <c:forEach var="room" items="${roomList}">
            <div class="col">
                <div class="card shadow-sm border-light rounded-3">
                    <!-- 이미지를 클릭했을 때 이동하는 페이지 -->
                    <a href="${pageContext.request.contextPath}/service/roomDetail?roomId=${room.id}">
                        <img src="${room.imagePath}" class="card-img-top" alt="${room.roomName}">
                    </a>
                    <div class="card-body">
                        <h5 class="card-title">${room.roomName}</h5>
                        <p class="card-text">${room.streetAddress}</p>
                        <p class="card-text">${room.roomName}</p>
                        <p class="card-text">${room.rentPrice}</p>
                        <p class="card-text"><small class="text-muted">방: 1 | 화장실: 1 | 거실: 0 | 주방: 0</small></p>
                    </div>
                    <div class="card-footer text-center">
                        <small class="text-primary">장기계약 시 최대 60% 할인</small>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- 페이지 네이션 -->
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center mt-4">
            <c:if test="${startPage > 1}">
                <li class="page-item">
                    <a href="${pageContext.request.contextPath}/service/guestMain?pageNum=${startPage - 1}" class="page-link" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:forEach var="num" begin="${startPage}" end="${endPage}">
                <li class="page-item <c:if test="${pageNum == num}">active</c:if>">
                    <a href="${pageContext.request.contextPath}/service/guestMain?pageNum=${num}" class="page-link">${num}</a>
                </li>
            </c:forEach>
            <c:if test="${endPage < pageCount}">
                <li class="page-item">
                    <a href="${pageContext.request.contextPath}/service/guestMain?pageNum=${endPage + 1}" class="page-link" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
 
</div>

<jsp:include page="/jsp/common/footer.jsp"></jsp:include>
 
</body>
</html>