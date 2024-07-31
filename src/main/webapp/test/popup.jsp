<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" href="webjars/bootstrap/5.3.3/css/bootstrap.css">
  <script src="webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/calendar" method="post">
<input type="submit" name="movemonth" value="달력 호출"/>
</form>
	<c:out value="${sb}" escapeXml="false"/>
<form action="${pageContext.request.contextPath }/calendar" method="post">
<input type="submit" name="movemonth" value="-1"/>
<input type="submit" name="movemonth" value="1"/>
</form>

</body>
</html>	