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

<%
	request.setCharacterEncoding("UTF-8");
	int cpage = 1;

	if (request.getParameter("cpage") != null && request.getParameter("cpage").equals("")) {
		cpage = Integer.parseInt(request.getParameter("cpage"));
	}
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	int totalRecord = 0;

	int recordPerPage = 10;
	int totalPage = 0;
	int blockPerPage =5;
	
	StringBuilder sbHtml = new StringBuilder();
	try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
		DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb1" );
		
		conn = dataSource.getConnection();
		
		String sql = "select seq, subject, writer, emot, date_format(wdate, '%Y-%m-%d') wdate, hit, datediff(now(), wdate) wgap from emot_board1 order by seq desc";
		pstmt = conn.prepareStatement( sql );
		
		rs = pstmt.executeQuery();
		
		rs.last();
		totalRecord = rs.getRow();
		rs.beforeFirst();
		
		rs = pstmt.executeQuery();

		//읽을 위치 지정
		int skip = (cpage-1)*recordPerPage;
		//읽을위치로 커서 이동
		if(skip!=0){
			rs.absolute(skip);
		}
		for(int i = 0; i < recordPerPage && rs.next(); i++) {
			String seq = rs.getString( "seq" );
			String subject = rs.getString( "subject" );
			String writer = rs.getString( "writer" );
			String emot = rs.getString( "emot" );
			String wdate = rs.getString( "wdate" );
			String hit = rs.getString( "hit" );
			int wgap = rs.getInt( "wgap" );
			
			sbHtml.append( "<tr>" );
			sbHtml.append( "<td><img src='../../images/emoticon/emot" + emot + ".png' width='15' /></td>" );
			sbHtml.append( "<td>" + seq + "</td>" );
			sbHtml.append( "<td class='left'>" );
			sbHtml.append( "	<a href='board_view1.jsp?seq=" + seq + "'>" + subject + "</a>&nbsp;" );
			
			if( wgap == 0 ) {
				sbHtml.append( "	<img src='../../images/icon_new.gif' alt='NEW'>" );
			}
			
			sbHtml.append( "</td>");
			sbHtml.append( "<td>" + writer + "</td>" );
			sbHtml.append( "<td>" + wdate + "</td>" );
			sbHtml.append( "<td>" + hit + "</td>" );
			sbHtml.append( "<td>&nbsp;</td>" );
			sbHtml.append( "</tr>" );
		}
		
	} catch( NamingException e ) {
		System.out.println( "[에러] " + e.getMessage() );
	} catch( SQLException e ) {
		System.out.println( "[에러] " + e.getMessage() );
	} finally {
		if( rs != null ) rs.close();
		if( pstmt != null ) pstmt.close();
		if( conn != null ) conn.close();
	}
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../css/board.css">
</head>

<body>
<!-- 상단 디자인 -->
<div class="con_title">
	<h3>게시판</h3>
	<p>HOME &gt; 게시판 &gt; <strong>게시판</strong></p>
</div>
<div class="con_txt">
	<div class="contents_sub">
		<div class="board_top">
			<div class="bold">총 <span class="txt_orange"><%=totalRecord %></span>건</div>
		</div>

		<!--게시판-->
		<div class="board">
			<table>
			<tr>
				<th width="3%">&nbsp;</th>
				<th width="5%">번호</th>
				<th>제목</th>
				<th width="10%">글쓴이</th>
				<th width="17%">등록일</th>
				<th width="5%">조회</th>
				<th width="3%">&nbsp;</th>
			</tr>
<!-- 시작 -->
<%=sbHtml %>
<!-- 끝  -->

			</table>
		</div>

		<div class="btn_area">
			<div class="align_right">
				<input type="button" value="쓰기" class="btn_write btn_txt01"
					   style="cursor: pointer;"
					   onclick="location.href='board_write1.jsp?cpage=<%=cpage %>'" />
			</div>

			<%
				int startBlock = cpage - (cpage - 1) % blockPerPage;
				int endBlock = cpage - (cpage - 1) % blockPerPage + blockPerPage - 1;
				if (endBlock >= totalPage) {
					endBlock = totalPage;
				}
			%>
			<!--페이지넘버-->
			<div class="paginate_regular">
				<div align="absmiddle">
					<%
						if (startBlock == 1) {
							out.println("<span><a>&lt;&lt;</a></span>");
						} else {
							out.println("<span><a href='board_list1.jsp?cpage="+(startBlock - blockPerPage)+"'>&lt;&lt;</a></span>");
						}
						out.println("&nbsp;");

						if (cpage == 1) {
							out.println("<span><a>&lt;</a></span>");
						} else {
							out.println("<span><a href='board_list1.jsp?cpage=" + (cpage - 1) + "'>&lt;</a></span>");
						}
						out.println("&nbsp;&nbsp;");

						for (int i = startBlock; i <= endBlock; i++) {
							if (i == cpage) {
								out.println("<span><a>[" + i + "]</a></span>");
							} else {
								out.println("<span><a href= 'board_list1.jsp?cpage=" + i + "'>" + i + "</a></span>");
							}
						}

						out.println("&nbsp;&nbsp;");
						if (cpage == totalPage) {
							out.println("<span><a>&gt;</a></span>");
						} else {
							out.println("<span><a href = 'board_list1.jsp?cpage=" + (cpage + 1) + "'>&gt;</a></span>");
						}

						if (endBlock == totalPage) {
							out.println("<span><a>&gt;&gt;</a></span>");
						} else {
							out.println("<span><a href='./board_list1.jsp?cpage=" + ( startBlock + blockPerPage) + "'>&gt;&gt;</a></span>");
						}

					%>
					&nbsp;&nbsp;
					&nbsp;
				</div>
			</div>
			<!--//페이지넘버-->
		</div>
		<!--//게시판-->

	</div>
</div>
<!--//하단 디자인 -->

</body>
</html>
