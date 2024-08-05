<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
     <script src="${pageContext.request.contextPath}/test/popup.js"></script>
           
</head>
<body>
    <h1>Main Page</h1>
    <button onclick="openPopup()">Open Popup</button>
    <div id="result"></div>
</body>
</html>