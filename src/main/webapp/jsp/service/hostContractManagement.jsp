<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <th>Room ID</th>
            <th>Guset ID</th>
            <th>CheckIn Date</th>
            <th>CheckOut Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="booking" items="${bookings}">
            <tr>
                <td>${booking.roomId}</td>
                <td>${booking.guestId}</td>
                <td>${booking.checkInDate}</td>
                <td>${booking.checkOutDate}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/editBooking" method="post" class="d-inline">
                        <input type="hidden" name="bookingId" value="${booking.id}">
                        <button type="submit" class="btn btn-primary">Edit</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/service/hostContractManagement" method="post" class="d-inline">
                        <input type="hidden" name="bookingId" value="${booking.id}">
                        <button type="submit" class="btn btn-secondary">Manage Contract</button>
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
