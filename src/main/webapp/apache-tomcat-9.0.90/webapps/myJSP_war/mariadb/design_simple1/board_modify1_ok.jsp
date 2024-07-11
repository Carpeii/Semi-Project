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
    
    String seq= request.getParameter("seq");
    String writer= request.getParameter("writer");
    String subject = request.getParameter("subject");
    
    String mail = "";
    if(!request.getParameter("mail1").equals("") && !request.getParameter("mail2").equals("")) {
    mail = request.getParameter("mail1")+ "@" + request.getParameter("mail2");
    }
    
    String password = request.getParameter("password");
    String content = request.getParameter("content");
    String wip = request.getRemoteAddr();
    
  Connection conn = null;
  PreparedStatement pstmt = null;
  
  int flag = 2;
    try{
    	Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource dataSource = (DataSource)envCtx.lookup("jdbc/mariadb1");
		 
		conn = dataSource.getConnection();
		 
		String sql = "update board1 set subject=?, mail=?, content=? where seq=? and password = password(?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,subject);
		pstmt.setString(2,mail);
		pstmt.setString(3,content);
		pstmt.setString(4,seq);
		pstmt.setString(5,password);
		
		 int result = pstmt.executeUpdate();
		 
		if(result == 0) {
			flag = 1;
		} else if(result == 1) {
			flag = 0;
		}
		
    } catch(NamingException e) {
    	System.out.println("에러" + e.getMessage());
    } catch(SQLException e) {
    	System.out.println("에러" + e.getMessage());
    }finally {
    	if(pstmt != null) pstmt.close();
    	if(conn != null) conn.close();
   	}
    
    out.println("<script type='text/javascript'>");
    if(flag == 0) {
    	out.println("alert('글수정 성공')");
    	out.println("location.href='./board_list1.jsp?seq="+ seq +"';");
    } else {
    	out.println("alert('글수정 실패')");
    	out.println("history.back();");
    }
    
    out.print("</script>");
  
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