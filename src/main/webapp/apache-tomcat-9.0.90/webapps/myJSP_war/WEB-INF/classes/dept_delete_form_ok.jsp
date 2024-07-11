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

    //1에러
    //0정상
    int flag = 1;
   try {
       Context initCtx = new InitialContext();
       Context envCtx = (Context) initCtx.lookup("java:comp/env");
       DataSource dataSource = (DataSource) envCtx.lookup("jdbc/mariadb1");

       conn = dataSource.getConnection();

       String sql = "delete from dept where deptno = ?";
       pstmt = conn.prepareStatement(sql);
       pstmt.setString(1, deptno);

       int result = pstmt.executeUpdate();
       System.out.println(result);
       if (result == 1) {
           flag = 0;
       }else {
           flag = 1;
       }

       System.out.println("결과" + result);
   }catch(NamingException e) {
	   System.out.println("에러" + e.getMessage());
   }catch(SQLException e) {
	   System.out.println("에러" + e.getMessage());
   }finally{
    if(pstmt != null) conn.close();
	if(conn != null) conn.close();
   }
   out.println("<script tpye='text/javascript'>");
   if(flag == 0){
       //정상
       out.println("alert ('부서 삭제 성공')");
       response.sendRedirect("./dept_list.jsp");
   }else {
       out.println("alert('부서 삭제 실패');");
       out.println("history.back();");
   }
   out.println("</script>");
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