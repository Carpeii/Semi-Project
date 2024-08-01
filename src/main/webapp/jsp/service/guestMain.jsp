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
<c:if test="${not empty userId }">
	<p>${userId }님 환영합니다</p>
</c:if>


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
	<c:forEach var="room" items="${roomList }">
		<tr>
		<!--  
            <td><img src="${room.imagePath}" alt="${room.imageName}" width="100"/></td>
       -->
			<td>${room.imagePath }</td>
			<td>${room.imageName }</td>
			<td>${room.roomName }</td>
			<td>${room.streetAddress }</td>
			<td>${room.rentPrice }</td>
			<td>${room.roomOption }</td>
			<td><a href="<%=request.getContextPath()%>/service/roomDetail?roomId=${room.id}">방 상세보기</a>
		</tr>
	</c:forEach>
</table>



<div>
    <!-- 이전 페이지 링크 -->
    <c:if test="${pageNum > 1}">
        <a href="${pageContext.request.contextPath}/service/guestMain?pageNum=${pageNum - 1}">이전</a>
    </c:if>

    <!-- 페이지 번호 링크 -->
    <c:set var="pageRange" value="10" />
    <c:set var="startPage" value="${pageNum - (pageRange / 2)}" />
    <c:set var="endPage" value="${pageNum + (pageRange / 2)}" />

    <c:if test="${startPage < 1}">
        <c:set var="endPage" value="${endPage + (1 - startPage)}" />
        <c:set var="startPage" value="1" />
    </c:if>

    <c:if test="${endPage > pageCount}">
        <c:set var="startPage" value="${startPage - (endPage - pageCount)}" />
        <c:set var="endPage" value="${pageCount}" />
    </c:if>

    <c:forEach var="num" begin="${startPage}" end="${endPage}">
        <c:choose>
            <c:when test="${num == pageNum}">
                <!-- 현재 페이지 색 바꾸기 -->
                <a href="${pageContext.request.contextPath}/service/guestMain?pageNum=${num}">
                    <span style="color:red">${num}</span>
                </a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/service/guestMain?pageNum=${num}">
                    <span style="color:gray">${num}</span>
                </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <!-- 다음 페이지 링크 -->
    <c:if test="${pageNum < pageCount}">
        <a href="${pageContext.request.contextPath}/service/guestMain?pageNum=${pageNum + 1}">다음</a>
    </c:if>
</div>





 

</body>
</html>