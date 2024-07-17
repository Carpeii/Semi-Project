<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<button onclick="location.href='../service/hostMain.jsp'">호스트 모드</button>
<!-- 로그인 안되어 있을때 -->
<button onclick="location.href='../auth/login.jsp'">로그인/회원가입</button>
<!-- 로그인 되어 있을때 -->
<button onclick="location.href='../auth/login.jsp'">로그인/회원가입</button>

<br><br>
<h2>단기임대, 1평에서 찾아보세요</h2>
<button onclick="location.href='../service/searchByMap.jsp'">지도로 검색</button>
<button onclick="location.href='../service/search.jsp'">검색</button>
</body>
</html>