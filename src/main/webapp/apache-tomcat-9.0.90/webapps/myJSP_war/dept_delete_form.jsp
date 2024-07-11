<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    request.setCharacterEncoding("utf-8");
    String deptno = request.getParameter("deptno");

    String dname="";
    String loc = "";

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:comp/env");
        DataSource dataSource = (DataSource) envContext.lookup("jdbc/mariadb1");

        conn = dataSource.getConnection();
        String sql = "select deptno, dname, loc from dept where deptno=?";
        pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, deptno);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            dname = rs.getString("dname");
            loc = rs.getString("loc");
        }
    }
    catch (NamingException e) {
        e.printStackTrace();
    }catch (SQLException e) {
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="./dept_delete_form_ok.jsp"  method="post">
<fieldset>
<label for="deptno">부서 번호</label>
<input type="text" id="deptno" name="deptno" value="<%=deptno%>" readonly/>

<br/><br/>

<label for="dname">부서 이름</label>
<input type="text" id="dname" name="dname" value="<%=dname%>"readonly/>

<br/><br/>

<label for="loc">부서위치</label>
<input type="text" id="loc" name="loc"value="<%=loc%>"readonly/>
<br/><br/>

<input type="submit" id="btn" value="내용입력" />
</fieldset>


</form>

</body>
</html>