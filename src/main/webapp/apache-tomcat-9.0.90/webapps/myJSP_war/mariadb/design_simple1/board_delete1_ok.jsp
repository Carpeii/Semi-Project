<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("utf-8");
    String seq = request.getParameter("seq");
    String password = request.getParameter("password");

    PreparedStatement pstmt = null;
    Connection conn= null;
    // 0 정상 1 비번 틀림 2 에러
    int flag = 1;
    try {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource dataSource = (DataSource) envCtx.lookup("jdbc/mariadb1");

        conn = dataSource.getConnection();

        String sql = "DELETE FROM board1 WHERE seq = ? and password = password(?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, seq);
        pstmt.setString(2, password);
        int result = pstmt.executeUpdate();

        if (result == 0) {
            flag = 1;
        }else if(result == 1){
            flag = 0;
        }else {
            flag = 2;
        }
    }
    catch (NamingException e) {
        e.printStackTrace();
    }catch (SQLException e) {
        e.printStackTrace();
    }finally {
        if(pstmt != null) {
            pstmt.close();
        }
        if(conn != null) {
            conn.close();
        }
    }
    out.println("<script type='text/javascript'>");
    if(flag==0){
        System.out.println("정상");
        out.println("alert('글삭제 성공');");
        out.println("location.href='./board_list1.jsp';");
    }
    else if(flag==1){
        out.println("alert('비번 오류');");
        out.println("history.back();");
    }else {
        out.println("alert('글 삭제 실패');");
        out.println("history.back();");
    }
    out.println("</script>");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
