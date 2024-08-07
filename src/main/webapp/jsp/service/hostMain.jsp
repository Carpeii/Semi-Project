<%@ page import="com.mywebapp.dto.MemberDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>hostMain.jsp</title>
<link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<h2>�ܱ��Ӵ�, 1�򿡼� ã�ƺ�����</h2>
<%
    MemberDto user = (session != null) ? (MemberDto) session.getAttribute("user") : null;
%>
<%
    if (user != null) {
        System.out.println(user);
        // �α��� ���� ����
%>
<p>${sessionScope.user.userId}�� ȯ���մϴ�</p>
<button onclick="location.href='${pageContext.request.contextPath}/auth/logout'">�α׾ƿ�</button>
<button onclick="location.href='${pageContext.request.contextPath}/guestMain'">�Խ�Ʈ ��������</button>
<button onclick="location.href='${pageContext.request.contextPath}/service/hostRoomList'">�����</button>
<br><br>
<button onclick="location.href='${pageContext.request.contextPath}/service/roomAdd'">�� ����ϱ�</button>
<%--        <p>${userId}�� ȯ���մϴ�</p>--%>
<%
} else if (user == null && user.getMemberType() == 1) {
    System.out.println(user);
    // �α��� ���� ����
%>
<button onclick="location.href='${pageContext.request.contextPath}/auth/login'">�α���</button>
<button onclick="location.href='${pageContext.request.contextPath}/auth/join'">ȸ������</button>
<%
    }
%>


<jsp:include page="/jsp/common/footer.jsp"></jsp:include>

</body>
</html>