<%@ page import="com.mywebapp.model.Member" %>
<%@ page import="com.mywebapp.dto.UserDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>방 추가하기</title>
</head>
<body>
<h1>새 방 추가하기</h1>
<form method="post" action="<%=request.getContextPath() %>/service/roomAdd">
    <!-- 기본적인 방 정보 입력란 -->
    <%
        if(session == null) {
            response.sendRedirect(request.getContextPath()+"/jsp/auth/loginMain");
            return;
        }
        UserDto user = (UserDto) session.getAttribute("user");
        if(user.getMemberType() == 0) {
            response.sendRedirect(request.getContextPath()+"/jsp/auth/loginMain");
            return;
        }
    %>
    <input type="hidden" id="hostId" name="hostId" value="<%=user.getId()%>" required /><br>

    <label for="roomName">방 이름:</label>
    <input type="text" id="roomName" name="roomName" value="기본방이름" required /><br>

    <label for="jibunAddress">지번 주소:</label>
    <input type="text" id="jibunAddress" name="jibunAddress" value="기본지번주소" required /><br>

    <label for="streetAddress">도로 주소:</label>
    <input type="text" id="streetAddress" name="streetAddress" value="기본도로주소" required /><br>

    <label for="addressDetail">상세 주소:</label>
    <input type="text" id="addressDetail" name="addressDetail" value="기본상세주소" required /><br>

    <label for="floor">층수:</label>
    <input type="number" id="floor" name="floor" value="1" required /><br>

    <label for="usableArea">사용 면적 (㎡):</label>
    <input type="number" id="usableArea" name="usableArea" value="50" required /><br>

    <label for="roomCount">방 개수:</label>
    <input type="number" id="roomCount" name="roomCount" value="2" required /><br>

    <label for="livingRoomCount">거실 개수:</label>
    <input type="number" id="livingRoomCount" name="livingRoomCount" value="1" required /><br>

    <label for="toiletCount">화장실 개수:</label>
    <input type="number" id="toiletCount" name="toiletCount" value="1" required /><br>

    <label for="kitchenCount">주방 개수:</label>
    <input type="number" id="kitchenCount" name="kitchenCount" value="1" required /><br>

    <label for="duplex">복층:</label>
    <input type="checkbox" id="duplex" name="duplex" checked /><br>

    <label for="elevator">엘리베이터:</label>
    <input type="checkbox" id="elevator" name="elevator" checked /><br>

    <label for="park">주차장:</label>
    <input type="checkbox" id="park" name="park" checked /><br>

    <label for="parkDetail">주차장 상세:</label>
    <input type="text" id="parkDetail" name="parkDetail" value="기본주차장상세" /><br>

    <label for="roomType">방 유형:</label>
    <input type="number" id="roomType" name="roomType" value="1" required /><br>

    <label for="minimumContract">최소 계약 기간 (주):</label>
    <input type="number" id="minimumContract" name="minimumContract" value="12" required /><br>

    <input type="hidden" id="approve" name="approve" value="0" required /><br>

    <input type="submit" value="방 추가하기" />
</form>
</body>
</html>
