<%--
  Created by IntelliJ IDEA.
  User: now
  Date: 2024. 7. 29.
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myPage.jsp</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/myPage" method="post">
    <table>
        <tr>
            <td><input type="submit" name="booking" value="계약관리">나의 계약관리</td>
        </tr>
        <tr>
            <td><input type="submit" name="account" value="계약관리">나의 정보관리</td>
        </tr>
        <tr>
            <td><input type="submit" name="chat" value="계약관리">채팅</td>
        </tr>
    </table>
</form>
</body>
</html>
