<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>header.jsp</title>
<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.3/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap JS -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
<style>
/* Custom styles for navbar */
.navbar {
	background-color: #f8f9fa; /* Light background for navbar */
}

.navbar-brand img {
	max-height: 60px; /* Adjust logo height */
}

.nav-link {
	color: #007bff !important; /* Blue color for nav links */
	font-weight: 500; /* Slightly bold */
}

.nav-link:hover {
	color: #f8f9fa !important; /* Light color on hover */
	background-color: #007bff; /* Blue background on hover */
	border-radius: 0.25rem; /* Rounded corners on hover */
}

.navbar-text {
	color: #007bff; /* Blue color for navbar text */
	font-weight: 500; /* Slightly bold */
}

.navbar-toggler-icon {
	background-image: url('data:image/svg+xml;base64,...');
	/* Custom icon (optional) */
}

.navbar-nav .nav-item+.nav-item {
	margin-left: 1rem; /* Spacing between nav items */
}

.custom-outline-btn {
	border-color: #007bff; /* 테두리 색상: 기본 색상 */
	color: #007bff; /* 텍스트 색상 */
	padding: 0.5rem 1rem; /* 버튼 패딩 */
	font-size: 1rem; /* 버튼 텍스트 크기 */
}

.custom-outline-btn:hover {
	background-color: #007bff; /* 호버 시 배경 색상 */
	color: #ffffff; /* 호버 시 텍스트 색상 */
}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/main.jsp"> <img
				src="${pageContext.request.contextPath}/image/logo.jpg" alt="로고 이미지"
				class="img-fluid">
			</a>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">

					<c:choose>
						<c:when test="${user != null}">
							<c:choose>
								<c:when test="${user.memberType == 0}">
									<li class="nav-item">
										<p class="navbar-text">게스트 ${user.name}님 환영합니다</p>
									</li>
									<li class="nav-item"><a class="nav-link"
										href="${pageContext.request.contextPath}/auth/logout">로그아웃</a>
									</li>
									<li class="nav-item"><a class="nav-link"
										href="${pageContext.request.contextPath}/user/myPage">마이
											페이지</a></li>
								</c:when>
								<c:when test="${user.memberType == 1}">
									<li class="nav-item">
										<p class="navbar-text">호스트 ${user.name}님 환영합니다</p>
									</li>
									<li class="nav-item"><a class="nav-link"
										href="${pageContext.request.contextPath}/auth/logout">로그아웃</a>
									</li>
									<li class="nav-item"><a class="nav-link"
										href="${pageContext.request.contextPath}/user/myPage">마이
											페이지</a></li>
									<li class="nav-item"><a class="nav-link"
										href="${pageContext.request.contextPath}/service/hostRoomList">방
											관리</a></li>
									<li class="nav-item"><a class="nav-link"
										href="${pageContext.request.contextPath}/service/contractAdd">계약
											추가</a></li>
								</c:when>
							</c:choose>


						</c:when>
						<c:otherwise>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/auth/login">로그인</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/auth/join">회원가입</a></li>

						</c:otherwise>
					</c:choose>

					<!-- 현재 페이지에 따른 버튼 렌더링 -->
					<c:choose>
						<c:when test="${pageType == 'guestMain'}">
							<li class="nav-item"><a
								class="btn custom-outline-btn btn-lg rounded-3"
								href="${pageContext.request.contextPath}/hostMain">호스트 페이지로</a>
							</li>
						</c:when>
						<c:when test="${pageType == 'hostMain'}">
							<li class="nav-item"><a
								class="btn custom-outline-btn btn-lg rounded-3"
								href="${pageContext.request.contextPath}/guestMain">게스트 페이지로</a>
							</li>
						</c:when>
					</c:choose>



				</ul>
			</div>
		</div>
	</nav>

</body>
</html>
