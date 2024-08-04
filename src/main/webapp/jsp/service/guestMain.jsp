<%@ page import="com.mywebapp.dto.UserDto" %>
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
<%
    UserDto user = (session != null) ? (UserDto) session.getAttribute("user") : null;
%>
<%
    if (user != null) {
        System.out.println(user);
        // �α��� ���� ����
%>
        <p>${sessionScope.user.userId}�� ȯ���մϴ�</p>
        <button onclick="location.href='../auth/logout.jsp'">�α׾ƿ�</button>
        <button onclick="location.href='../auth/myPage.jsp'">���� ������</button>
<%--        <p>${userId}�� ȯ���մϴ�</p>--%>
<%
    } else if (user == null) {
        System.out.println(user);
        // �α��� ���� ����
%>
        <button onclick="location.href='../auth/loginMain.jsp'">�α���</button>
        <button onclick="location.href='../auth/join.jsp'">ȸ������</button>
<%
    }
%>
<button onclick="location.href='../service/searchByMap.jsp'">������ �˻�</button>
<button onclick="location.href='../service/search.jsp'">�˻�</button>

<!-- RoomListController���� ���� �޾ƿͼ� ���� -->
<%--<c:if test="${not empty userId }">--%>
<%--    <p>${userId }�� ȯ���մϴ�</p>--%>
<%--</c:if>--%>

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