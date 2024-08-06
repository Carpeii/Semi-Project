<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
 
<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>게스트 메인</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
    <style>
        .body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .hero-section {
            background: url('${pageContext.request.contextPath}/images/hero-bg.jpg') no-repeat center center;
            background-size: cover;
            padding: 4rem 0;
            color: #fff;
            text-align: center;
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
            border: 1px solid #ddd;
            border-radius: 1rem;
            overflow: hidden;
            transition: transform 0.3s, box-shadow 0.3s;
        }
        .card:hover {
            transform: scale(1.05);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }
        .card-img-top {
            object-fit: cover;
            height: 200px; /* 이미지 높이 조정 */
        }
        .card-body {
            padding: 1.5rem;
        }
        .card-title {
            font-size: 1.25rem;
            font-weight: bold;
        }
        .card-text {
            color: #6c757d; /* 회색 텍스트 색상 */
        }
        .rent-price {
            font-size: 1.5rem; /* 텍스트 크기 */
            font-weight: bold; /* 볼드체 */
            color: #000; /* 검정색 텍스트 */
        }
        .rent-info {
            font-size: 1rem; /* 기본 텍스트 크기 */
            color: #6c757d; /* 회색 텍스트 색상 */
        }
        .card-footer {
            background-color: #f1f1f1;
            border-top: 1px solid #e9ecef;
            padding: 1rem;
        }
        .pagination .page-item.active .page-link {
            background-color: #0d6efd;
            border-color: #0d6efd;
        }
        .pagination .page-link {
            border-radius: 0.375rem;
        }
        
        /* Custom styles for search button */
        
        .btn-custom {
            background-color: #007bff; /* 파란색 배경 */
            color: #ffffff; /* 흰색 글씨 */
            border: none; /* 기본 테두리 제거 */
            border-radius: 0.375rem; /* 모서리 둥글게 */
            font-weight: 500; /* 약간의 굵기 */
            transition: background-color 0.3s ease, color 0.3s ease; /* 부드러운 전환 효과 */
        }
        .btn-custom:hover {
            background-color: #0056b3; /* 호버 시 더 어두운 파란색 */
            color: #ffffff; /* 호버 시 흰색 글씨 유지 */
        }
        .btn-custom:focus, .btn-custom:active {
            box-shadow: none; /* 포커스 및 활성 상태에서 그림자 제거 */
        }
        
        .card-link {
		    text-decoration: none; /* 링크 밑줄 제거 */
		    color: inherit; /* 링크 텍스트 색상 유지 (부모 요소의 색상 사용) */
		}

		.card-link:hover {
		    text-decoration: none; /* 호버 시에도 밑줄 제거 */
		}
    </style>
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
    <div class="d-flex justify-content-center mb-5">
        <form action="${pageContext.request.contextPath}/host/search" method="get" class="search-form">
            <div class="input-group">
                <input type="search" name="searchWord" class="form-control" placeholder="지역 또는 건물명을 입력하세요" aria-label="Search">
                <button class="btn btn-custom" type="submit">검색</button>
            </div>
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
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center mt-4">
            <c:if test="${startPage > 1}">
                <li class="page-item">
                    <a href="${pageContext.request.contextPath}/guestMain?pageNum=${startPage - 1}" class="page-link" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:forEach var="num" begin="${startPage}" end="${endPage}">
                <li class="page-item <c:if test="${pageNum == num}">active</c:if>">
                    <a href="${pageContext.request.contextPath}/guestMain?pageNum=${num}" class="page-link">${num}</a>
                </li>
            </c:forEach>
            <c:if test="${endPage < pageCount}">
                <li class="page-item">
                    <a href="${pageContext.request.contextPath}/guestMain?pageNum=${endPage + 1}" class="page-link" aria-label="Next">
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