<%--
  Created by IntelliJ IDEA.
  User: now
  Date: 2024. 7. 22.
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>loginMain.jsp</title>
</head>
<body>
<h1>Login</h1>
<form action="${pageContext.request.contextPath}/login" method="post">
    아이디: <input type="text" name="id"><br>
    비밀번호: <input type="password" name="pw"><br>
    <div>${errMsg}</div>
    <input type="submit" name="guestLogin" value="게스트 로그인">
    <input type="submit" name="hostLogin" value="호스트 로그인">
</form>
</body>
</html>