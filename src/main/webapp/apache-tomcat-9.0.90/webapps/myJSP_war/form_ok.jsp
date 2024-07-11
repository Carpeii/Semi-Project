<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding( "utf-8" );

	String id = request.getParameter( "id" );
	String password = request.getParameter( "password" );

%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
<h1><%=id%></h1>
<h1><%=password%></h1>
</body>
</html>