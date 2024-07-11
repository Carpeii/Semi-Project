<%--
  Created by IntelliJ IDEA.
  User: kimjiwoong
  Date: 2024. 6. 20.
  Time: 오전 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="simple1.MemberTo" %>
<%
    request.setCharacterEncoding("utf-8");

    String name = request.getParameter("name");
    String password = request.getParameter("password");

    //Beans
    MemberTo memberTo = new MemberTo();
        memberTo.setName(name);
        memberTo.setPassword(password);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--member_ok.jsp-->
<%=memberTo.getName()%><br/>
<%=memberTo.getPassword()%><br/>
</body>
</html>
