<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Information</title>
    <script type="text/javascript">
    	function confirmBooking() {
    		var result = confirm("계약 승인 요청을 하시겠습니까?");
    		if (result) {
                // 사용자가 확인을 클릭하면 폼을 제출합니다.
				document.getElementById("bookingForm").submit();
    		} else {
                // 사용자가 취소를 클릭하면 아무 동작도 하지 않습니다.
				return false;
    		}
    	}
    </script>
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
    
    <form id ="bookingForm" action="${pageContext.request.contextPath}/service/bookRoom" method="post">
        <input type="hidden" name="roomId" value="${roomBookingInfo.id}">
        <input type="hidden" name="checkInDate" value="${roomBookingInfo.checkInDate}">
        <input type="hidden" name="checkOutDate" value="${roomBookingInfo.checkOutDate}">
        <!-- 필요한 추가 hidden 필드들도 여기에 포함할 수 있습니다. -->
        <input type="button" value="계약 승인 요청하기" onclick="confirmBooking()">
    </form>
</body>
</html>
