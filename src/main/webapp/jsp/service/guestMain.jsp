<%@ page import="com.mywebapp.dto.UserDto" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>guestMain.jsp</title>
</head>
<body>

<h2>�ܱ��Ӵ�, 1�򿡼� ã�ƺ�����</h2>
<%
    UserDto user = (session != null) ? (UserDto) session.getAttribute("user") : null;
%>
<%
    if (user != null) {
        System.out.println(user);
        // �α��� ���� ����
%>
        <p>${sessionScope.user.userId}�� ȯ���մϴ�</p>
<%--        <p>${user.userId}�� ȯ���մϴ�</p>--%>
        <button onclick="location.href='${pageContext.request.contextPath}/logout'">�α׾ƿ�</button>
        <button onclick="location.href='${pageContext.request.contextPath}/myPage'">���� ������</button>
        <button onclick="location.href='${pageContext.request.contextPath}/hostMain'">ȣ��Ʈ ��������</button>
<%--        <p>${userId}�� ȯ���մϴ�</p>--%>


<%
    } else if (user == null) {
        System.out.println(user);
        // �α��� ���� ����
%>
        <button onclick="location.href='${pageContext.request.contextPath}/login'">�α���</button>
        <button onclick="location.href='${pageContext.request.contextPath}/join'">ȸ������</button>
<%
    }
%>
<button onclick="location.href='${pageContext.request.contextPath}/jsp/service/searchByMap.jsp'">������ �˻�</button>
<button onclick="location.href='${pageContext.request.contextPath}/jsp/service/search.jsp'">�˻�</button>

<!-- RoomListController���� ���� �޾ƿͼ� ���� -->
<%--<c:if test="${not empty userId }">--%>
<%--    <p>${userId }�� ȯ���մϴ�</p>--%>
<%--</c:if>--%>

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
