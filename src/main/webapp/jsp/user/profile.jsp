<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>profile.jsp</title>
</head>
<body>
<h1>회원정보 수정</h1>
<form action="${pageContext.request.contextPath}/edit" method="post">
    <table>
        <td><h3>개인정보 변경</h3></td>
        <tr>
            <td>이름:</td>
            <td><input type="text" name="name" id="name" value="${sessionScope.user.name}"></td>
        </tr>
        <tr>
            <td>휴대폰 번호:</td>
            <td><input type="text" name="phone" id="phone" value="${sessionScope.user.phone}"></td>
        </tr>
        <br>
        <tr>
            <td>기존 비밀번호:</td>
            <td><input type="password" name="password" id="password"></td>
        </tr>
        <tr>
            <td>비밀번호:</td>
            <td><input type="password" name="newPassword" id="newPassword"></td>
        </tr>
        <tr>
            <td>비밀번호 확인:</td>
            <td><input type="password" name="pwConfirm" id="passwordConfirm"></td>
        </tr>
        <tr>
            <div id="errMsg">${errMsg}</div><br>
        </tr>
        <tr>
            <td><button type="submit" name="edit">회원정보 수정하기</button></td>
        </tr>
    </table>
</form>
</body>
</html>

