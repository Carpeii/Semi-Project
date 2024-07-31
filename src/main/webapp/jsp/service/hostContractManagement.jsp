<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Host Contract Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        function approveBooking(bookingId) {
            $.post('${pageContext.request.contextPath}/approveBooking', { bookingId: bookingId }, function(response) {
                alert('Booking approved');
                location.reload();
            });
        }

        function declineBooking(bookingId) {
            $.post('${pageContext.request.contextPath}/declineBooking', { bookingId: bookingId }, function(response) {
                alert('Booking declined');
                location.reload();
            });
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <h2>Host Contract Management</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Room ID</th>
            <th>Guest ID</th>
            <th>Check-In Date</th>
            <th>Check-Out Date</th>
            <th>Actions</th>
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
                    <button type="button" class="btn btn-primary" onclick="approveBooking(${booking.id})">½ÂÀÎ</button>
                    <button type="button" class="btn btn-secondary" onclick="declineBooking(${booking.id})">°ÅÀý</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
</body>
</html>
