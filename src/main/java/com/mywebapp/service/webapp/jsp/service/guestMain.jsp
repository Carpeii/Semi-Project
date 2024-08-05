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
    <!-- �α��� ���� Ȯ�� �� ����� �̸� ǥ�� -->
	<c:if test="${not empty user }">
		<p>${user.name }�� ȯ���մϴ�</p>
	</c:if>
	
	 <!-- �α��� ���°� �ƴ� �� �α��� ��ư ǥ�� -->
    <c:if test="${empty user}">
        <p>�α����� �ʿ��մϴ�.</p>
        <a href="${pageContext.request.contextPath}/login">�α���</a>
    </c:if>
	
<c:if test="${empty roomList }">
	<p>No rooms available.</p>
</c:if>

<!-- �� ��� ǥ�� -->
<c:if test="${not empty roomList}">
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
        <c:forEach var="room" items="${roomList}">
            <tr>
                <!--  
                <td><img src="${room.imagePath}" alt="${room.imageName}" width="100"/></td>
                -->
                <td>${room.imagePath}</td>
                <td>${room.imageName}</td>
                <td>${room.roomName}</td>
                <td>${room.streetAddress}</td>
                <td>${room.rentPrice}</td>
                <td>${room.roomOption}</td>
                <td><a href="${pageContext.request.contextPath}/service/roomDetail?roomId=${room.id}">�� �󼼺���</a></td>
            </tr>
        </c:forEach>
    </table>

    <!-- Pagination Controls -->
    <div class="pagination">
        <c:if test="${pageNum > 1}">
            <a href="?pageNum=${pageNum - 1}" style="text-decoration: underline; color: #007bff;">Previous</a>
        </c:if>

        <c:forEach begin="${startPage}" end="${endPage}" var="i">
            <c:choose>
                <c:when test="${i == pageNum}">
                    <span style="color: red; font-weight: bold;">${i}</span> <!-- Highlight current page -->
                </c:when>
                <c:otherwise>
                    <a href="?pageNum=${i}" style="text-decoration: none; color: #007bff;">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${pageNum < pageCount}">
            <a href="?pageNum=${pageNum + 1}" style="text-decoration: underline; color: #007bff;">Next</a>
        </c:if>
    </div>
</c:if>

</body>
</html>
