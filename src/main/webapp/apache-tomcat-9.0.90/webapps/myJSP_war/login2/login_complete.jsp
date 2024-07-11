<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //로그인이 안된 경우 -> 세션이 없는 경우 => 접근 X
    //세션안의 값이 아니라  세션이 있는지를 검사
//    if(session.getAttribute("sid") == null) {
//    	//로그인 안됨
//    	out.println("<script type='text/javascript'>");
//    	out.println("alert('로그인 해야합니다.');");
//    	out.println("location.href='login_form.jsp';");
//    	out.println("</script>");
//    } else {
//    	//로그이 됨
//    }
        Cookie[] cookies = request.getCookies();

        int flag = 1;
        if(cookies!=null && cookies.length>0) {
            for (int i = 0; i < cookies.length; i++) {
                if(cookies[i].getName().equals("cid")){
                    flag = 0;
                }
                break;
            }
        }
        //1이면 비정상
        if(flag == 1) {
            out.println("<script type='text/javascript'>");
            out.println("alert('로그인 해야합니다.');");
            out.println("location.href='login_form.jsp';");
            out.println("</script>");
        }else{
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
로그인 되었습니다. <br/>
<a href="logout_ok.jsp">로그아웃</a>

</body>
</html>
<%
    }
%>