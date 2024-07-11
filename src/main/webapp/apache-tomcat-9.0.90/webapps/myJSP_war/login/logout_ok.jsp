<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.invalidate();
%>
<script type="text/javascript">
alert('로그아웃되었습니다.')
location.href='login_form.jsp';
</script>