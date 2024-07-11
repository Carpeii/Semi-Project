<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="static org.apache.coyote.http11.Constants.a" %>
<%@ page import="javax.naming.NamingException" %>
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
	PreparedStatement preparedStatement = null;
	Connection connection = null;
	ResultSet resultSet = null;
	StringBuilder stringBuilderHtml = new StringBuilder();
	String seq ="";
	String subject="";
	String writer="";
	String emot="";
	String hit="";
	String wdate="";
	int totalRecord= 0;
	try {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup( "java:comp/env" );
		DataSource dataSource = (DataSource)envCtx.lookup( "jdbc/mariadb1" );
		connection = dataSource.getConnection();

		String sql = "SELECT seq, subject, writer, emot, date_format(wdate, '%Y-%m-%d') wdate, hit, datediff(now(), wdate) wgap from emot_board1 order by seq desc";
		preparedStatement = connection.prepareStatement(sql);

		resultSet = preparedStatement.executeQuery();

		resultSet.last();
		totalRecord = resultSet.getRow();
		resultSet.beforeFirst();

		while (resultSet.next()) {
			seq = resultSet.getString("seq");
			subject = resultSet.getString("subject");
			writer = resultSet.getString("writer");
			emot = resultSet.getString("emot");
			hit = resultSet.getString("hit");
			wdate = resultSet.getString("wdate");
			int wgap = resultSet.getInt( "wgap" );

			stringBuilderHtml.append("<tr>");
			stringBuilderHtml.append("<td><img src=\"../../images/emoticon/emot" +emot + ".png\" width=\"15\"/></td>");
			stringBuilderHtml.append("<td>"+seq+"</td>");

			stringBuilderHtml.append("<td class=\"left\"><a href=\"board_view1.jsp?seq="+seq+"\">"+subject+"</a>&nbsp;");
			if(wgap ==0){
				stringBuilderHtml.append("<img src=\"../../images/icon_new.gif\" alt=\"NEW\">");
			}
			stringBuilderHtml.append("</td>");
			stringBuilderHtml.append("<td>"+writer+"</td>");
			stringBuilderHtml.append("<td>"+wdate+"</td>");
			stringBuilderHtml.append("<td>"+hit+"</td>");
			stringBuilderHtml.append("<td>&nbsp;</td>");
			stringBuilderHtml.append("</tr>");
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}catch (NamingException e){
		e.printStackTrace();
	}finally {
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
		if (resultSet != null) {
			resultSet.close();
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
			<div class="bold">총 <span class="txt_orange"><%=totalRecord%></span>건</div>
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
			<%=stringBuilderHtml.toString()%>
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
