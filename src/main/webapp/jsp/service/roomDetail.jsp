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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/roomDetail.css"/>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap-icons/1.11.3/font/bootstrap-icons.css">
    <script type="text/javascript">
	    let popupUrl = '${pageContext.request.contextPath}/calendar/call';
	    function openPopup() {
	        window.open(popupUrl, 'popupWindow', 'width=1027,height=474,scrollbars=yes');
	    }
    </script>
</head>
<body>
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
             <table class="table room-detail text-s">
          <thead>
            <tr>
              <th class="w-50">Option</th>
              <th>Information</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><i class="bi bi-0-circle"></i> Host Name</td>
              <td>${room.hostName}</td>
            </tr>
            <tr>
              <td>Usable Area</td>
              <td>${room.usableArea}</td>
            </tr>
            <tr>
              <td>Room Count</td>
              <td>${room.roomCount}</td>
            </tr>
            <tr>
              <td>Living Room Count</td>
              <td>${room.livingRoomCount}</td>
            </tr>
            <tr>
              <td>Toilet Count</td>
              <td>${room.toiletCount}</td>
            </tr>
            <tr>
              <td>Kitchen Count</td>
              <td> ${room.kitchenCount}</td>
            </tr>
            <tr>
              <td>Duplex</td>
              <td> ${room.duplex ? 'Yes' : 'No'}</td>
            </tr>
            <tr>
              <td>Elevator</td>
              <td>${room.elevator ? 'Yes' : 'No'}</td>
            </tr>
            <tr>
              <td>Park</td>
              <td> ${room.park ? 'Yes' : 'No'}</td>
            </tr>
            <tr>
              <td>Park Detail</td>
              <td> ${room.parkDetail}</td>
            </tr>
            <tr>
              <td>Room Type</td>
              <td> ${room.roomType}</td>
            </tr>
            <tr>
              <td>Minimum Contract</td>
              <td> ${room.minimumContract}</td>
            </tr>
            <tr>
              <td>Rent Price</td>
              <td> ${room.rentPrice}</td>
            </tr>
             <tr>
              <td>Long Term</td>
              <td>${room.longTerm}</td>
            </tr>
            <tr>
              <td>Long Term Discount</td>
              <td> ${roomBookingInfo.longTermDiscount}</td>
            </tr>
            <tr>
              <td>Early Check-In</td>
              <td>${roomBookingInfo.earlyCheckIn}</td>
            </tr>
            <tr>
              <td>Early Check-In Discount</td>
              <td>${roomBookingInfo.earlyCheckInDiscount}</td>
            </tr>
            <tr>
              <td>Maintenance Bill</td>
              <td>${roomBookingInfo.maintenanceBill}</td>
            </tr>
             <tr>
              <td>Maintenance Bill Detail</td>
              <td>${room.maintenanceBillDetail}</td>
            </tr>
            <tr>
              <td>Electricity</td>
              <td>${room.electricity ? 'Yes' : 'No'}</td>
            </tr>
            <tr>
              <td>Water</td>
              <td>${room.water ? 'Yes' : 'No'}</td>
            </tr>
            <tr>
              <td>Gas</td>
              <td>${room.gas ? 'Yes' : 'No'}</td>
            </tr>
            <tr>
              <td>Internet</td>
              <td>${room.internet ? 'Yes' : 'No'}</td>
            </tr>
            <tr>
              <td>Cleaning Fee</td>
              <td>${roomBookingInfo.cleaningFee}</td>
            </tr>
            <tr>
              <td>Refund Type</td>
              <td>${room.refundType}</td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <td>Totals</td>
              <td>21</td>
            </tr>
          </tfoot>
        </table>
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
<button type="button" class="btn btn-primary mb-3"onclick="openPopup()">
<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar3" viewBox="0 0 16 16">
  <path d="M14 0H2a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2M1 3.857C1 3.384 1.448 3 2 3h12c.552 0 1 .384 1 .857v10.286c0 .473-.448.857-1 .857H2c-.552 0-1-.384-1-.857z"></path>
  <path d="M6.5 7a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2"></path>
</svg> 계약 가능 날짜 확인</button>
                <div class="mb-3">
                    <label for="checkInDate" class="form-label">Check-in Date</label>
                    <input type="text"  id="checkInDate" name="checkInDate" value="${selectDate }" class="form-control w-25" readonly required/>
                </div>

                <div class="mb-3">
                    <label for="checkOutDate" class="form-label">Check-out Date</label>
                    <input type="text" id="checkOutDate" name="checkOutDate" value="${selectEndDate }" class="form-control w-25" readonly required/>
                </div>

                <button type="submit" class="btn btn-primary mt-5">Start Booking</button>
			</div>
            </form>
        </div>
    </c:if>
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
        	let url = '${pageContext.request.contextPath}/calendar/call?roomId='+roomId+'&rentPrice=${room.rentPrice}';
        function openPopup() {
            //매개변수 -> (Url, 창이름 , 옵션)
            window.open(url, 'popupWindow', 'width=1005,height=545,scrollbars=yes');
        }
    </script>
</html>
