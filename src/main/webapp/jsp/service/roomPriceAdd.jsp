<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>객실 요금 폼</title>
</head>
<body>
<h2>객실 요금 폼</h2>
<form id="priceForm" action="<%=request.getContextPath() %>/service/roomPriceAdd" method="post">
    <label for="rentPrice">임대료:</label>
    <input type="number" id="rentPrice" name="rentPrice" value="1000" required><br><br>

    <label for="longTerm">장기 계약 기간 (주):</label>
    <input type="number" id="longTerm" name="longTerm" value="6" required><br><br>

    <label for="longTermDiscount">장기 계약 할인율 (%):</label>
    <input type="number" id="longTermDiscount" name="longTermDiscount" value="10" required><br><br>

    <label for="earlyCheckIn">얼리 체크인 (일):</label>
    <input type="number" id="earlyCheckIn" name="earlyCheckIn" value="2" required><br><br>

    <label for="earlyCheckInDiscount">얼리 체크인 할인율 (%):</label>
    <input type="number" id="earlyCheckInDiscount" name="earlyCheckInDiscount" value="5" required><br><br>

    <label for="maintenanceBill">관리비 :</label>
    <input type="number" id="maintenanceBill" name="maintenanceBill" value="50" required><br><br>

    <label for="maintenanceBillDetail">관리비 세부사항:</label>
    <input type="text" id="maintenanceBillDetail" name="maintenanceBillDetail" value="월간 유지보수"><br><br>

    <label for="electricity">전기:</label>
    <input type="hidden" name="electricity" value="false">
    <input type="checkbox" id="electricity" name="electricity" value="true" checked><br><br>

    <label for="water">수도:</label>
    <input type="hidden" name="water" value="false">
    <input type="checkbox" id="water" name="water" value="true"><br><br>

    <label for="gas">가스:</label>
    <input type="hidden" name="gas" value="false">
    <input type="checkbox" id="gas" name="gas" value="true" checked><br><br>

    <label for="internet">인터넷:</label>
    <input type="hidden" name="internet" value="false">
    <input type="checkbox" id="internet" name="internet" value="true"><br><br>

    <label for="cleaningFee">청소 비용:</label>
    <input type="number" id="cleaningFee" name="cleaningFee" value="20" required><br><br>

    <label for="refundType">환불 유형:</label>
    <input type="number" id="refundType" name="refundType" value="1" required><br><br>

    <button type="submit">제출</button>
</form>
</body>
</html>
