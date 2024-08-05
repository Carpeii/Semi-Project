<%@ page import="com.mywebapp.dto.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>hostMain.jsp</title>
</head>
<body>
<h2>단기임대, 1평에서 찾아보세요</h2>
<%
    UserDto user = (session != null) ? (UserDto) session.getAttribute("user") : null;
%>
<%
    if (user != null) {
        System.out.println(user);
        // 로그인 정보 존재
%>
<p>${sessionScope.user.userId}님 환영합니다</p>
<button onclick="location.href='${pageContext.request.contextPath}/logout'">로그아웃</button>
<button onclick="location.href='${pageContext.request.contextPath}/myPage'">마이 페이지</button>
<button onclick="location.href='${pageContext.request.contextPath}/guestMain'">게스트 페이지로</button>
<button onclick="location.href='${pageContext.request.contextPath}/jsp/auth/roomAdd.jsp'">방관리</button>
<button onclick="location.href='${pageContext.request.contextPath}/jsp/auth/roomAdd.jsp'">계약</button>
<button onclick="location.href='${pageContext.request.contextPath}/jsp/auth/roomAdd.jsp'">채팅</button>
<button onclick="location.href='${pageContext.request.contextPath}/jsp/auth/roomAdd.jsp'">정산</button>
<button onclick="location.href='${pageContext.request.contextPath}/jsp/auth/roomAdd.jsp'">더보기</button>
<br><br>
<button onclick="location.href='${pageContext.request.contextPath}/jsp/auth/roomAdd.jsp'">방 등록하기</button>
<%--        <p>${userId}님 환영합니다</p>--%>
<%
} else if (user == null) {
    System.out.println(user);
    // 로그인 정보 없음
%>
<button onclick="location.href='${pageContext.request.contextPath}/login'">로그인</button>
<button onclick="location.href='${pageContext.request.contextPath}/join'">회원가입</button>
<%
    }
%>


</body>
</html>