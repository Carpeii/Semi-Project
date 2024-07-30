<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Host Contract Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
</head>
<body>
<div class="container mt-5">
    <h2>Host Contract Management</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Room Name</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="room" items="${rooms}">
            <tr>
                <td> </td>
                <td>${room.roomName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
</body>
</html>
