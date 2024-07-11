<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<%
    int i =10;
%>
Hello 1<br/>
<%--<%@ include file="includer.jsp" %>--%>
<jsp:include page="includer.jsp">
    <jsp:param name="data1" value="value1"/>
    <jsp:param name="data2" value="<%=i%>"/>
</jsp:include>
<jsp:forward page="includer.jsp"></jsp:forward>
Hello 4<br/>
</body>
</html>
