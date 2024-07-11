<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사원 조회</title>
</head>
<body>
<h2>부서별 사원 조회</h2>
<form action="./EmpController" method="get">
    <input type="hidden" name="path" value="emp_ok">
    <label for="deptno">부서 번호:</label>
    <input type="text" id="deptno" name="deptno">
    <input type="submit" value="조회">
</form>
</body>
</html>
