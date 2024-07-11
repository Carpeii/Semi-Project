<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- form -->
<form action="./FormController" method="get">
<%--<form action="form_ok.jsp" method="post">--%>
    <input type="hidden" name="path" value="form_ok">
    아이디 <input type="text" name="id">
    패스워드 <input type="text" name="password">
    <input type="submit" value="login">
</form>


</body>
</html>