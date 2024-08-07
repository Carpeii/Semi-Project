<%@ page import="com.mywebapp.model.Room" %>
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
<form method="post" action="<%=request.getContextPath() %>/service/host/roomImageUpdate" enctype="multipart/form-data">
    <!-- Hidden field to pass the roomId -->
    <label for="imageFiles">Room Images:</label>
    <input type="file" id="imageFiles" name="imageFiles" multiple required /><br>
    <input type="submit" value="Upload Images" />
</form>
</body>
</html>
