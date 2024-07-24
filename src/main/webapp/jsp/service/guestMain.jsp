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
<table border="1" width="1500">
	<tr>
		<th>�̹��� ���</th>
		<th>�̹��� �̸�</th>
		<th>�� �̸�</th>
		<th>���θ� �ּ�</th>
		<th>�Ӵ� ����</th>
		<th>�� �ɼ�</th>
	</tr>
	<c:forEach var="room" items="${requestScope.roomList }">
		<tr>
			<td>${room.imagePath }</td>
			<td>${room.imageName }</td>
			<td>${room.roomName }</td>
			<td>${room.streetAddress }</td>
			<td>${room.rentPrice }</td>
			<td>${room.roomOption }</td>
		</tr>
	</c:forEach>
	
</table>
</body>
</html>