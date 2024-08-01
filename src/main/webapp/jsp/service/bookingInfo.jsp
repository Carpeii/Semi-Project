<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Information</title>
</head>
<body>
    <h1>Booking Information</h1>
    
    <c:if test="${not empty roomBookingInfo}">
        <h2>Room Details</h2>
        <p><strong>Room Name:</strong> ${roomBookingInfo.roomName}</p>
        <p><strong>Jibun Address:</strong> ${roomBookingInfo.jibunAddress}</p>
        <p><strong>Street Address:</strong> ${roomBookingInfo.streetAddress}</p>
        <p><strong>Address Detail:</strong> ${roomBookingInfo.addressDetail}</p>
        <p><strong>Floor:</strong> ${roomBookingInfo.floor}</p>
        <p><strong>Host Name:</strong> ${roomBookingInfo.hostName}</p>
        <p><strong>Rent Price:</strong> ${roomBookingInfo.rentPrice}</p>
        <p><strong>Long Term Discount:</strong> ${roomBookingInfo.longTermDiscount}</p>
        <p><strong>Early Check-In Discount:</strong> ${roomBookingInfo.earlyCheckInDiscount}</p>
        <p><strong>Maintenance Bill:</strong> ${roomBookingInfo.maintenanceBill}</p>
        <p><strong>Cleaning Fee:</strong> ${roomBookingInfo.cleaningFee}</p>
        <p><strong>Check-In Date:</strong> ${roomBookingInfo.checkInDate}</p>
        <p><strong>Check-Out Date:</strong> ${roomBookingInfo.checkOutDate}</p>
    </c:if>
    
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
</body>
</html>
