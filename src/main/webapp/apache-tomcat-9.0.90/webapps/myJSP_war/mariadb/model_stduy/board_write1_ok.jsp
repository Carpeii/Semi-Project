<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>

<%@ page import="javax.sql.DataSource" %>

<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="study.BoardDao" %>
<%@ page import="study.BoardTo" %>

<%
	request.setCharacterEncoding( "utf-8" );

	BoardDao board = new BoardDao();
	BoardTo to = new BoardTo();

	to.setSubject(request.getParameter( "subject" ));
	to.setWriter(request.getParameter( "writer" ));
	String mail = "";
	if(!request.getParameter( "mail1" ).equals( "" ) && !request.getParameter( "mail2" ).equals( "" ) ) {
		mail = request.getParameter( "mail1" ) + "@" + request.getParameter( "mail2" );
	}
	to.setMail(mail);
	to.setPassword(request.getParameter( "password" ));
	to.setContent(request.getParameter( "content" ));
	to.setEmot(request.getParameter( "emot" ).replaceAll( "emot", "" ));
	String wip = request.getRemoteAddr();

	to.setWip(wip);

	int flag = board.boardWriteOk(to);
		
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		out.println( "alert('글쓰기에 성공');" );
		out.println( "location.href='board_list1.jsp';" );
	} else if( flag == 1 ) {
		out.println( "alert('글쓰기에 실패');" );
		out.println( "history.back();" );
	}
	out.println( "</script>" );		
%>