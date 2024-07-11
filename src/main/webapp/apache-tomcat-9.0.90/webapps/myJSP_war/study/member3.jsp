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
<%
    to.setName("tester");

    out.println("to" + to + "<br/>");
    out.println("name" + to.getName() + "<br/>");
%>
</body>
</html>
