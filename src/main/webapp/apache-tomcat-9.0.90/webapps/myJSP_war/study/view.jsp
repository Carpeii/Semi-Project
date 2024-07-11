<%@ page import="jakarta.servlet.RequestDispatcher" %><%--
  Created by IntelliJ IDEA.
  User: kimjiwoong
  Date: 2024. 6. 19.
  Time: 오후 4:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    request.setCharacterEncoding("utf-8");
    String action = request.getParameter("action");
    RequestDispatcher dispatcher = null;

    if(action.equals("a")){
        dispatcher = request.getRequestDispatcher("./a.jsp");
    }else if(action.equals("b")){
        dispatcher = request.getRequestDispatcher("./b.jsp");
    }

%>