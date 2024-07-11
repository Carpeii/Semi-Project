<%--
  Created by IntelliJ IDEA.
  User: kimjiwoong
  Date: 2024. 6. 28.
  Time: 오후 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<a href="./Controller">view1</a>--%>
<%--<a href="./Controller?path=view1">view1</a>--%>
<%--<a href="./Controller?path=view2">view2</a>--%>

<form action="./EmpController" method="get">
    <input type="hidden" name="path" value="emp">
<%--    데이터 <input type="text" name="data">--%>
    <input type="submit">
</form>

</body>
</html>
