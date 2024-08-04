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
			
				<form>

					<table>
						<tr>
							<td>1주일</td>
							<td>2주일</td>
							<td>3주일</td>
						</tr>
						<tr>
							<td>4주일</td>
							<td>5주일</td>
							<td>6주일</td>
						</tr>
						<tr>
							<td>7주일</td>
							<td>8주일</td>
							<td>9주일</td>
						</tr>
						<tr>
							<td>10주일</td>
							<td>11주일</td>
							<td>12주일</td>
						</tr>
					</table>
				</form>

			</div>
		</div>
	</div>

</body>
</html>
