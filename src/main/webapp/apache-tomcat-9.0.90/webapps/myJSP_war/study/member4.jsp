<%--
  Created by IntelliJ IDEA.
  User: kimjiwoong
  Date: 2024. 6. 20.
  Time: 오전 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:useBean id="to" class="simple1.MemberTo"/>
    <jsp:setProperty name="to" property="name" value="TestName"/>
    <jsp:getProperty name="to" property="name"/><br/>
</body>
</html>