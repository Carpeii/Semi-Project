<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Room Price Form</title>
</head>
<body>
<h2>Room Price Form</h2>
<form id="priceForm" action="<%=request.getContextPath() %>/service/roomPriceAdd" method="post">
    <input type="hidden" name="roomId" value="<%= request.getAttribute("roomId") != null ? request.getAttribute("roomId") : "1" %>">

    <label for="rentPrice">Rent Price:</label>
    <input type="number" id="rentPrice" name="rentPrice" value="1000" required><br><br>

    <label for="longTerm">Long Term:</label>
    <input type="number" id="longTerm" name="longTerm" value="6" required><br><br>

    <label for="longTermDiscount">Long Term Discount:</label>
    <input type="number" id="longTermDiscount" name="longTermDiscount" value="10" required><br><br>

    <label for="earlyCheckIn">Early Check-in:</label>
    <input type="number" id="earlyCheckIn" name="earlyCheckIn" value="2" required><br><br>

    <label for="earlyCheckInDiscount">Early Check-in Discount:</label>
    <input type="number" id="earlyCheckInDiscount" name="earlyCheckInDiscount" value="5" required><br><br>

    <label for="maintenanceBill">Maintenance Bill:</label>
    <input type="number" id="maintenanceBill" name="maintenanceBill" value="50" required><br><br>

    <label for="maintenanceBillDetail">Maintenance Bill Detail:</label>
    <input type="text" id="maintenanceBillDetail" name="maintenanceBillDetail" value="Monthly maintenance"><br><br>

    <label for="electricity">Electricity:</label>
    <input type="hidden" name="electricity" value="false">
    <input type="checkbox" id="electricity" name="electricity" value="true" checked><br><br>

    <label for="water">Water:</label>
    <input type="hidden" name="water" value="false">
    <input type="checkbox" id="water" name="water" value="true"><br><br>

    <label for="gas">Gas:</label>
    <input type="hidden" name="gas" value="false">
    <input type="checkbox" id="gas" name="gas" value="true" checked><br><br>

    <label for="internet">Internet:</label>
    <input type="hidden" name="internet" value="false">
    <input type="checkbox" id="internet" name="internet" value="true"><br><br>

    <label for="cleaningFee">Cleaning Fee:</label>
    <input type="number" id="cleaningFee" name="cleaningFee" value="20" required><br><br>

    <label for="refundType">Refund Type:</label>
    <input type="number" id="refundType" name="refundType" value="1" required><br><br>

    <button type="submit">Submit</button>
</form>
</body>
</html>
