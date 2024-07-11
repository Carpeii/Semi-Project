<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>

<%@ page import="javax.sql.DataSource" %>

<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.SQLException" %>

<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.io.File" %>
<%@ page import="album.BoardDAO" %>
<%@ page import="album.BoardTO" %>

<%
	request.setCharacterEncoding("utf-8");
	String uploadPath = "/Users/kimjiwoong/javaprojects/myJSP/src/main/webapp/upload";
	int maxFileSize = 2 * 1024 * 1024;
	String encType = "utf-8";
	BoardTO to = new BoardTO();
	BoardDAO dao = new BoardDAO();

	MultipartRequest multi
		= new MultipartRequest( request, uploadPath, maxFileSize, encType, new DefaultFileRenamePolicy() );
	
	String subject = multi.getParameter( "subject" );
	String writer = multi.getParameter( "writer" );
	
	String mail = "";
	if( !multi.getParameter( "mail1" ).equals("") && !multi.getParameter( "mail2" ).equals("") ) {
		mail = multi.getParameter( "mail1" ) + "@" + multi.getParameter( "mail2" );
	}
	
	String password = multi.getParameter( "password" );
	String content = multi.getParameter( "content" );
	
	String wip = request.getRemoteAddr();
	
	String filename = multi.getFilesystemName( "upload" );
	long filesize = 0;
	if( multi.getFile( "upload" ) != null ) {
		filesize = multi.getFile( "upload" ).length();
	}

	to.setSubject(subject);
	to.setWriter(writer);
	to.setContent(content);
	to.setWip(wip);
	to.setPassword(password);
	to.setFileName(filename);
	to.setMail(mail);

	int flag = dao.boardWriteOk(to);

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






