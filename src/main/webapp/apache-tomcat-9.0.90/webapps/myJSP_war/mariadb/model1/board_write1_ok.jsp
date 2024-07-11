<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="simple1.BoardDAO" %>
<%@ page import="simple1.BoardTO" %>

<%
	request.setCharacterEncoding( "utf-8" );

	BoardTO to = new BoardTO();
	to.setSubject( request.getParameter( "subject" ) );
	to.setWriter( request.getParameter( "writer" ) );
	
	to.setMail( "" );
	if( !request.getParameter( "mail1" ).equals("") && !request.getParameter( "mail2" ).equals("") ) {
		to.setMail( request.getParameter( "mail1" ) + "@" + request.getParameter( "mail2" ) );
	}
	
	to.setPassword( request.getParameter( "password" ) );
	to.setContent( request.getParameter( "content" ) );
	to.setWip( request.getRemoteAddr() );

	BoardDAO dao = new BoardDAO();
	int flag = dao.boardWriteOk( to );
	
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		out.println( "alert('글쓰기에 성공');" );
		out.println( "location.href='./board_list1.jsp';" );
	} else {
		out.println( "alert('글쓰기에 실패');" );
		out.println( "history.back();" );
	}
	out.println( "</script>" );
%>