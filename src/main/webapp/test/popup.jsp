<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/calendar.css">
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
<script
	src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>

<title>Insert title here</title>

<% 
int a = 0;
if(request.getParameter("a") != null) {
a = Integer.parseInt(request.getParameter("a"));
}
%>
<%
if(a > 0){
%>	
<script type="text/javascript">
	self.close();
</script>
<% 	} %> 
	

</head>
<body>
	<div class="container mt-4 sm custom-container">
		<div class="row">

			<div class="col">
				<form action="${pageContext.request.contextPath }/calendar/move"
					method="post">
					<c:out value="${sb}" escapeXml="false" />
				</form>
			</div>
				
			<div class="col">
				<form action="${pageContext.request.contextPath }/calendar/select" method="post" id="periodfrm"  target="_parent" onsubmit="return closepopup(event);" class="mb-5">
					<table class="table custom-table" style="border:none; ">
						<tr>
							<c:if test="${dateDiff >= 7}">
						<td class="noborder">
					         	<button  class="w-100 h-100 btn btn-outline-primary" value="7" name="period">1주일</button>
            			</td>
							</c:if>
            				<c:if test="${dateDiff >= 14}">
            			<td class="noborder">
								<button class="w-100 h-100 btn btn-outline-primary" value="14" name="period">2주일</button>
						</td>
							</c:if>
							<c:if test="${dateDiff >= 21}">
						<td class="noborder">
								<button class="w-100 h-100 btn btn-outline-primary" value="21" name="period">3주일</button>
						</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${dateDiff >= 28}">
							<td class="noborder">
								<button class="w-100 h-100 btn btn-outline-primary" value="28" name="period">4주일</button>
							</td>
							</c:if>
							<c:if test="${dateDiff >= 35}">
							<td class="noborder">
								<button class="w-100 h-100 btn btn-outline-primary" value="35" name="period">5주일</button>
							</td>
							</c:if>
							<c:if test="${dateDiff >= 42}">
							<td class="noborder">
								<button class="w-100 h-100 btn btn-outline-primary" value="42" name="period">6주일</button>
							</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${dateDiff >= 49}">
							<td class="noborder">
								<button class="w-100 h-100 btn btn-outline-primary" value="49" name="period">7주일</button>
							</td>
							</c:if>
							<c:if test="${dateDiff >= 63}">
							<td class="noborder">
								<button class="w-100 h-100 btn btn-outline-primary" value="63" name="period">8주일</button>
							</td>
							</c:if>
							<c:if test="${dateDiff >= 70}">
							<td class="noborder">
								<button class="w-100 h-100 btn btn-outline-primary" value="70" name="period">9주일</button>
							</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${dateDiff >= 77}">
							<td class="noborder">
								<button class="w-100 h-100 btn btn-outline-primary" value="77" name="period">10주일</button>
							</td>
							</c:if>
							<c:if test="${dateDiff >= 84}">
							<td class="noborder">
								<button class="w-100 h-100 btn btn-outline-primary" value="84" name="period">11주일</button>
							</td>
							</c:if>
							<c:if test="${dateDiff >= 91}">
							<td class="noborder">
								<button class="w-100 h-100 btn btn-outline-primary" value="91" name="period">12주일</button>
							</td>
							</c:if>
						</tr>
					</table>
				</form>

			</div>
		</div>
	</div>
		<div><button class="btn btn-outline-success" onclick=" opener.location.reload();self.close();" style="width:100px; height:50px;">확인</button></div>
<script>

</script>
</body>
</html>
