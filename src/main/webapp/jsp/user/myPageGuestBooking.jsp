<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MyPageGuestBooking.jsp</title>
    <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            margin-top: 20px;
        }
        .room-detail {
            border: 1px solid #ddd;
            border-radius: 0.5rem;
            padding: 20px;
            background-color: #f8f9fa;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .section-title {
            border-bottom: 2px solid #007bff;
            padding-bottom: 10px;
            margin-bottom: 20px;
            color: #007bff;
            font-size: 1.75rem;
            font-weight: 600;
        }
        .room-name {
            font-size: 1.5rem;
            font-weight: bold;
            color: #007bff;
            margin-bottom: 20px;
        }
        .room-info p {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<!-- Include header -->
<jsp:include page="/jsp/common/header.jsp"></jsp:include>

<div class="container">
    <!-- 계약한 내용 보여주기 -->

    <h2 class="section-title">진행중인 계약</h2>
    <c:if test="${not empty userRooms}">
        <c:set var="firstRoom" value="${userRooms[0]}"/>
        <div class="room-detail">
            <div class="room-name">${firstRoom.roomName}</div>
            <div class="room-info">
                <p><strong>주소:</strong> ${firstRoom.jibunAddress}, ${firstRoom.streetAddress}, ${firstRoom.addressDetail}, ${firstRoom.floor}</p>
                <p><strong>체크인:</strong> ${firstRoom.checkInDate}</p>
                <p><strong>체크아웃:</strong> ${firstRoom.checkOutDate}</p>
                <c:choose>
                    <c:when test="${firstRoom.bookingStatus == 0}">
                        <p><strong>상태:</strong> 승인 요청</p>
                    </c:when>
                    <c:when test="${firstRoom.bookingStatus == 1}">
                        <p><strong>상태:</strong> 승인 완료</p>
                    </c:when>
                    <c:when test="${firstRoom.bookingStatus == 2}">
                        <p><strong>상태:</strong> 승인 거절</p>
                    </c:when>
                </c:choose>
            </div>
        </div>
        <br>
    </c:if>

    <h2 class="section-title">계약 목록</h2>
    <c:forEach var="room" items="${userRooms}">
        <div class="room-detail">
            <div class="room-name">${room.roomName}</div>
            <div class="room-info">
                <p><strong>주소:</strong> ${room.jibunAddress}, ${room.streetAddress}, ${room.addressDetail}, ${room.floor}</p>
                <p><strong>임대 가격:</strong> ${room.rentPrice}</p>
                <p><strong>체크인:</strong> ${room.checkInDate}</p>
                <p><strong>체크아웃:</strong> ${room.checkOutDate}</p>
                <c:choose>
                    <c:when test="${room.bookingStatus == 0}">
                        <p><strong>상태:</strong> 승인 요청</p>
                    </c:when>
                    <c:when test="${room.bookingStatus == 1}">
                        <p><strong>상태:</strong> 승인 완료</p>
                    </c:when>
                    <c:when test="${room.bookingStatus == 2}">
                        <p><strong>상태:</strong> 승인 거절</p>
                    </c:when>
                </c:choose>
            </div>
        </div>
        <br>
    </c:forEach>
</div>

<!-- Include Bootstrap JS -->
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>

<jsp:include page="/jsp/common/footer.jsp"></jsp:include>

</body>
</html>
