<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Information</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
    <style>
        .container {
            margin-top: 20px;
        }
        .booking-details {
            border: 1px solid #ddd;
            border-radius: 0.5rem;
            padding: 20px;
            background-color: #f8f9fa;
        }
        .booking-details h2 {
            border-bottom: 2px solid #007bff;
            padding-bottom: 10px;
            margin-bottom: 20px;
            color: #007bff;
        }
        .form-section {
            margin-top: 30px;
        }
    </style>
    <script type="text/javascript">
        function confirmBooking() {
            var result = confirm("계약 승인 요청을 하시겠습니까?");
            if (result) {
                document.getElementById("bookingForm").submit();
            }
        }
    </script>
</head>
<body>

<div class="container">

    <c:if test="${not empty roomBookingInfo}">
        <div class="booking-details">
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
        </div>
    </c:if>
    

    
    <div class="form-section">
        <form id="bookingForm" action="${pageContext.request.contextPath}/service/bookRoom" method="post">
            <input type="hidden" name="roomId" value="${roomBookingInfo.id}">
            <input type="hidden" name="checkInDate" value="${roomBookingInfo.checkInDate}">
            <input type="hidden" name="checkOutDate" value="${roomBookingInfo.checkOutDate}">
            <!-- 필요한 추가 hidden 필드들도 여기에 포함할 수 있습니다. -->
            <button type="button" class="btn btn-primary" onclick="confirmBooking()">계약 승인 요청하기</button>
        </form>
    </div>
</div>

<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>

</body>
</html>
