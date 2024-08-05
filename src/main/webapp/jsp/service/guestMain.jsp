<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>jsp/service/guestMain.jsp</title>
</head>
<body>

<h2>단기임대, 1평에서 찾아보세요</h2>
<button onclick="location.href='../service/searchByMap.jsp'">지도로 검색</button>
<button onclick="location.href='../service/search.jsp'">검색</button>

<!-- RoomListController에서 세션 받아와서 구현 -->
    <!-- 로그인 상태 확인 및 사용자 이름 표시 -->
	<c:if test="${not empty user }">
		<p>${user.name }님 환영합니다</p>
	</c:if>
	
	 <!-- 로그인 상태가 아닐 때 로그인 버튼 표시 -->
    <c:if test="${empty user}">
        <p>로그인이 필요합니다.</p>
        <a href="${pageContext.request.contextPath}/login">로그인</a>
    </c:if>
	
<c:if test="${empty roomList }">
	<p>No rooms available.</p>
</c:if>

<!-- 방 목록 표시 -->
<c:if test="${not empty roomList}">
    <table border="1" width="1500">
        <tr>
            <th>이미지 경로</th>
            <th>이미지 이름</th>
            <th>방 이름</th>
            <th>도로명 주소</th>
            <th>임대 가격</th>
            <th>방 옵션</th>
            <th>방 선택</th>
        </tr>
        <c:forEach var="room" items="${roomList}">
            <tr>
                <!--  
                <td><img src="${room.imagePath}" alt="${room.imageName}" width="100"/></td>
                -->
                <td>${room.imagePath}</td>
                <td>${room.imageName}</td>
                <td>${room.roomName}</td>
                <td>${room.streetAddress}</td>
                <td>${room.rentPrice}</td>
                <td>${room.roomOption}</td>
                <td><a href="${pageContext.request.contextPath}/service/roomDetail?roomId=${room.id}">방 상세보기</a></td>
            </tr>
        </c:forEach>
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
</c:if>

</body>
</html>
