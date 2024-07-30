<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
    <title>Popup Page</title>
     <script src="${pageContext.request.contextPath}/test/popup.js"></script>
</head>
<body>
    <h1>Popup Page</h1>
    <input type="text" id="inputData" placeholder="Enter some data">
    <button onclick="sendData()">Send Data</button>
</body>
</html>