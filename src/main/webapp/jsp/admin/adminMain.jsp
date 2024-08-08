<%@ page import="com.mywebapp.dto.MemberDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>adminMain.jsp</title>
</head>
<body>
<h2>단기임대, 1평에서 찾아보세요</h2>
<h3>관리자 페이지</h3>
<%
    MemberDto user = (session != null) ? (MemberDto) session.getAttribute("user") : null;
%>
<%
    if (user != null) {
        System.out.println(user);
        // 로그인 정보 존재
%>
<p>관리자 ${sessionScope.user.userId}님 환영합니다</p>

<button onclick="location.href='${pageContext.request.contextPath}/admin/roomManagement'">방관리</button>
<button onclick="location.href='../auth/logout.jsp'">로그아웃</button>
<%--<button onclick="location.href='../auth/myPage.jsp'">마이 페이지</button>--%>
<%--        <p>${userId}님 환영합니다</p>--%>
<%
} else if (user == null) {
    System.out.println(user);
    // 로그인 정보 없음
%>
<button onclick="location.href='../admin/adminJoin.jsp'">관리자 계정 등록</button>
<button onclick="location.href='../auth/loginMain.jsp.jsp'">관리자 로그인</button>
<%
    }
%>

</body>
</html>
