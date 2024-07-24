<%--
  Created by IntelliJ IDEA.
  User: now
  Date: 2024. 7. 17.
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>roomEdit.jsp</title>
</head>
<body>
<!-- 승인 대기 중인 매물 목록 -->
<!-- 승인, 보류, 거절 관리 -->
<form method="post" action="${pageContext.request.contextPath }/board/insert">
    매물 1 <br>
    <button onclick="location.href='./roomEditOk.jsp'">edit</button>
    매물 2 <br>
    <button onclick="location.href='./roomEditOk.jsp'">edit</button>
    매물 3 <br>
    <button onclick="location.href='./roomEditOk.jsp'">edit</button>
</form>
</body>
</html>
