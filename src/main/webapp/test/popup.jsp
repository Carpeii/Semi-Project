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
				
			<div class="col mb-5">
				<form action="${pageContext.request.contextPath }/calendar/select" method="post">
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
		<div class="mt-5">
		<c:choose>
			<c:when test="${not empty datecheck}">
				<button type="button" class="btn btn-success" onclick="opener.location.reload();self.close();">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-lg" viewBox="0 0 16 16">
  <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425z"></path>
</svg>확인</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-tertiary" disabled>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16  fill="currentColor" class="bi bi-check-lg" viewBox="0 0 16 16">
  <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425z"></path>
</svg>확인</button>
			</c:otherwise>
		</c:choose>
		</div>
</body>
</html>

