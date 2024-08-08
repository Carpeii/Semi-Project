<%@ page import="com.mywebapp.dto.MemberDto" %><%--
  Created by IntelliJ IDEA.
  User: now
  Date: 2024. 7. 29.
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>1평 단기임대, 한달살기 부동산 단기계약 플랫폼</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<div class="container">
<main>
    <div class="py-5 text-center">
        <h2>마이 페이지</h2>
        <p class="lead">
            호스트로 회원가입을 원하시는 경우 아래의 기본정보와 추가 입력 폼을 작성하셔야합니다.
        </p>
    </div>
    <div class="col-md-8 col-lg-8">
    <form action="${pageContext.request.contextPath}/user/myPage" method="post">
        <%
            MemberDto user = (MemberDto) session.getAttribute("user");
        %>
        <%
            if(user.getMemberType() == 1) { // 호스트
        %>
        <div>
            <button class="w-100 btn btn-primary btn-lg" type="submit" name="action" value="profileEdit">나의 기본정보관리</button>
        </div>
            <div><br></div>
        <div>
            <button class="w-100 btn btn-primary btn-lg" type="submit" name="action" value="hostEdit">나의 추가 정보관리</button>
        </div>
        <%
            } else if(user.getMemberType() == 0) { // 게스트
        %>
        <div>
            <button class="w-100 btn btn-primary btn-lg" type="submit" name="action" value="guestBooking">나의 계약관리</button>
        </div>
            <div><br></div>
        <div>
            <button class="w-100 btn btn-primary btn-lg" type="submit" name="action" value="guestEdit">나의 정보관리</button>
        </div>
        <%
            }
        %>
</form>
</main>
</body>
</html>
