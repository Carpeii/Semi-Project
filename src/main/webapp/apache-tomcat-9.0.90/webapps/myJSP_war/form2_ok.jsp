<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//request.setCharacterEncoding( "utf-8" );

	String data = request.getParameter( "data" );
	out.println( "form2 data : " + data );
%>