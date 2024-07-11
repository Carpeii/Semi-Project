<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>

<%@ page import="javax.sql.DataSource" %>

<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>

<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.io.File" %>
<%@ page import="album.BoardDAO" %>
<%@ page import="album.BoardTO" %>

<%
	request.setCharacterEncoding("utf-8");
	String uploadPath = "/Users/kimjiwoong/javaprojects/myJSP/src/main/webapp/upload";
	int maxFileSize = 2 * 1024 * 1024;
	String encType = "utf-8";

	MultipartRequest multi
			= new MultipartRequest( request, uploadPath, maxFileSize, encType, new DefaultFileRenamePolicy() );

	String seq = multi.getParameter( "seq" );
	String subject = multi.getParameter( "subject" );
	String mail = "";
	if( !multi.getParameter( "mail1" ).equals( "" ) && !multi.getParameter( "mail2" ).equals( "" ) ) {
		mail = multi.getParameter( "mail1" ) + "@" + multi.getParameter( "mail2" );
	}
	String password = multi.getParameter( "password" );
	String content = multi.getParameter( "content" );
	
	String newfilename = multi.getFilesystemName( "upload" );
	BoardDAO dao = new BoardDAO();
	BoardTO to = new BoardTO();

	to.setSeq(seq);
	to.setSubject(subject);
	to.setMail(mail);
	to.setPassword(password);
	to.setContent(content);

	int flag= dao.boardModifyOk(to, newfilename);
	
	out.println( "<script type='text/javascript'>" );
	if( flag == 0 ) {
		out.println( "alert('글수정에 성공');" );
		out.println( "location.href='./board_view1.jsp?seq=" + seq + "';" );
	} else if( flag == 1 ){
		out.println( "alert('비밀번호 오류');" );
		out.println( "history.back();" );
	} else {
		out.println( "alert('글수정에 실패');" );
		out.println( "history.back();" );
	}
	out.println( "</script>" );
%>




