<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
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
            <!-- 이하 필요한 정보들을 출력합니다. -->
        </div>
    </c:if>

</body>
</html>