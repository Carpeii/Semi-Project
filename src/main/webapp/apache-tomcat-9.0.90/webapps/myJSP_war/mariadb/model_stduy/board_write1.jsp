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
<script type="text/javascript">
	window.onload = function() {
		document.getElementById( 'wbtn' ).onclick = function() {
			if( document.wfrm.info.checked == false ) {
				alert( '동의하셔야 합니다.' );
				return false;
			}
			if( document.wfrm.writer.value.trim() == '' ) {
				alert( '글쓴이를 입력하셔야 합니다.' );
				return false;
			}
			if( document.wfrm.subject.value.trim() == '' ) {
				alert( '제목을 입력하셔야 합니다.' );
				return false;
			}
			if( document.wfrm.password.value.trim() == '' ) {
				alert( '비밀번호를 입력하셔야 합니다.' );
				return false;
			}
			document.wfrm.submit();
		};
	};
</script>
</head>

<body>
<!-- 상단 디자인 -->
<div class="con_title">
	<h3>게시판</h3>
	<p>HOME &gt; 게시판 &gt; <strong>게시판</strong></p>
</div>
<div class="con_txt">
	<form action="board_write1_ok.jsp" method="post" name="wfrm">
		<div class="contents_sub">	
			<!--게시판-->
			<div class="board_write">
				<table>
				<tr>
					<th class="top">글쓴이</th>
					<td class="top" colspan="3"><input type="text" name="writer" value="" class="board_view_input_mail" maxlength="5" /></td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="3"><input type="text" name="subject" value="" class="board_view_input" /></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td colspan="3"><input type="password" name="password" value="" class="board_view_input_mail"/></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3"><textarea name="content" class="board_editor_area"></textarea></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td colspan="3"><input type="text" name="mail1" value="" class="board_view_input_mail"/> @ <input type="text" name="mail2" value="" class="board_view_input_mail"/></td>
				</tr>
				<tr>
					<th>이모티콘</th>
					<td colspan="3" align="center">
						<table>
						<tr>
							<td>
								<img src="../../images/emoticon/emot01.png" width="25"/><br />
								<input type="radio" name="emot" value="emot01" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot02.png" width="25" /><br />
								<input type="radio" name="emot" value="emot02" class="input_radio" checked="checked"/>
							</td>
							<td>
								<img src="../../images/emoticon/emot03.png" width="25" /><br />
								<input type="radio" name="emot" value="emot03" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot04.png" width="25" /><br />
								<input type="radio" name="emot" value="emot04" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot05.png" width="25" /><br />
								<input type="radio" name="emot" value="emot05" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot06.png" width="25" /><br />
								<input type="radio" name="emot" value="emot06" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot07.png" width="25" /><br />
								<input type="radio" name="emot" value="emot07" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot08.png" width="25" /><br />
								<input type="radio" name="emot" value="emot08" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot09.png" width="25" /><br />
								<input type="radio" name="emot" value="emot09" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot10.png" width="25" /><br />
								<input type="radio" name="emot" value="emot10" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot11.png" width="25"/><br />
								<input type="radio" name="emot" value="emot11" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot12.png" width="25" /><br />
								<input type="radio" name="emot" value="emot12" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot13.png" width="25" /><br />
								<input type="radio" name="emot" value="emot13" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot14.png" width="25" /><br />
								<input type="radio" name="emot" value="emot14" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot15.png" width="25" /><br />
								<input type="radio" name="emot" value="emot15" class="input_radio" />
							</td>
						</tr>
						<tr>
							<td>
								<img src="../../images/emoticon/emot16.png" width="25"/><br />
								<input type="radio" name="emot" value="emot16" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot17.png" width="25" /><br />
								<input type="radio" name="emot" value="emot17" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot18.png" width="25" /><br />
								<input type="radio" name="emot" value="emot18" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot19.png" width="25" /><br />
								<input type="radio" name="emot" value="emot19" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot20.png" width="25" /><br />
								<input type="radio" name="emot" value="emot20" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot21.png" width="25" /><br />
								<input type="radio" name="emot" value="emot21" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot22.png" width="25" /><br />
								<input type="radio" name="emot" value="emot22" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot23.png" width="25" /><br />
								<input type="radio" name="emot" value="emot23" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot24.png" width="25" /><br />
								<input type="radio" name="emot" value="emot24" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot25.png" width="25"/><br />
								<input type="radio" name="emot" value="emot25" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot26.png" width="25" /><br />
								<input type="radio" name="emot" value="emot26" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot27.png" width="25" /><br />
								<input type="radio" name="emot" value="emot27" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot28.png" width="25" /><br />
								<input type="radio" name="emot" value="emot28" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot29.png" width="25" /><br />
								<input type="radio" name="emot" value="emot29" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot30.png" width="25" /><br />
								<input type="radio" name="emot" value="emot30" class="input_radio" />
							</td>
						</tr>
						<tr>
							<td>
								<img src="../../images/emoticon/emot31.png" width="25"/><br />
								<input type="radio" name="emot" value="emot31" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot32.png" width="25" /><br />
								<input type="radio" name="emot" value="emot32" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot33.png" width="25" /><br />
								<input type="radio" name="emot" value="emot33" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot34.png" width="25" /><br />
								<input type="radio" name="emot" value="emot34" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot35.png" width="25" /><br />
								<input type="radio" name="emot" value="emot35" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot36.png" width="25" /><br />
								<input type="radio" name="emot" value="emot36" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot37.png" width="25" /><br />
								<input type="radio" name="emot" value="emot37" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot38.png" width="25" /><br />
								<input type="radio" name="emot" value="emot38" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot39.png" width="25" /><br />
								<input type="radio" name="emot" value="emot39" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot40.png" width="25"/><br />
								<input type="radio" name="emot" value="emot40" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot41.png" width="25" /><br />
								<input type="radio" name="emot" value="emot41" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot42.png" width="25" /><br />
								<input type="radio" name="emot" value="emot42" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot43.png" width="25" /><br />
								<input type="radio" name="emot" value="emot43" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot44.png" width="25" /><br />
								<input type="radio" name="emot" value="emot44" class="input_radio" />
							</td>
							<td>
								<img src="../../images/emoticon/emot45.png" width="25" /><br />
								<input type="radio" name="emot" value="emot45" class="input_radio" />
							</td>
						</tr>
						</table>
					</td>
				</tr>
				</table>
				
				<table>
				<tr>
					<br />
					<td style="text-align:left;border:1px solid #e0e0e0;background-color:f9f9f9;padding:5px">
						<div style="padding-top:7px;padding-bottom:5px;font-weight:bold;padding-left:7px;font-family: Gulim,Tahoma,verdana;">※ 개인정보 수집 및 이용에 관한 안내</div>
						<div style="padding-left:10px;">
							<div style="width:97%;height:95px;font-size:11px;letter-spacing: -0.1em;border:1px solid #c5c5c5;background-color:#fff;padding-left:14px;padding-top:7px;">
								1. 수집 개인정보 항목 : 회사명, 담당자명, 메일 주소, 전화번호, 홈페이지 주소, 팩스번호, 주소 <br />
								2. 개인정보의 수집 및 이용목적 : 제휴신청에 따른 본인확인 및 원활한 의사소통 경로 확보 <br />
								3. 개인정보의 이용기간 : 모든 검토가 완료된 후 3개월간 이용자의 조회를 위하여 보관하며, 이후 해당정보를 지체 없이 파기합니다. <br />
								4. 그 밖의 사항은 개인정보취급방침을 준수합니다.
							</div>
						</div>
						<div style="padding-top:7px;padding-left:5px;padding-bottom:7px;font-family: Gulim,Tahoma,verdana;">
							<input type="checkbox" name="info" value="1" class="input_radio"> 개인정보 수집 및 이용에 대해 동의합니다.
						</div>
					</td>
				</tr>
				</table>
			</div>
			
			<div class="btn_area">
				<div class="align_left">
					<input type="button" value="목록" class="btn_list btn_txt02" style="cursor: pointer;" onclick="location.href='board_list1.jsp'" />
				</div>
				<div class="align_right">
					<input type="button" id="wbtn" value="쓰기" class="btn_write btn_txt01" style="cursor: pointer;" />
				</div>
			</div>
			<!--//게시판-->
		</div>
	</form>
</div>
<!-- 하단 디자인 -->

</body>
</html>
