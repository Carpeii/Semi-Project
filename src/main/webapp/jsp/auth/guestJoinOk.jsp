<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %><%--
  Created by IntelliJ IDEA.
  User: now
  Date: 2024. 7. 23.
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    request.setCharacterEncoding("UTF-8");

    String id = request.getParameter("id");
    String pw = request.getParameter("pw");

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    Context initCtx = new InitialContext();
    Context envCtx = (Context) initCtx.lookup("java:comp/env");
    DataSource dataSource = (DataSource) envCtx.lookup("jdbc/mariadb1");

    conn = dataSource.getConnection();



%>
