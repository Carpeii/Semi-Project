<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>header.jsp</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
    <style>
        /* Optional: Custom styles for specific elements */
        .logo img {
            max-height: 50px; /* Adjust logo height as needed */
        }
        .navbar .nav-link {
            padding: .5rem 1rem; /* Adjust padding for nav links */
        }
        .navbar .navbar-text {
            margin-right: 1rem; /* Adjust margin for navbar text */
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/main.jsp">
            <img src="${pageContext.request.contextPath}/image/logo.jpg" alt="로고 이미지" class="img-fluid">
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">메뉴1</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../index.jsp">메인 페이지</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../user/profile.jsp">마이페이지</a>
                </li>

                <c:choose>
                    <c:when test="${user != null}">
                        <li class="nav-item">
                            <p class="navbar-text">${user.name}님 환영합니다</p>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/myPage">마이 페이지</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/hostMain">호스트 페이지로</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login">로그인</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/join">회원가입</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

</body>
</html>
