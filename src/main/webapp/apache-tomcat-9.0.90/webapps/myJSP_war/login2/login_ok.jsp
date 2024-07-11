<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("utf-8");
    
    String id = request.getParameter("id");
    String password = request.getParameter("password");
    
    //가상 아이디 , 비밀번호
    String saveId = "tester";
    String savePassword = "1234";
    
    //ㅣ게시판 - insert
    //0 -> 정상 / 1 -> 비밀번호 오류 / 2 -> 기타에러
    int flag = 2;
    if(saveId.equals(id) && savePassword.equals(password)) {
    	flag = 0;
    	//session 부여 => 민감한 정보 안됨(비밀번호X) / 프로그램에 공통적으로 필요한 정보

        Cookie cookie1 = new Cookie("cid", id);
        Cookie cookie2 = new Cookie("cgrade", "B");

        response.addCookie(cookie1);
        response.addCookie(cookie2);

//    	session.setAttribute("sid",id);
//    	session.setAttribute("sgrade","A");
    } else {
    	flag = 1;
    }
    
    out.println("<script type='text/javascript'>");
    if(flag == 0) {
    	out.println("alert('로그인 되었습니다.');");
    	out.println("location.href='login_complete.jsp';");
    } else if(flag == 1) {
    	out.println("alert('아이디, 비밀번호를 확인해주세요.');");
    	out.println("history.back();");
    } else {
    	out.println("alert('기타 에러');");
    	out.println("history.back();");
    }
    out.println("</script>");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>