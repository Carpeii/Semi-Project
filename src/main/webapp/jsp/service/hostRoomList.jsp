<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Host Contract Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
</head>
<body>
<div class="container mt-5">
    <h2>Host Contract Management</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Room Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="room" items="${rooms}">
            <tr>
                <td>${room.roomName}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/editRoom" method="post" class="d-inline">
                        <input type="hidden" name="roomId" value="${room.id}">
                        <button type="submit" class="btn btn-primary">Edit</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/service/hostContractManagement" method="post" class="d-inline">
                        <input type="hidden" name="roomId" value="${room.id}">
                        <button type="submit" class="btn btn-secondary">Manage Contract</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/service/hostBookingHistory" method="post" class="d-inline">
                        <input type="hidden" name="roomId" value="${room.id}">
                        <button type="submit" class="btn btn-secondary">view History</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
</body>
</html>
