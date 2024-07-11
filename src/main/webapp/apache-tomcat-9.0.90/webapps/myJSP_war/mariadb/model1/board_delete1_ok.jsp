<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="simple1.BoardDAO" %>
<%@ page import="simple1.BoardTO" %>

<%
	request.setCharacterEncoding( "utf-8" );

	BoardTO to = new BoardTO();	
	to.setSeq( request.getParameter( "seq" ) );
	to.setPassword( request.getParameter( "password" ) );
	
	BoardDAO dao = new BoardDAO();
	int flag = dao.boardDeleteOk( to );

	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		out.println( "alert('글삭제에 성공');" );
		out.println( "location.href='./board_list1.jsp';" );
	} else if( flag == 1 ) {
		out.println( "alert('비밀번호 오류');" );
		out.println( "history.back();" );
	} else {
		out.println( "alert('글삭제에 실패');" );
		out.println( "history.back();" );
	}
	out.println( "</script>" );
%>





