<%--
  Created by IntelliJ IDEA.
  User: now
  Date: 2024. 7. 23.
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>joinForm.jsp</title>
</head>
<body>
<h1>1평 회원가입</h1>
<form action="${pageContext.request.contextPath}/joinOk" method="post">
    <fieldset>
        <legend>회원가입 폼을 작성해주세요.</legend>
        <table>
            <tr>
                <td><label for="id">아이디</label></td>
                <td><input type="text" name="id" id="id" /></td>
            </tr>
            <tr>
                <td><label for="pw">비밀번호</label></td>
                <td><input type="password" name="pw" id="pw" /></td>
            </tr>
            <tr>
                <td><label for="pwConfirm">비밀번호 확인</label></td>
                <td><input type="password" name="pwConfirm" id="pwConfirm" /></td>
            </tr>
            <tr>
                <td><label for="name">이름</label></td>
                <td><input type="text" name="name" id="name" /></td>
            </tr>
            <tr>
                <td><label for="phone">휴대폰 번호</label></td>
                <td><input type="text" name="phone" id="phone" /></td>
            </tr>
        </table>
        <br>
        <table>
            <div>${errMsg}</div><br>
        </table>
        <table>
            회원 타입을 선택해주세요<br>
            <input type="submit" name="guest" value="게스트로 가입하기"><br>
            <input type="submit" name="host" value="호스트로 가입하기">
        </table>
    </fieldset>
</form>
</body>
</html>
