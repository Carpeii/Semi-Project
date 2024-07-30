<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<button onclick="location.href='../service/roomAdd.jsp'">방관리</button>
<button onclick="location.href='<%=request.getContextPath() %>/service/hostRoomList'">계약</button>
<button onclick="location.href='../auth/roomAdd.jsp'">채팅</button>
<button onclick="location.href='../auth/roomAdd.jsp'">정산</button>
<button onclick="location.href='../auth/roomAdd.jsp'">더보기</button>
<br><br>
<button onclick="location.href='../auth/roomAdd.jsp'">방 등록하기</button>

</body>
</html>