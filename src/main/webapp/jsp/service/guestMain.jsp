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

<h2>�ܱ��Ӵ�, 1�򿡼� ã�ƺ�����</h2>
<button onclick="location.href='../service/searchByMap.jsp'">������ �˻�</button>
<button onclick="location.href='../service/search.jsp'">�˻�</button>

<!-- RoomListController���� ���� �޾ƿͼ� ���� -->
<c:if test="${not empty userId }">
	<p>${userId }�� ȯ���մϴ�</p>
</c:if>


<table border="1" width="1500">
	<tr>
		<th>�̹��� ���</th>
		<th>�̹��� �̸�</th>
		<th>�� �̸�</th>
		<th>���θ� �ּ�</th>
		<th>�Ӵ� ����</th>
		<th>�� �ɼ�</th>
		<th>�� ����</th>
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
			<td><a href="<%=request.getContextPath()%>/service/roomDetail?roomId=${room.id}">�� �󼼺���</a>
		</tr>
	</c:forEach>
</table>



<div>
    <!-- ���� ������ ��ũ -->
    <c:if test="${pageNum > 1}">
        <a href="${pageContext.request.contextPath}/service/guestMain?pageNum=${pageNum - 1}">����</a>
    </c:if>

    <!-- ������ ��ȣ ��ũ -->
    <c:set var="pageRange" value="10" />
    <c:set var="startPage" value="${pageNum - (pageRange / 2)}" />
    <c:set var="endPage" value="${pageNum + (pageRange / 2)}" />

    <c:if test="${startPage < 1}">
        <c:set var="endPage" value="${endPage + (1 - startPage)}" />
        <c:set var="startPage" value="1" />
    </c:if>

    <c:if test="${endPage > pageCount}">
        <c:set var="startPage" value="${startPage - (endPage - pageCount)}" />
        <c:set var="endPage" value="${pageCount}" />
    </c:if>

    <c:forEach var="num" begin="${startPage}" end="${endPage}">
        <c:choose>
            <c:when test="${num == pageNum}">
                <!-- ���� ������ �� �ٲٱ� -->
                <a href="${pageContext.request.contextPath}/service/guestMain?pageNum=${num}">
                    <span style="color:red">${num}</span>
                </a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/service/guestMain?pageNum=${num}">
                    <span style="color:gray">${num}</span>
                </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <!-- ���� ������ ��ũ -->
    <c:if test="${pageNum < pageCount}">
        <a href="${pageContext.request.contextPath}/service/guestMain?pageNum=${pageNum + 1}">����</a>
    </c:if>
</div>





 

</body>
</html>