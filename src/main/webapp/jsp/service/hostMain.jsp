<%@ page import="com.mywebapp.dto.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hostMain.jsp</title>
</head>
<body>
<h2>단기임대, 1평에서 찾아보세요</h2>
<%
    UserDto user = (UserDto) session.getAttribute("user");
%>
<%-- Check if the user is logged in --%>
<% if (user != null) { %>
    <p>${user.name}님 환영합니다</p>
    <button onclick="location.href='${pageContext.request.contextPath}/logout'">로그아웃</button>
    <button onclick="location.href='${pageContext.request.contextPath}/myPage'">마이 페이지</button>
    <button onclick="location.href='${pageContext.request.contextPath}/service/guestMain'">게스트 페이지로</button>
    <button onclick="location.href='${pageContext.request.contextPath}/service/roomAdd'">방추가</button>
    <button onclick="location.href='${pageContext.request.contextPath}/roomAdd.jsp'">방관리</button>
<% } else { %>
    <button onclick="location.href='${pageContext.request.contextPath}/login'">로그인</button>
    <button onclick="location.href='${pageContext.request.contextPath}/join'">회원가입</button>
<% } %>
</body>
</html>
