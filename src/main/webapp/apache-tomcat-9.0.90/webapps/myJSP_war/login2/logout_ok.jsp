<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Cookie cookie1 = new Cookie("cid", "");
	Cookie cookie2 = new Cookie("cgrade", "");
	cookie1.setMaxAge(0);
	cookie2.setMaxAge(0);

	response.addCookie(cookie1);
	response.addCookie(cookie2);
%>
<script type="text/javascript">
alert('로그아웃되었습니다.')
location.href='login_form.jsp';
</script>