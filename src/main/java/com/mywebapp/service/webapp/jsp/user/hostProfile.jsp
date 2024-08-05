<%--
  Created by IntelliJ IDEA.
  User: kimjiwoong
  Date: 2024. 7. 16.
  Time: 오후 2:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>hostProfile.jsp</title>
</head>
<body>
<h1>추가 정보 수정</h1>
<form action="${pageContext.request.contextPath}/editHost" method="post">
  <table>
    <td><h3>계좌정보 변경</h3></td>
    <tr>
      <td>은행이름:</td>
      <td><input type="text" name="bankName" id="bankName" value="${sessionScope.host.bankName}"></td>
    </tr>
    <tr>
      <td>계좌번호:</td>
      <td><input type="text" name="account" id="account" value="${sessionScope.host.account}"></td>
    </tr>
    <tr>
      <td>예금주:</td>
      <td><input type="text" name="accountHolder" id="accountHolder" value="${sessionScope.host.accountHolder}"></td>
    </tr>
    <tr>
      <td>${errMsg}</td>
    </tr>
    <tr>
      <td><button type="submit" name="edit">계좌정보 수정하기</button></td>
    </tr>
  </table>
</form>
</body>
</html>

