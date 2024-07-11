<%@ page import="album.BoardDAO" %>
<%@ page import="album.BoardTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../css/board_view.css">
</head>
<%
	request.setCharacterEncoding( "utf-8" );

	String cpage = request.getParameter( "cpage" );
	String seq = request.getParameter( "seq" );

	String subject = "";
	String writer = "";
	String mail = "";
	String wip = "";
	String wdate = "";
	String hit = "";
	String content = "";
	String filename = "";
	BoardDAO dao = new BoardDAO();
	BoardTO to  = new BoardTO();

	to.setSeq(seq);
	to = dao.boardView(to);

	subject = to.getSubject();
	writer  = to.getWriter();
	mail = to.getMail();
	wip  = to.getWip();
	wdate  = to.getWdate();
	hit = to.getHit();
	content = to.getContent();
	filename = to.getFileName();

	StringBuilder sbHtml =new StringBuilder();

	BoardTO nextTo = new BoardTO();
	BoardTO previousTo = new BoardTO();

//	nextTo.setSeq(dao.getNextPost(to).getSeq());
//	previousTo.setSeq(dao.getPreviousPost(to).getSeq());
	previousTo = dao.getPreviousPost(to);
	nextTo = dao.getNextPost(to);

	sbHtml.append("<div class=\"next_data_area\">");
	if(nextTo.getSeq() == null || nextTo.getSeq().isEmpty()){
		sbHtml.append("<span class=\"b\">다음글 | </span><a>다음글이 없습니다.</a>");
	}else{
		sbHtml.append("<span class=\"b\">다음글 | </span><a href=\"board_view1.jsp?seq="+ nextTo.getSeq()+"\">"+nextTo.getSubject()+"</a>");
	}
	sbHtml.append("</div>");
	sbHtml.append("<div class=\"prev_data_area\">");
	if(previousTo.getSeq() == null || previousTo.getSeq().isEmpty()){
		sbHtml.append("<span class=\"b\">이전글 | </span><a>이전글이 없습니다.</a>");
	}else{
		sbHtml.append("<span class=\"b\">이전글 | </span><a href=\"board_view1.jsp?seq="+previousTo.getSeq()+"\">"+previousTo.getSubject()+"</a>");
	}
	sbHtml.append("</div>");
%>
<body>
<!-- 상단 디자인 -->
<div class="contents1"> 
	<div class="con_title"> 
		<p style="margin: 0px; text-align: right">
			<img style="vertical-align: middle" alt="" src="../../images/home_icon.gif" /> &gt; 커뮤니티 &gt; <strong>여행지리뷰</strong>
		</p>
	</div>

	<div class="contents_sub">	
	<!--게시판-->
		<div class="board_view">
			<table>
			<tr>
				<th width="10%">제목</th>
				<td width="60%"><%=subject%>(<%=wip%>)</td>
				<th width="10%">등록일</th>
				<td width="20%"><%=wdate%></td>
			</tr>
			<tr>
				<th>글쓴이</th>
				<td><%=writer%></td>
				<th>조회</th>
				<td><%=hit%></td>
			</tr>
			<tr>
				<td colspan="4" height="200" valign="top" style="padding:20px; line-height:160%">
					<div id="bbs_file_wrap">
						<div>
							<img src="../../upload/<%=filename%>" width="900" onerror="" /><br />
						</div>
					</div>
					<%=content%>
				</td>
			</tr>			
			</table>
		</div>
		<div class="btn_area">
			<div class="align_left">			
				<input type="button" value="목록" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='board_list1.jsp'" />
			</div>
			<div class="align_right">
				<input type="button" value="수정" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='board_modify1.jsp?seq=<%=seq%>'" />
				<input type="button" value="삭제" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='board_delete1.jsp?seq=<%=seq%>'" />
				<input type="button" value="쓰기" class="btn_write btn_txt01" style="cursor: pointer;" onclick="location.href='board_write1.jsp'" />
			</div>	
		</div>
		<!--//게시판-->
		
		<!-- 이전글 / 다음글 -->
		<%=sbHtml.toString()%>
<%--		<div class="next_data_area">--%>
<%--			<span class="b">다음글 | </span><a href="board_view1.jsp">다음글이 없습니다.</a>--%>
<%--		</div>--%>
<%--		<div class="prev_data_area">--%>
<%--			<span class="b">이전글 | </span><a href="board_view1.jsp">이전글이 없습니다.</a>--%>
<%--		</div>--%>
		<!-- //이전글 / 다음글 -->
	</div>
<!-- 하단 디자인 -->
</div>

</body>
</html>
