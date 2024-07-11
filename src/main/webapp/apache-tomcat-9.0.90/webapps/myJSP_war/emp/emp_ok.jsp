<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="emp.EmpDao, emp.EmpDto" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사원 조회 결과</title>
</head>
<body>
<h2>사원 조회 결과</h2>
<%
//    String deptnoStr = request.getParameter("deptno");
//    int deptno = 0;
//    if (deptnoStr != null && !deptnoStr.trim().isEmpty()) {
//        deptno = Integer.parseInt(deptnoStr);
//    }
//
//    EmpDao dao = new EmpDao();
//    ArrayList<EmpDto> empList = dao.getEmpByDept(deptno);
//
    ArrayList<EmpDto>empList = (ArrayList<EmpDto>)request.getAttribute("empList");
    if (empList.size() > 0) {
%>
<table border="1">
    <tr>
        <th>사원 번호</th>
        <th>사원 이름</th>
        <th>직업</th>
        <th>매니저</th>
        <th>입사일</th>
        <th>급여</th>
        <th>커미션</th>
        <th>부서 번호</th>
    </tr>
    <%
        for (EmpDto emp : empList) {
    %>
    <tr>
        <td><%= emp.getEmpno() %></td>
        <td><%= emp.getEname() %></td>
        <td><%= emp.getJob() %></td>
        <td><%= emp.getMgr() %></td>
        <td><%= emp.getHiredate() %></td>
        <td><%= emp.getSal() %></td>
        <td><%= emp.getComm() %></td>
        <td><%= emp.getDeptno() %></td>
    </tr>
    <%
        }
    %>
</table>
<%
} else {
%>
<p>해당 부서에 사원이 없습니다.</p>
<%
    }
%>
</body>
</html>
