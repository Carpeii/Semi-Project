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
	<c:forEach var="room" items="${roomList }">
		<tr>
		<!--  
            <td><img src="${room.imagePath}" alt="${room.imageName}" width="100"/></td>
       -->
			<td>${room.imagePath }</td>
			<td>${room.imageName }</td>
			<td>${room.roomName }</td>
			<td>${room.streetAddress }</td>
			<td>${room.rentPrice }</td>
			<td>${room.roomOption }</td>
		</tr>
	</c:forEach>
</table>

<div>
<!-- ���������� ��ũ -->
<c:if test="${startPage>10 }">
	<a href="<%=request.getContextPath() %>/service/guestMain?pageNum=${ startPage - 1}">����</a>
</c:if>
<!--  -->
	<c:forEach var="num" begin="${startPage }" end="${endPage }" >
		<c:choose>
			<c:when test="${pageNum == num }"><!-- ���� ������ �� �ٲٱ� -->
				<a href="<%=request.getContextPath() %>/service/guestMain?pageNum=${num }">
				<span style="color:red">${num }</span>
				</a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath() %>/service/guestMain?pageNum=${num}">
				<span style="color:gray">${num }</span>
				</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
<!-- ���������� ��ũ -->
<c:if test="${endPage<pageCount }">
	<a href="<%=request.getContextPath() %>/service/guestMain?pageNum=${endPage + 1}">����</a>
</c:if>
<!-- -->
</div>
 

</body>
</html>