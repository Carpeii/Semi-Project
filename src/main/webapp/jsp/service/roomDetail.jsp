<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room Detail</title>
 	<link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
    <style>
        .container {
            margin-top: 20px;
        }
        .room-detail {
            border: 1px solid #ddd;
            border-radius: 0.5rem;
            padding: 20px;
            background-color: #f8f9fa;
        }
        .room-detail h2 {
            border-bottom: 2px solid #007bff;
            padding-bottom: 10px;
            margin-bottom: 20px;
            color: #007bff;
        }
        .room-name {
            font-size: 2rem;
            font-weight: bold;
            color: #007bff;
            margin-bottom: 20px;
        }
        .address-info {
            margin-bottom: 20px;
        }
        .address-info p {
            margin-bottom: 5px;
        }
        .image-info {
            margin-bottom: 20px;
        }
        .form-section {
            margin-top: 30px;
        }
    </style>
    <script type="text/javascript">
	    let popupUrl = '${pageContext.request.contextPath}/calendar/call';
	    function openPopup() {
	        window.open(popupUrl, 'popupWindow', 'width=1027,height=474,scrollbars=yes');
	    }
    </script>
</head>
<body>

<!-- Include header -->
<jsp:include page="/jsp/common/header.jsp"></jsp:include>

<div class="container">
    <c:if test="${not empty room}">
        <div class="room-detail">
            <!-- Room Name -->
            <div class="room-name">${room.roomName}</div>

            <!-- Address Information -->
            <div class="address-info">
                <p><strong>Address:</strong> ${room.jibunAddress} ${room.streetAddress} ${room.addressDetail} ${room.floor}</p>
            </div>

            <!-- Image Information -->
            <div class="image-info">
                <img src="${pageContext.request.contextPath}${room.imagePath}" class="card-img-top" alt="${room.roomName}">
            </div>

            <!-- Additional Room Information -->
            <h2>Additional Room Information</h2>
            <p><strong>Host Name:</strong> ${room.hostName}</p>
            <p><strong>Usable Area:</strong> ${room.usableArea}</p>
            <p><strong>Room Count:</strong> ${room.roomCount}</p>
            <p><strong>Living Room Count:</strong> ${room.livingRoomCount}</p>
            <p><strong>Toilet Count:</strong> ${room.toiletCount}</p>
            <p><strong>Kitchen Count:</strong> ${room.kitchenCount}</p>
            <p><strong>Duplex:</strong> ${room.duplex ? 'Yes' : 'No'}</p>
            <p><strong>Elevator:</strong> ${room.elevator ? 'Yes' : 'No'}</p>
            <p><strong>Park:</strong> ${room.park ? 'Yes' : 'No'}</p>
            <p><strong>Park Detail:</strong> ${room.parkDetail}</p>
            <p><strong>Room Type:</strong> ${room.roomType}</p>
            <p><strong>Minimum Contract:</strong> ${room.minimumContract}</p>
            <p><strong>Rent Price:</strong> ${room.rentPrice}</p>
            <p><strong>Long Term:</strong> ${room.longTerm}</p>
            <p><strong>Long Term Discount:</strong> ${room.longTermDiscount}</p>
            <p><strong>Early Check-In:</strong> ${room.earlyCheckIn}</p>
            <p><strong>Early Check-In Discount:</strong> ${room.earlyCheckInDiscount}</p>
            <p><strong>Maintenance Bill:</strong> ${room.maintenanceBill}</p>
            <p><strong>Maintenance Bill Detail:</strong> ${room.maintenanceBillDetail}</p>
            <p><strong>Electricity:</strong> ${room.electricity ? 'Yes' : 'No'}</p>
            <p><strong>Water:</strong> ${room.water ? 'Yes' : 'No'}</p>
            <p><strong>Gas:</strong> ${room.gas ? 'Yes' : 'No'}</p>
            <p><strong>Internet:</strong> ${room.internet ? 'Yes' : 'No'}</p>
            <p><strong>Cleaning Fee:</strong> ${room.cleaningFee}</p>
            <p><strong>Refund Type:</strong> ${room.refundType}</p>
        </div>

        <!-- Booking Form -->
        <div class="form-section">
            <form action="${pageContext.request.contextPath}/service/bookingInfo" method="post">
                <!-- Hidden inputs for additional data -->
                <input type="hidden" name="roomId" value="${room.id}">
                <input type="hidden" name="roomName" value="${room.roomName}">
                <input type="hidden" name="jibunAddress" value="${room.jibunAddress}">
                <input type="hidden" name="streetAddress" value="${room.streetAddress}">
                <input type="hidden" name="addressDetail" value="${room.addressDetail}">
                <input type="hidden" name="floor" value="${room.floor}">
                <input type="hidden" name="hostName" value="${room.hostName}">
                <input type="hidden" name="rentPrice" value="${room.rentPrice}">
                <input type="hidden" name="longTermDiscount" value="${room.longTermDiscount}">
                <input type="hidden" name="earlyCheckInDiscount" value="${room.earlyCheckInDiscount}">
                <input type="hidden" name="maintenanceBill" value="${room.maintenanceBill}">
                <input type="hidden" name="cleaningFee" value="${room.cleaningFee}">

                <!-- Date fields -->
                <div class="container mt-5">
    <h1>달력호출창</h1>
<button type="button" class="btn btn-primary"onclick="openPopup()">
<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar3" viewBox="0 0 16 16">
  <path d="M14 0H2a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2M1 3.857C1 3.384 1.448 3 2 3h12c.552 0 1 .384 1 .857v10.286c0 .473-.448.857-1 .857H2c-.552 0-1-.384-1-.857z"></path>
  <path d="M6.5 7a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2"></path>
</svg>달력</button>
			</div>
                <div class="mb-3">
                    <label for="checkInDate" class="form-label">Check-in Date:</label>
                    <input type="text"  id="checkInDate" name="checkInDate" value="${selectDate }" class="form-control"  required/>
                </div>

                <div class="mb-3">
                    <label for="checkOutDate" class="form-label">Check-out Date:</label>
                    <input type="text" id="checkOutDate" name="checkOutDate" value="${selectEndDate }" class="form-control"  required/>
                </div>

                <button type="submit" class="btn btn-primary">Start Booking</button>
            </form>
        </div>
    </c:if>
    
    <!-- Popup Button -->
    <div class="mt-3">
        <button type="button" class="btn btn-outline-primary" onclick="openPopup()">계약 가능 날짜 확인</button>
    </div>
</div>

<!-- Include Bootstrap JS -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>

<jsp:include page="/jsp/common/footer.jsp"></jsp:include>

</body>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
    <title>팝업 JSP 예제</title>
    <script type="text/javascript">
    	let roomId = new URLSearchParams(window.location.search).get('roomId');
    	console.log(roomId);
        	let url = '${pageContext.request.contextPath}/calendar/call?roomId='+roomId;
        function openPopup() {
            //매개변수 -> (Url, 창이름 , 옵션)
            window.open(url, 'popupWindow', 'width=1005,height=545,scrollbars=yes');
        }
    </script>
</html>
