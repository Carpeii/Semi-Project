<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- calendar.jsp -->

<form action="calendar_ok.jsp" method="post">
<fieldset>
	<legend>달력 출력</legend>
	<label for="year">년도</label>
	<select id="year" name="year">
		<option value='2023'>2023 년</option>
		<option value='2024' selected>2024 년</option>
		<option value='2025'>2025 년</option>
	</select>
	
	<label for="month">월</label>
	<select id="month" name="month">
		<option value='1'>1 월</option>
		<option value='2'>2 월</option>
		<option value='3'>3 월</option>
		<option value='4'>4 월</option>
		<option value='5'>5 월</option>
		<option value='6' selected>6 월</option>
		<option value='7'>7 월</option>
		<option value='8'>8 월</option>
		<option value='9'>9 월</option>
		<option value='10'>10 월</option>
		<option value='11'>11 월</option>
		<option value='12'>12 월</option>
	</select>
	
	<input type='submit' id="btn" value='달력보기'>
</fieldset>
</form>

</body>
</html>