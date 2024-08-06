<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="room" items="${roomList}">
            <tr>
                <td>${room.id}</td>
                <td>${room.roomName}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin/roomManagement" method="post" class="d-inline">
                        <input type="hidden" name="action" value="approve">
                        <input type="hidden" name="roomId" value="${room.id}">
                        <button type="submit" class="btn btn-secondary">approve</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/admin/roomManagement" method="post" class="d-inline">
                        <input type="hidden" name="action" value="decline">
                        <input type="hidden" name="roomId" value="${room.id}">
                        <button type="submit" class="btn btn-secondary">decline</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Pagination Controls -->
    <div class="pagination">
        <c:if test="${pageNum > 1}">
            <a href="?pageNum=${pageNum - 1}" style="text-decoration: underline; color: #007bff;">Previous</a>
        </c:if>

        <c:forEach begin="${startPage}" end="${endPage}" var="i">
            <c:choose>
                <c:when test="${i == pageNum}">
                    <span style="color: red; font-weight: bold;">${i}</span> <!-- Highlight current page -->
                </c:when>
                <c:otherwise>
                    <a href="?pageNum=${i}" style="text-decoration: none; color: #007bff;">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${pageNum < pageCount}">
            <a href="?pageNum=${pageNum + 1}" style="text-decoration: underline; color: #007bff;">Next</a>
        </c:if>
    </div>

</div>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
</body>
</html>
