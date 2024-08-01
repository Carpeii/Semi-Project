<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Information</title>
</head>
<body>
    <h1>계약 시작하기</h1>
    
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
    
    <form action="${pageContext.request.contextPath}/service/bookRoom" method="post">
        <input type="hidden" name="roomId" value="${roomBookingInfo.id}">
        <input type="hidden" name="checkInDate" value="${roomBookingInfo.checkInDate}">
        <input type="hidden" name="checkOutDate" value="${roomBookingInfo.checkOutDate}">
        <!-- 필요한 추가 hidden 필드들도 여기에 포함할 수 있습니다. -->
        <input type="submit" value="계약 승인 요청하기">
    </form>
</body>
</html>
