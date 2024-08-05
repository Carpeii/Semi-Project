<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>join.jsp</title>
</head>
<body>
<h1>회원가입</h1>
<h2>서비스 이용을 위한</h2>
<h2>이용약관에 동의해주세요</h2>
개인정보처리방침<button onclick="location.href='${pageContext.request.contextPath}/jsp/document/termPrivate.jsp'">개인정보처리방침 자세히 보기</button><br>
이용약관<button onclick="location.href='${pageContext.request.contextPath}/jsp/document/termUsing.jsp'">이용약관 자세히 보기</button><br>
<button onclick="location.href='${pageContext.request.contextPath}/jsp/auth/joinForm.jsp'">전체동의</button>
</body>
</html>