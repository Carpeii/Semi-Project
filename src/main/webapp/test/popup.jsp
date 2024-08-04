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
				<form action="${pageContext.request.contextPath }/calendar/select" method="post">
					<table>
						<tr>
						<td>
							<c:if test="${dateDiff >= 7}">
					         	<button value="7" name="period">1주일</button>
							</c:if>
            			</td>
            			<td>
            				<c:if test="${dateDiff >= 14}">
								<button value="14" name="period">2주일</button>
							</c:if>
						</td>
						<td>
							<c:if test="${dateDiff >= 21}">
								<button value="21" name="period">3주일</button>
							</c:if>
						</td>
						</tr>
						<tr>
							<td>
							<c:if test="${dateDiff >= 28}">
								<button value="28" name="period">4주일</button>
							</c:if>
							</td>
							<td>
							<c:if test="${dateDiff >= 35}">
								<button value="35" name="period">5주일</button>
							</c:if>
							</td>
							<td>
							<c:if test="${dateDiff >= 42}">
								<button value="42" name="period">6주일</button>
							</c:if>
							</td>
						</tr>
						<tr>
							<td>
							<c:if test="${dateDiff >= 49}">
								<button value="49" name="period">7주일</button>
							</c:if>
							</td>
							<td>
							<c:if test="${dateDiff >= 63}">
								<button value="63" name="period">8주일</button>
							</c:if>
							</td>
							<td>
							<c:if test="${dateDiff >= 70}">
								<button value="70" name="period">9주일</button>
							</c:if>
							</td>
						</tr>
						<tr>
							<td>
							<c:if test="${dateDiff >= 77}">
								<button value="77" name="period">10주일</button>
							</c:if>
							</td>
							<td>
							<c:if test="${dateDiff >= 84}">
								<button value="84" name="period">11주일</button>
							</c:if>
							</td>
							<td>
							<c:if test="${dateDiff >= 91}">
								<button value="91" name="period">12주일</button>
							</c:if>
							</td>
						</tr>
					</table>
				</form>

			</div>
		</div>
		<div><button onclick="history.back(-1)" style="width:100px; height:50px;">취소</button></div>
	</div>

</body>
</html>
