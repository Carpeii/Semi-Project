<%--
  Created by IntelliJ IDEA.
  User: now
  Date: 2024. 7. 25.
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hostJoinForm.jsp</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/hostJoin" method="post">
    <input type="hidden" name="userId" value="${userId}">
  <fieldset>
    <legend>호스트 추가 정보를 입력해주세요.</legend>
    <table>
<%--      <tr>--%>
<%--        <td><label for="memberId">가입하신 아이디를 적어주세요</label></td><br>--%>
<%--        <td><input type="text" name="memberId" id="memberId" /></td>--%>
<%--        <div>${idErrMsg}</div><br>--%>
<%--      </tr>--%>
      <tr>
        <td><label for="bankName">은행 이름</label></td>
        <td><input type="text" name="bankName" id="bankName" /></td>
      </tr>
      <tr>
        <td><label for="account">계좌</label></td>
<%--        <td>숫자만 입력해주세요</td>--%>
        <td><input type="text" name="account" id="account" /></td>
      </tr>
      <tr>
        <td><label for="accountHolder">예금주</label></td>
        <td><input type="text" name="accountHolder" id="accountHolder" /></td>
      </tr>
    </table>
    <br>
    <table>
      <div>${errMsg}</div><br>
    </table>
    <table>
      가입하시겠습니까?<br>
      <input type="submit" name="hostBank" value="가입하기">
    </table>
  </fieldset>
</form>
</body>
</html>
