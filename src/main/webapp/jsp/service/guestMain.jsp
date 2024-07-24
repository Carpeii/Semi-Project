<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>jsp/service/guestMain.jsp</title>
</head>
<body>
<table border="1" width="500">
	<tr>
		<th>방이름</th>
		<th>도로명 주소</th>
		<th>지번 주소</th>
	</tr>
	<c:forEach var="room" items="${requestScope.roomList }">
		<tr>
			<td>${room.id }</td>
			<td>${room.jibunAddress }</td>
			<td>${room.streetAddress }</td>
		</tr>
	</c:forEach>
	
</table>
</body>
</html>