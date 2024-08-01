<%@ page import="com.mywebapp.model.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Room</title>
</head>
<body>
<h1>Add New Room</h1>
<form method="post" action="<%=request.getContextPath() %>/service/roomAdd">
    <!-- 기본적인 Room 정보 넣기란 -->
<%--    <label for="hostId">Host ID:</label>--%>
    <%
        if(session == null) {
            response.sendRedirect(request.getContextPath()+"/jsp/auth/loginMain");
            return;
        }
        Member user = (Member) session.getAttribute("user");
        System.out.println(user.getId());
        if(user.getMemberType() == 0) {
            response.sendRedirect(request.getContextPath()+"/jsp/auth/loginMain");
            return;
        }
    %>
    <input type="hidden" id="hostId" name="hostId" value="<%=user.getId()%>" required /><br>

    <label for="roomName">Room Name:</label>
    <input type="text" id="roomName" name="roomName" value="defaultRoomName" required /><br>

    <label for="jibunAddress">Jibun Address:</label>
    <input type="text" id="jibunAddress" name="jibunAddress" value="defaultJibunAddress" required /><br>

    <label for="streetAddress">Street Address:</label>
    <input type="text" id="streetAddress" name="streetAddress" value="defaultStreetAddress" required /><br>

    <label for="addressDetail">Street Address Detail:</label>
    <input type="text" id="addressDetail" name="addressDetail" value="defaultAddressDetail" required /><br>

    <label for="floor">Floor:</label>
    <input type="number" id="floor" name="floor" value="1" required /><br>

    <label for="usableArea">Usable Area:</label>
    <input type="number" id="usableArea" name="usableArea" value="50" required /><br>

    <label for="roomCount">Room Count:</label>
    <input type="number" id="roomCount" name="roomCount" value="2" required /><br>

    <label for="livingRoomCount">Living Room Count:</label>
    <input type="number" id="livingRoomCount" name="livingRoomCount" value="1" required /><br>

    <label for="toiletCount">Toilet Count:</label>
    <input type="number" id="toiletCount" name="toiletCount" value="1" required /><br>

    <label for="kitchenCount">Kitchen Count:</label>
    <input type="number" id="kitchenCount" name="kitchenCount" value="1" required /><br>

    <label for="duplex">Duplex:</label>
    <input type="checkbox" id="duplex" name="duplex" checked /><br>

    <label for="elevator">Elevator:</label>
    <input type="checkbox" id="elevator" name="elevator" checked /><br>

    <label for="park">Park:</label>
    <input type="checkbox" id="park" name="park" checked /><br>

    <label for="parkDetail">Park Detail:</label>
    <input type="text" id="parkDetail" name="parkDetail" value="defaultParkDetail" /><br>

    <label for="roomType">Room Type:</label>
    <input type="number" id="roomType" name="roomType" value="1" required /><br>

    <label for="minimumContract">Minimum Contract:</label>
    <input type="number" id="minimumContract" name="minimumContract" value="12" required /><br>

    <%--hidden 필드--%>
    <input type="hidden" id="approve" name="approve" value="0" required /><br>

    <input type="submit" value="Add Room" />
</form>
</body>
</html>
