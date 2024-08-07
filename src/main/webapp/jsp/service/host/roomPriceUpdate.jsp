<%@ page import="com.mywebapp.dto.RoomDetailDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>객실 요금 폼</title>
</head>
<body>
<h2>객실 요금 폼</h2>
<%
    if(session == null) {
        response.sendRedirect(request.getContextPath()+"/jsp/auth/loginMain");
        return;
    }
    RoomDetailDto roomDetailDto = (RoomDetailDto) session.getAttribute("roomDetailDto");
%>
<form id="priceForm" action="<%=request.getContextPath() %>/service/host/roomPriceUpdate" method="post">
    <input type="hidden" id="roomId" name="roomId" value="<%=roomDetailDto.getId()%>">
    <label for="rentPrice">임대료:</label>
    <input type="number" id="rentPrice" name="rentPrice" value="<%=roomDetailDto.getRentPrice()%>" required><br><br>

    <label for="longTerm">장기 계약 기간 (주):</label>
    <input type="number" id="longTerm" name="longTerm" value="<%=roomDetailDto.getLongTerm()%>" required><br><br>

    <label for="longTermDiscount">장기 계약 할인율 (%):</label>
    <input type="number" id="longTermDiscount" name="longTermDiscount" value="<%=roomDetailDto.getLongTermDiscount()%>" required><br><br>

    <label for="earlyCheckIn">얼리 체크인 (일):</label>
    <input type="number" id="earlyCheckIn" name="earlyCheckIn" value="<%=roomDetailDto.getEarlyCheckIn()%>" required><br><br>

    <label for="earlyCheckInDiscount">얼리 체크인 할인율 (%):</label>
    <input type="number" id="earlyCheckInDiscount" name="earlyCheckInDiscount" value="<%=roomDetailDto.getEarlyCheckInDiscount()%>" required><br><br>

    <label for="maintenanceBill">관리비 :</label>
    <input type="number" id="maintenanceBill" name="maintenanceBill" value="<%=roomDetailDto.getMaintenanceBill()%>" required><br><br>

    <label for="maintenanceBillDetail">관리비 세부사항:</label>
    <input type="text" id="maintenanceBillDetail" name="maintenanceBillDetail" value="<%=roomDetailDto.getMaintenanceBillDetail()%>"><br><br>

    <label for="electricity">전기:</label>
    <input type="hidden" name="electricity" value="false">
    <input type="checkbox" id="electricity" name="electricity" <c:if test="${roomDetailDto.electricity}">checked</c:if>><br><br>

    <label for="water">수도:</label>
    <input type="hidden" name="water" value="false">
    <input type="checkbox" id="water" name="water"  <c:if test="${roomDetailDto.water}">checked</c:if>><br><br>

    <label for="gas">가스:</label>
    <input type="hidden" name="gas" value="false">
    <input type="checkbox" id="gas" name="gas"  <c:if test="${roomDetailDto.gas}">checked</c:if>><br><br>

    <label for="internet">인터넷:</label>
    <input type="hidden" name="internet" value="false">
    <input type="checkbox" id="internet" name="internet"  <c:if test="${roomDetailDto.internet}">checked</c:if>><br><br>

    <label for="cleaningFee">청소 비용:</label>
    <input type="number" id="cleaningFee" name="cleaningFee" value="<%=roomDetailDto.getCleaningFee()%>" required><br><br>

    <label for="refundType">환불 유형:</label>
    <input type="number" id="refundType" name="refundType" value="<%=roomDetailDto.getRefundType()%>" required><br><br>

    <button type="submit">제출</button>
</form>
</body>
</html>
