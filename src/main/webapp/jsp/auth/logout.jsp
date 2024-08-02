<%--
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
    <title>logout.jsp</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <table>
            <tr>
                <td><div id="userId">${sessionScope.userId}님, 로그아웃 하시겠습니까?</div></td>
            </tr>
            <tr>
                <button type="submit" name="logout">로그아웃</button>
            </tr>
        </table>
    </form>
</body>
</html>
