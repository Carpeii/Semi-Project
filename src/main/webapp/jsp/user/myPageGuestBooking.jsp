<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPageGuestBooking.jsp</title>
</head>
<body>
<!-- 계약한 내용 보여주기 -->
<h1>계약 관리</h1>

<h2>진행중인 계약</h2>
<c:if test="${not empty userRooms}">
    <c:set var="firstRoom" value="${userRooms[0]}"/>
    <div>
        <p><strong>방 이름:</strong>${firstRoom.roomName }</p>
        <p><strong>주소:</strong>${firstRoom.jibunAddress }, ${firstRoom.streetAddress }, ${firstRoom.addressDetail }, ${firstRoom.floor }</p>
        <p><strong>체크인:</strong>${firstRoom.checkInDate} </p>
        <p><strong>체크아웃:</strong>${firstRoom.checkOutDate} </p>

        <c:choose>
            <c:when test="${firstRoom.bookingStatus == 0 }">
                <p><strong>상태:</strong>승인 요청</p>
            </c:when>
            <c:when test="${firstRoom.bookingStatus == 1 }">
                <p><strong>상태:</strong>승인 완료</p>
            </c:when>
            <c:when test="${firstRoom.bookingStatus == 2 }">
                <p><strong>상태:</strong>승인 거절</p>
            </c:when>
        </c:choose>
    </div>
    <br>
</c:if>


<h2>계약 목록</h2>
<c:forEach var="room" items="${userRooms }">
	<div>
		<p><strong>방 이름:</strong>${room.roomName }</p>
		<p><strong>주소:</strong>${room.jibunAddress }, ${room.streetAddress }, ${room.addressDetail }, ${room.floor }</p>
		<p><strong>임대 가격:</strong>${room.rentPrice} </p>
		<p><strong>체크인:</strong>${room.checkInDate} </p>
		<p><strong>체크아웃:</strong>${room.checkOutDate} </p>
		
		<c:choose>
			<c:when test="${room.bookingStatus == 0 }">
				<p><strong>상태:</strong>승인 요청</p>
			</c:when>
			<c:when test="${room.bookingStatus == 1 }">
				<p><strong>상태:</strong>승인 완료</p>
			</c:when>
			<c:when test="${room.bookingStatus == 2 }">
				<p><strong>상태:</strong>승인 거절</p>
			</c:when>
		</c:choose>
	</div>
	<br>



</c:forEach>

</body>
</html>