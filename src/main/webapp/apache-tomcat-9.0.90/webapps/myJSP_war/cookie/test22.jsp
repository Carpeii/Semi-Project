<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<s:setDataSource var="ds"
                 url="jdbc:mariadb://localhost/sample"
                 driver="org.mariadb.jdbc.Driver"
                 user="root"
                 password="1234"/>
출력 : ${ds}
<s:query
        var="result"
        dataSource="${ds}"
        sql = "SELECT * FROM dept WHERE deptno = ?;">
    <s:param value="${param.deptno}" />
</s:query>
<c:forEach var="row" items="${result.rows}">
    번호: ${row.deptno}, 부서명: ${row.dname}, 위치: ${row.loc} <br/>
</c:forEach>
</body>
</html>
