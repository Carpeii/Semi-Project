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

<%
	String uploadPath = "C:/Java/jsp-workspace/UploadBoardEx01/src/main/webapp/upload";
	int maxFileSize = 2 * 1024 * 1024;
	String encType = "utf-8";
	
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
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	int flag = 1;
	try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
		DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb2" );
	
		conn = dataSource.getConnection();
	
		String sql = "insert into pds_board1 values ( 0, ?, ?, ?, password( ? ), ?, ?, ?, 0, ?, now() )";
		pstmt = conn.prepareStatement( sql );
		pstmt.setString( 1, subject );
		pstmt.setString( 2, writer );
		pstmt.setString( 3, mail );
		pstmt.setString( 4, password );
		pstmt.setString( 5, content );
		pstmt.setString( 6, filename );
		pstmt.setLong( 7, filesize );
		pstmt.setString( 8, wip );
	
		if( pstmt.executeUpdate() == 1 ) {
			flag = 0;
		}
	} catch( NamingException e ) {
		System.out.println( "[에러] " + e.getMessage() );
	} catch( SQLException e ) {
		System.out.println( "[에러] " + e.getMessage() );
	} finally {
		if( pstmt != null ) pstmt.close();
		if( conn != null ) conn.close();
	}
	
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






