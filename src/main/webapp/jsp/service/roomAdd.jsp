<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Room</title>
</head>
<body>
<h1>Add Room</h1>
<form action="roomAdd" method="post">
    <label for="hostId">Host ID:</label>
    <input type="text" id="hostId" name="hostId" required><br>

    <label for="roomName">Room Name:</label>
    <input type="text" id="roomName" name="roomName" required><br>

    <label for="jibunAddress">Jibun Address:</label>
    <input type="text" id="jibunAddress" name="jibunAddress" required><br>

    <label for="streetAddress">Street Address:</label>
    <input type="text" id="streetAddress" name="streetAddress" required><br>

    <label for="floor">Floor:</label>
    <input type="number" id="floor" name="floor" required><br>

    <label for="usableArea">Usable Area (sq m):</label>
    <input type="number" id="usableArea" name="usableArea" required><br>

    <label for="roomCount">Room Count:</label>
    <input type="number" id="roomCount" name="roomCount" required><br>

    <label for="livingRoomCount">Living Room Count:</label>
    <input type="number" id="livingRoomCount" name="livingRoomCount" required><br>

    <label for="toiletCount">Toilet Count:</label>
    <input type="number" id="toiletCount" name="toiletCount" required><br>

    <label for="kitchenCount">Kitchen Count:</label>
    <input type="number" id="kitchenCount" name="kitchenCount" required><br>

    <label for="duplex">Duplex:</label>
    <input type="checkbox" id="duplex" name="duplex"><br>

    <label for="elevator">Elevator:</label>
    <input type="checkbox" id="elevator" name="elevator"><br>

    <label for="park">Park:</label>
    <input type="checkbox" id="park" name="park"><br>

    <label for="parkDetail">Park Detail:</label>
    <input type="text" id="parkDetail" name="parkDetail"><br>

    <label for="roomType">Room Type:</label>
    <input type="number" id="roomType" name="roomType" required><br>

    <label for="minimumContract">Minimum Contract (months):</label>
    <input type="number" id="minimumContract" name="minimumContract" required><br>

    <label for="approve">Approval Status:</label>
    <input type="number" id="approve" name="approve" required><br>

    <label for="imageName">Image Name:</label>
    <input type="text" id="imageName" name="imageName"><br>

    <label for="imagePath">Image Path:</label>
    <input type="text" id="imagePath" name="imagePath"><br>

    <label for="imageOrder">Image Order:</label>
    <input type="number" id="imageOrder" name="imageOrder"><br>

    <label for="roomOption">Room Option:</label>
    <input type="text" id="roomOption" name="roomOption"><br>

    <label for="rentPrice">Rent Price:</label>
    <input type="number" id="rentPrice" name="rentPrice" required><br>

    <label for="longTerm">Long Term (months):</label>
    <input type="number" id="longTerm" name="longTerm" required><br>

    <label for="longTermDiscount">Long Term Discount (%):</label>
    <input type="number" id="longTermDiscount" name="longTermDiscount"><br>

    <label for="earlyCheckIn">Early Check-In Fee:</label>
    <input type="number" id="earlyCheckIn" name="earlyCheckIn"><br>

    <label for="earlyCheckInDiscount">Early Check-In Discount (%):</label>
    <input type="number" id="earlyCheckInDiscount" name="earlyCheckInDiscount"><br>

    <label for="maintenanceBill">Maintenance Bill:</label>
    <input type="number" id="maintenanceBill" name="maintenanceBill"><br>

    <label for="maintenanceBillDiscount">Maintenance Bill Discount (%):</label>
    <input type="number" id="maintenanceBillDiscount" name="maintenanceBillDiscount"><br>

    <label for="electricity">Electricity Included:</label>
    <input type="checkbox" id="electricity" name="electricity"><br>

    <label for="water">Water Included:</label>
    <input type="checkbox" id="water" name="water"><br>

    <label for="gas">Gas Included:</label>
    <input type="checkbox" id="gas" name="gas"><br>

    <label for="internet">Internet Included:</label>
    <input type="checkbox" id="internet" name="internet"><br>

    <label for="cleaningFee">Cleaning Fee:</label>
    <input type="number" id="cleaningFee" name="cleaningFee"><br>

    <label for="refundType">Refund Type:</label>
    <input type="number" id="refundType" name="refundType"><br>

    <input type="submit" value="Add Room">
</form>
</body>
</html>
