<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="javax.naming.Context" %>
    <%@page import="javax.naming.InitialContext" %>
    <%@page import="javax.naming.NamingException" %>
    
    <%@page import="javax.sql.DataSource" %>
    
    <%@page import="java.sql.Connection" %>
    <%@page import="java.sql.PreparedStatement" %>
    <%@page import="java.sql.SQLException" %>
    
    
<% 
    request.setCharacterEncoding("utf-8");
    
    String deptno = request.getParameter("deptno");
    String dname = request.getParameter("dname");
    String loc = request.getParameter("loc");
   
   Connection conn = null;
   PreparedStatement pstmt = null;
   try {
       Context initCtx = new InitialContext();
       Context envCtx = (Context) initCtx.lookup("java:comp/env");
       DataSource dataSource = (DataSource) envCtx.lookup("jdbc/mariadb1");

       conn = dataSource.getConnection();

       String sql = "UPDATE dept SET dname =?, loc = ? WHERE deptno =?";
       pstmt = conn.prepareStatement(sql);
       pstmt.setString(1, dname);
       pstmt.setString(2, loc);
       pstmt.setString(3, deptno);

       int result = pstmt.executeUpdate();

       System.out.println("결과" + result);
   } catch(NamingException e) {
	   System.out.println("에러" + e.getMessage());
   } catch(SQLException e) {
	   System.out.println("에러" + e.getMessage());
   }finally {
       if (pstmt != null) conn.close();
       if (conn != null) conn.close();
   }
   
   response.sendRedirect("./dept_list.jsp");
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>