<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Room Detail</h1>
    
    <c:if test="${not empty room}">
        <div>
            <h2>Room Information</h2>
            <p><strong>Host Name:</strong> ${room.hostName}</p>
            <p><strong>Room Name:</strong> ${room.roomName}</p>
            <p><strong>Jibun Address:</strong> ${room.jibunAddress}</p>
            <p><strong>Street Address:</strong> ${room.streetAddress}</p>
            <p><strong>Address Detail:</strong> ${room.addressDetail}</p>
            <p><strong>Floor:</strong> ${room.floor}</p>
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
            <p><strong>Image Name:</strong> ${room.imageName}</p>
	        <p><strong>Image Path:</strong> ${room.imagePath}</p>
	        <p><strong>Image Order:</strong> ${room.imageOrder}</p>
	        <p><strong>Room Option:</strong> ${room.roomOption}</p>
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

<!--    
        <form action="${pageContext.request.contextPath}/service/bookRoom" method="post">
        	<input type="hidden" name="roomId" value="${room.id }">
        	<label for="checkInDate">Check-in Date:</label>
        	<input type="date" id="checkInDate" name="checkInDate" required><br><br>
        	
        	<label for="checkOutDate">Check-out Date:</label>
        	<input type="date" id="checkOutDate" name="checkOutDate" required><br><br>
        	
        	<input type="submit" value="방 계약 시작하기">
        </form>
      
-->
		<form action="${pageContext.request.contextPath}/service/bookingInfo" method="post">
		    <!-- Hidden inputs for additional data -->
		    <input type="hidden" name="roomId" value="${room.id}">
		    <input type="hidden" name="roomName" value="${room.roomName }">
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
        	<label for="checkInDate">Check-in Date:</label>
        	<input type="date" id="checkInDate" name="checkInDate" required><br><br>
        	
        	<label for="checkOutDate">Check-out Date:</label>
        	<input type="date" id="checkOutDate" name="checkOutDate" required><br><br>
        	
        	<input type="submit" value="방 계약 시작하기">
        </form>
        
    </c:if>

</body>
</html>