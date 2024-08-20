<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("utf-8");
%>
<%
	LocalDate CheckOut = null;
long roomId = Long.parseLong(request.getParameter("roomId")); 
	int rentPrice = Integer.parseInt(request.getParameter("rentPrice"));
	
	if(session.getAttribute("selectDate") != null) {
	String Date = (String)session.getAttribute("selectDate");
	CheckOut = LocalDate.parse(Date);
	}
	
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
	<div class="container mt-4 mb-3 sm custom-container">
		<div class="row">

			<div class="col">
				<form action="${pageContext.request.contextPath }/calendar/move?roomId=${roomId}"
					method="get">
					<input type="hidden" name="roomId" value="<%=roomId%>"/>
					<input type="hidden" name="rentPrice" value="<%=rentPrice%>"/>
					<c:out value="${sb}" escapeXml="false" />
				</form>
			</div>
				
			<div class="col">
				<form action="${pageContext.request.contextPath }/calendar/select" method="get">
				<input type="hidden" name="roomId" value="<%=roomId%>"/>
				<input type="hidden" name="rentPrice" value="<%=rentPrice%>"/>
				
					<table class="table custom-table" style="border:none;">
						<tr> 
							<c:if test="${dateDiff >= 7}">
						<td class="noborder">
						<div class="d-flex flex-column align-items-center">
					         	<button  class="w-100 h-100 btn btn-outline-primary" value="7" name="period">1주일</button>
					         	<span class="text-body-tertiary"><%=rentPrice%>원</span>
					         	<span class="text-body-tertiary">~<%=CheckOut.plusDays(6)%></span>
						</div>
            			</td>
							</c:if>
            				<c:if test="${dateDiff >= 14}">
            			<td class="noborder">
            			<div class="d-flex flex-column align-items-center">
								<button class="w-100 h-100 btn btn-outline-primary" value="14" name="period">2주일</button>
								<span class="text-body-tertiary"><%=rentPrice*2%>원</span>
								<span class="text-body-tertiary">~<%=CheckOut.plusDays(13)%></span>
            			</div>
						</td>
							</c:if>
							<c:if test="${dateDiff >= 21}">
						<td class="noborder">
						<div class="d-flex flex-column align-items-center">
								<button class="w-100 h-100 btn btn-outline-primary" value="21" name="period">3주일</button>
								<span class="text-body-tertiary"><%=rentPrice*3%>원</span>
								<span class="text-body-tertiary">~<%=CheckOut.plusDays(20)%></span>
						</div>
						</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${dateDiff >= 28}">
							<td class="noborder">
							<div class="d-flex flex-column align-items-center">
								<button class="w-100 h-100 btn btn-outline-primary" value="28" name="period">4주일</button>
								<span class="text-body-tertiary"><%=rentPrice*4%>원</span>
								<span class="text-body-tertiary">~<%=CheckOut.plusDays(27)%></span>
							</div>
							</td>
							</c:if>
							<c:if test="${dateDiff >= 35}">
							<td class="noborder">
							<div class="d-flex flex-column align-items-center">
								<button class="w-100 h-100 btn btn-outline-primary" value="35" name="period">5주일</button>
								<span class="text-body-tertiary"><%=rentPrice*5%>원</span>
								<span class="text-body-tertiary">~<%=CheckOut.plusDays(34)%></span>
							</div>
							</td>
							</c:if>
							<c:if test="${dateDiff >= 42}">
							<td class="noborder">
							<div class="d-flex flex-column align-items-center">
								<button class="w-100 h-100 btn btn-outline-primary" value="42" name="period">6주일</button>
								<span class="text-body-tertiary"><%=rentPrice*6%>원</span>
								<span class="text-body-tertiary">~<%=CheckOut.plusDays(41)%></span>
							</div>
							</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${dateDiff >= 49}">
							<td class="noborder">
							<div class="d-flex flex-column align-items-center">
								<button class="w-100 h-100 btn btn-outline-primary" value="49" name="period">7주일</button>
								<span class="text-body-tertiary"><%=rentPrice*7%>원</span>
								<span class="text-body-tertiary">~<%=CheckOut.plusDays(48)%></span>
							</div>
							</td>
							</c:if>
							<c:if test="${dateDiff >= 63}">
							<td class="noborder">
							<div class="d-flex flex-column align-items-center">
								<button class="w-100 h-100 btn btn-outline-primary" value="63" name="period">8주일</button>
								<span class="text-body-tertiary"><%=rentPrice*8%>원</span>
								<span class="text-body-tertiary">~<%=CheckOut.plusDays(62)%></span>
							</div>
							</td>
							</c:if>
							<c:if test="${dateDiff >= 70}">
							<td class="noborder">
							<div class="d-flex flex-column align-items-center">
								<button class="w-100 h-100 btn btn-outline-primary" value="70" name="period">9주일</button>
								<span class="text-body-tertiary"><%=rentPrice*9%>원</span>
								<span class="text-body-tertiary">~<%=CheckOut.plusDays(69)%></span>
							</div>
							</td>
							</c:if>
						</tr>
						<tr>
							<c:if test="${dateDiff >= 77}">
							<td class="noborder">
							<div class="d-flex flex-column align-items-center">
								<button class="w-100 h-100 btn btn-outline-primary" value="77" name="period">10주일</button>
								<span class="text-body-tertiary"><%=rentPrice*10%>원</span>
								<span class="text-body-tertiary">~<%=CheckOut.plusDays(76)%></span>
							</div>
							</td>
							</c:if>
							<c:if test="${dateDiff >= 84}">
							<td class="noborder">
							<div class="d-flex flex-column align-items-center">
								<button class="w-100 h-100 btn btn-outline-primary" value="84" name="period">11주일</button>
								<span class="text-body-tertiary"><%=rentPrice*11%>원</span>
								<span class="text-body-tertiary">~<%=CheckOut.plusDays(83)%></span>
							</div>
							</td>
							</c:if>
							<c:if test="${dateDiff >= 91}">
							<td class="noborder">
							<div class="d-flex flex-column align-items-center">
								<button class="w-100 h-100 btn btn-outline-primary" value="91" name="period">12주일</button>
								<span class="text-body-tertiary"><%=rentPrice*12%>원</span>
								<span class="text-body-tertiary">~<%=CheckOut.plusDays(90)%></span>
							</div>
							</td>
							</c:if>
						</tr>
					</table>
				</form>

			</div>
		</div>
		<div class="mt-1 ms-3 d-flex justify-content-between w-50">
		<c:choose>
			<c:when test="${not empty datecheck}">
				<button type="button" class="btn btn-success mt-3 me-5" onclick="opener.location.reload();self.close();">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-lg" viewBox="0 0 16 16">
  <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425z"></path>
</svg>확인</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-tertiary mt-3 me-5" disabled>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16  fill="currentColor" class="bi bi-check-lg" viewBox="0 0 16 16">
  <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425z"></path>
</svg>확인</button>
			</c:otherwise>
		</c:choose>
		
		
		<form action="${pageContext.request.contextPath }/calendar/cancel" method="get">
		<input type="hidden" name="roomId" value="<%=roomId%>"/>
					<input type="hidden" name="rentPrice" value="<%=rentPrice%>"/>
		<c:choose>
			<c:when test="${not empty selectEndDate}">
				<button class="btn btn-outline-danger mt-3 me-5">
<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"></path>
</svg>취소 </button>
			</c:when>
			<c:otherwise>
				<button class="btn btn btn-tertiary mt-3 me-5" disabled>
<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-circle-fill" viewBox="0 0 16 16">
  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293z"></path>
</svg>취소 </button>
			</c:otherwise>
		</c:choose>
		
		
		

		</form>
		</div>
	</div>
		
</body>
</html>

