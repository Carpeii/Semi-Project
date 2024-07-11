<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>

<%
    //별도의 시간 설정이 없으면 브라우저 종료와 동시에 삭제
    //쿠키 생성
    Cookie cookie1 = new Cookie("data1","value1");
    Cookie cookie2 = new Cookie("data2","value2");

    //쿠키 전송
    response.addCookie(cookie1);
    response.addCookie(cookie2);

    out.println(cookie1.getName() + "<br/>");
    out.println(cookie1.getValue() + "<br/>");

%>

</body>
</html>