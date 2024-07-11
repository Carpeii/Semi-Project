<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!--  context 파일을 읽는 import -->
    <%@page import="javax.naming.Context" %>
    <%@page import="javax.naming.InitialContext" %>
    <%@page import="javax.naming.NamingException" %>

    <%@page import="javax.sql.DataSource" %>

    <%@page import="java.sql.Connection" %>
    <%@page import="java.sql.PreparedStatement" %>
    <%@page import="java.sql.ResultSet" %>
    <%@page import="java.sql.SQLException" %>

<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StringBuilder sbHtml = new StringBuilder();
	try{
	Context initCtx = new InitialContext();
	Context envCtx = (Context)initCtx.lookup("java:comp/env");
	DataSource dataSource = (DataSource)envCtx.lookup("jdbc/mariadb1");

	conn = dataSource.getConnection();
	System.out.println("연결성공");

	String sql ="select deptno, dname, loc from dept";
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	sbHtml.append("<table width='800' border='1'>");
	while(rs.next()) {
		sbHtml.append("<tr>");
		sbHtml.append("<td>" + rs.getString("deptno") + "</td>");
		sbHtml.append("<td>" + rs.getString("dname") + "</td>");
		sbHtml.append("<td>" + rs.getString("loc") + "</td>");
		sbHtml.append("<td><a href='./dept_delete_form.jsp?deptno=" + rs.getString("deptno")+"'>삭제</a></td>");
		sbHtml.append("<td><a href='./dept_update_form.jsp?deptno=" + rs.getString("deptno")+"'>수정</a></td>");
	}
	sbHtml.append("</table>");


	}catch(NamingException e) {
		System.out.println("에러" + e.getMessage());
	}catch (SQLException e) {
		System.out.println("에러" + e.getMessage());
	} finally {
		if(rs!= null) rs.close();
		if(pstmt != null) conn.close();
		if(conn != null) conn.close();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="dept_insert_form.jsp">내용입력</a>
<hr/>
<%=sbHtml.toString() %>

</body>
</html>