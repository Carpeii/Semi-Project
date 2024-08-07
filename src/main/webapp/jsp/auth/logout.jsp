<%@ page import="com.mywebapp.dto.MemberDto" %><%--
  Created by IntelliJ IDEA.
  User: kimjiwoong
  Date: 2024. 7. 16.
  Time: 오후 2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>1평 단기임대, 한달살기 부동산 단기계약 플랫폼</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
</head>
<body>
<main class="container">
    <div class="bg-body-tertiary p-5 rounded mt-3">
        <h1>단기임대, 1평에서 찾아보세요</h1>
        <form action="${pageContext.request.contextPath}/auth/logout" method="post">
            <table>
                <tr>
                    <%
                        MemberDto user = (MemberDto) session.getAttribute("user");
                    %>
                    <td><div id="userId">${user.getUserId()}님, 로그아웃 하시겠습니까?</div></td>
                </tr>
                <tr>
                    <button class="btn btn-lg btn-primary" type="submit" name="logout">로그아웃</button>
                </tr>
            </table>
        </form>
    </div>
</main>
</body>
</html>
