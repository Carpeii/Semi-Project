<%@ page import="com.mywebapp.dto.UserDto" %><%--
  Created by IntelliJ IDEA.
  User: now
  Date: 2024. 7. 21.
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>adminMain.jsp</title>
</head>
<body>
<h2>단기임대, 1평에서 찾아보세요</h2>
<h3>관리자 페이지</h3>
<%
    UserDto user = (session != null) ? (UserDto) session.getAttribute("user") : null;
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
<table>
    <tr>
        <!-- 매물 승인 요청 후 대기 매물 리스트 -->
        <!-- 매물 리스트 해당 클릭하면 해당 매물 상태 관리하는 페이지 나오도록 -->
        <td><button onclick="location.href='./roomEdit.jsp'">edit</button></td>
    </tr>
</table>
</body>
</html>
