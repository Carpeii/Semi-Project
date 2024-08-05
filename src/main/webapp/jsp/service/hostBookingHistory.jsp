<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Bookings</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
</head>
<body>
<div class="container mt-5">
    <h2>Booking List</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Room ID</th>
            <th>Guest ID</th>
            <th>Check-In Date</th>
            <th>Check-Out Date</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="booking" items="${bookings}">
            <tr>
                <td>${booking.id}</td>
                <td>${booking.roomId}</td>
                <td>${booking.guestId}</td>
                <td>${booking.checkInDate}</td>
                <td>${booking.checkOutDate}</td>
                <td>
                    <c:choose>
<%--                        <c:when test="${booking.bookingStatus == 0}">Pending</c:when>--%>
                        <c:when test="${booking.bookingStatus == 1}">Approved</c:when>
<%--                        <c:when test="${booking.bookingStatus == 2}">Declined</c:when>--%>
                        <c:otherwise>Unknown</c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
</body>
</html>
