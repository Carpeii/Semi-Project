<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Room Images</title>
</head>
<body>
<h1>Upload Room Images</h1>
<form method="post" action="<%=request.getContextPath() %>/service/roomImageUpload" enctype="multipart/form-data">
    <!-- Hidden field to pass the roomId -->
    <input type="hidden" name="roomId" value="<%= request.getAttribute("roomId") %>">

    <label for="imageFiles">Room Images:</label>
    <input type="file" id="imageFiles" name="imageFiles" multiple required /><br>

    <input type="submit" value="Upload Images" />
</form>
</body>
</html>
