<%@ page import="com.mywebapp.dto.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>hostMain.jsp</title>
</head>
<body>
<h2>�ܱ��Ӵ�, 1�򿡼� ã�ƺ�����</h2>
<%
    UserDto user = (session != null) ? (UserDto) session.getAttribute("user") : null;
%>
<%
    if (user != null) {
        System.out.println(user);
        // �α��� ���� ����
%>
<p>${sessionScope.user.userId}�� ȯ���մϴ�</p>
<button onclick="location.href='../auth/logout.jsp'">�α׾ƿ�</button>
<button onclick="location.href='../user/profile.jsp'">���� ������</button>
<button onclick="location.href='../auth/roomAdd.jsp'">�����</button>
<button onclick="location.href='../auth/roomAdd.jsp'">���</button>
<button onclick="location.href='../auth/roomAdd.jsp'">ä��</button>
<button onclick="location.href='../auth/roomAdd.jsp'">����</button>
<button onclick="location.href='../auth/roomAdd.jsp'">������</button>
<br><br>
<button onclick="location.href='../auth/roomAdd.jsp'">�� ����ϱ�</button>
<%--        <p>${userId}�� ȯ���մϴ�</p>--%>
<%
} else if (user == null) {
    System.out.println(user);
    // �α��� ���� ����
%>
<button onclick="location.href='../auth/loginMain.jsp'">�α���</button>
<button onclick="location.href='../auth/join.jsp'">ȸ������</button>
<%
    }
%>


</body>
</html>