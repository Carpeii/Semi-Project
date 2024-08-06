<%--
  Created by IntelliJ IDEA.
  User: now
  Date: 2024. 8. 5.
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>adminJoin</title>
</head>
<body>
<h1>1평 관리자 회원가입</h1>
<form action="${pageContext.request.contextPath}/adminJoin" method="post">
    <input type="hidden" name="isDuplicate" value="${isDuplicate}">
    <fieldset>
        <legend>관리자 계정 추가 폼을 작성해주세요</legend>
        <table>
            <tr>
                <td><label for="userId">아이디</label></td>
                <td><input type="text" name="userId" id="userId" value="${userId != null ? userId : ''}"/></td>
                <td><button type="submit" name="action" value="checkId">아이디 중복 검사</button></td>
            </tr>
            <tr>
                <br>
                <div id="idErrMsg">${idErrMsg}</div><br>
            </tr>
            <tr>
                <td><label for="password">비밀번호</label></td>
                <td><input type="password" name="password" id="password" /></td>
            </tr>
            <tr>
                <td><label for="pwConfirm">비밀번호 확인</label></td>
                <td><input type="password" name="pwConfirm" id="pwConfirm" /></td>
            </tr>
            <tr>
                <div id="pwErrMsg">${pwErrMsg}</div><br>
            </tr>
            <tr>
                <td><label for="name">이름</label></td>
                <td><input type="text" name="name" id="name" value="${param.name != null ? param.name : ''}"/></td>
            </tr>
            <tr>
                <td><label for="phone">휴대폰 번호</label></td>
                <td><input type="text" name="phone" id="phone" value="${param.phone != null ? param.phone : ''}"/></td>
            </tr>
        </table>
        <br>
        <table>
            <tr>
                <td colspan="3"> <div id="errMsg">${errMsg}</div></td>
            </tr>
        </table>
        <table>
<%--            <button type="submit" name="action" value="host">호스트로 추가 정보 작성하기</button>--%>
                <input type="submit" name="login" value="로그인">
        </table>
    </fieldset>
</form>
</body>
</html>
