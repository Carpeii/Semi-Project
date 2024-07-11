<%--
  Created by IntelliJ IDEA.
  User: kimjiwoong
  Date: 2024. 6. 27.
  Time: 오전 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--7d08f5690826b66704bc6bc3997247e2&targetDt=20240626--%>
<c:url var="url" value="http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml">
    <c:param name="key" value="7d08f5690826b66704bc6bc3997247e2"/>
    <c:param name="targetDt" value="20240626"/>
</c:url>
<c:import var="xmlData" url="${url}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
소스보기 : <br/>
<textarea rows="50" cols="800">
    ${xmlData}
</textarea>
</body>
</html>
