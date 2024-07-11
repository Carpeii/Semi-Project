<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../css/board.css">
</head>
 <%
	request.setCharacterEncoding("utf-8");
	PreparedStatement pstmt = null;
	Connection conn= null;
	 ResultSet rs =null;
	 StringBuilder sbHtml = new StringBuilder();
	 int totalRecords = 0;
	try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource dataSource = (DataSource) envCtx.lookup("jdbc/mariadb1");

		conn = dataSource.getConnection();
		//date_format(wdate,'%Y-%m-%d') wdate, hit, datediff(now(), wdate) as wgap
		String sql = "SELECT seq, subject, writer, date_format(wdate,'%Y-%m-%d') wdate, hit, datediff(now(), wdate) as wgap FROM board1 order by seq desc";
		pstmt = conn.prepareStatement(sql);

		rs = pstmt.executeQuery();

		rs.last();
		totalRecords = rs.getRow();
		rs.beforeFirst();

		while (rs.next()) {
//			System.out.println(rs.getString("seq"));
			String seq = rs.getString("seq");
			String subject = rs.getString("subject");
			String writer = rs.getString("writer");
			String wdate = rs.getString("wdate");
			String hit = rs.getString("hit");
			int wgap = rs.getInt("wgap");
			sbHtml.append("<tr>");
			sbHtml.append("<td>&nbsp;</td>");
			sbHtml.append("<td>"+seq+"</td>");
			sbHtml.append("<td class=\"left\"><a href=\"board_view1.jsp?seq="+ seq +"\">" + subject + "</a>");
			if(wgap ==0){
				sbHtml.append("&nbsp;<img src=\"../../images/icon_new.gif\" alt=\"NEW\">");
			}
			sbHtml.append("</td>");
			sbHtml.append("<td>"+writer+"</td>");
			sbHtml.append("<td>"+wdate+"</td>");
			sbHtml.append("<td>" +hit +"</td>");
			sbHtml.append("<td>&nbsp;</td>");
			sbHtml.append("</tr>");
		}
	}
	catch (NamingException e) {
		e.printStackTrace();
	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		if(pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
	}
 %>
<body>
<!-- 상단 디자인 -->
<div class="con_title">
	<h3>게시판</h3>
	<p>HOME &gt; 게시판 &gt; <strong>게시판</strong></p>
</div>
<div class="con_txt">
	<div class="contents_sub">
		<div class="board_top">
			<div class="bold">총 <span class="txt_orange"><%=totalRecords%></span>건</div>
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
				<%= sbHtml.toString()%>
			</table>
		</div>	

		<div class="btn_area">
			<div class="align_right">
				<input type="button" value="쓰기" class="btn_write btn_txt01" style="cursor: pointer;" onclick="location.href='board_write1.jsp'" />
			</div>
		</div>
		<!--//게시판-->
	</div>
</div>
<!--//하단 디자인 -->

</body>
</html>
