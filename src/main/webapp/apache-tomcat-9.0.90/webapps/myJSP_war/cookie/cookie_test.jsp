<%--
  Created by IntelliJ IDEA.
  User: kimjiwoong
  Date: 2024. 6. 26.
  Time: 오후 2:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Cookie[] cookies = request.getCookies();
    Cookie cookie = new Cookie("data1", "value1");
    out.println(cookies);

    if (cookies!=null && cookies.length>0) {
        for (int i = 0; i < cookies.length; i++) {
            out.println(cookies[i].getName() + "<br />");
            out.println(cookies[i].getValue() + "<br />");
        }
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
