<%@ page import="com.mywebapp.dto.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>guestMain.jsp</title>
</head>
<body>

<h2>단기임대, 1평에서 찾아보세요</h2>

<c:choose>
    <c:when test="${user != null}">
        <p>${user.name}님 환영합니다</p>
        <button onclick="location.href='${pageContext.request.contextPath}/logout'">로그아웃</button>
        <button onclick="location.href='${pageContext.request.contextPath}/myPage'">마이 페이지</button>
        <button onclick="location.href='${pageContext.request.contextPath}/hostMain'">호스트 페이지로</button>
    </c:when>
    <c:otherwise>
        <button onclick="location.href='${pageContext.request.contextPath}/login'">로그인</button>
        <button onclick="location.href='${pageContext.request.contextPath}/join'">회원가입</button>
    </c:otherwise>
</c:choose>

<button onclick="location.href='${pageContext.request.contextPath}/jsp/service/searchByMap.jsp'">지도로 검색</button>
<button onclick="location.href='${pageContext.request.contextPath}/jsp/service/search.jsp'">검색</button>

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

<div>
    <!-- 이전페이지 링크 -->
    <c:if test="${startPage > 10}">
        <a href="${pageContext.request.contextPath}/service/guestMain?pageNum=${startPage - 1}">이전</a>
    </c:if>

    <!-- 페이지 번호 -->
    <c:forEach var="num" begin="${startPage}" end="${endPage}">
        <c:choose>
            <c:when test="${pageNum == num}">
                <span style="color:red">${num}</span>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/service/guestMain?pageNum=${num}">
                    <span style="color:gray">${num}</span>
                </a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <!-- 다음페이지 링크 -->
    <c:if test="${endPage < pageCount}">
        <a href="${pageContext.request.contextPath}/service/guestMain?pageNum=${endPage + 1}">다음</a>
    </c:if>
</div>

</body>
</html>
