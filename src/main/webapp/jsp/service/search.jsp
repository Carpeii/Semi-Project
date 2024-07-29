    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!-- 
 검색된 결과들 보이기 
asdasdsadas
 결과중 하나 선택 
<button onclick="location.href='../service/detail.jsp'">이태원루프탑 STAY</button>
 -->
 <a href="${pageContext.request.contextPath }/search.jsp?searchWord=${param.searchWord}&cpage=${-1}">이전페이지</a>
 <table>
 <c:forEach var="room" items="${requestScope.rooms}">
			<tr>
			<td>방 주소 : ${room.jibunAddress }........${room.roomPrice.rentPrice}</td> 
			
			
			</tr>
		 </c:forEach>
 </table>
 <c:if test="">
 <a href="">이전페이지</a>
 </c:if>
 

</body>
</html>