<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>join.jsp</title>
</head>
<body>
<h1>ȸ������</h1>
<h2>���� �̿��� ����</h2>
<h2>�̿����� �������ּ���</h2>
��������ó����ħ<button onclick="location.href='${pageContext.request.contextPath}/jsp/document/termPrivate.jsp'">��������ó����ħ �ڼ��� ����</button><br>
�̿���<button onclick="location.href='${pageContext.request.contextPath}/jsp/document/termUsing.jsp'">�̿��� �ڼ��� ����</button><br>
<button onclick="location.href='${pageContext.request.contextPath}/jsp/auth/joinForm.jsp'">��ü����</button>
</body>
</html>