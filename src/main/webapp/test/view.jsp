<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css">
<meta charset="UTF-8">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
  <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
<title>Insert title here</title>
</head>
<body>
<body>
<div class="container mt-2 sm custom-container">
<form>
	<table class="table calendar-table">
	<th colspan="7">2024-06</th>
	<tr>
<th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th><th>일</th>
</tr>
<tr>
<td>
<button type="button" class="w-100 h-100 btn btn-outline-primary" style"border: none;" value="notselectedDay">
1
</button>
</td>
<td class="disabled"> </td>
<td class="disabled"> </td>
<td class="disabled">1</td>
<td class="disabled">[2]</td>
<td class="disabled">3</td>
<td class="disabled">4</td>
</tr>
<tr>
<td class="disabled">5</td>
<td class="disabled">6</td>
<td class="disabled">7</td>
<td class="disabled">8</td>
<td class="disabled">9</td>
<td class="disabled">10</td>
<td class="disabled">11</td>
</tr>
<tr>
<td class="disabled">12</td>
<td class="disabled">13</td>
<td class="disabled">14</td>
<td class="disabled">15</td>
<td class="disabled">16</td>
<td class="disabled">17</td>
<td class="disabled">18</td>
</tr>
<tr>
<td class="disabled">19</td>
<td class="disabled">20</td>
<td>21</td>
<td>22</td>
<td>23</td>
<td>24</td>
<td>25</td>
</tr>
<tr>
<td>26</td>
<td>27</td>
<td>28</td>
<td class="disabled">29</td>
<td class="disabled">30</td>
<td class="disabled">31</td>
<td class="disabled"> </td>
</tr>
</tbody></table>
</form>

<form action="/Semi-Project/calendar/move" method="post">
<button value="before" name="moveMonth">이전달</button>
<button value="next" name="moveMonth">다음달</button>
</form>
<table>
<tbody><tr>
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
</tbody></table>
</div>


	</body>

</body>
</html>