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
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>1평 단기임대, 한달살기 부동산 단기계약 플랫폼</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
  <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<div class="container">
  <main>
    <div class="py-5 text-center">
      <h2>추가 호스트 정보 수정</h2>
      <p class="lead">
        추가적인 호스트 정보를 수정하실 수 있습니다.
      </p>
    </div>
    <div class="col-md-7 col-lg-8">
      <h4 class="mb-3">계좌정보 변경</h4>
      <form action="${pageContext.request.contextPath}/user/editHost" method="post">
        <div class="row g-3">
          <div class="col-sm-6">
            <p>은행이름</p>
            <input type="text" class="form-control" name="bankName"/>
          </div>
          <div class="col-sm-6">
            <p>계좌번호</p>
            <input type="number" class="form-control" name="account"/>
          </div>
          <div class="col-sm-6">
            <p>예금주</p>
            <input type="text" class="form-control" name="accountHolder"/>
          </div>
          <div>${errMsg}</div>
          <hr class="my-4">
          <button class="w-100 btn btn-primary btn-lg" type="submit" name="edit">추가정보 수정하기</button>
        </div>
      </form>
    </div>
  </main>
</div>
</body>
</html>
          <%--<h1>추가 정보 수정</h1>--%>
<%--<form action="${pageContext.request.contextPath}/editHost" method="post">--%>
<%--  <table>--%>
<%--    <td><h3>계좌정보 변경</h3></td>--%>
<%--    <tr>--%>
<%--      <td>은행이름:</td>--%>
<%--      <td><input type="text" name="bankName" id="bankName" value="${sessionScope.host.bankName}"></td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--      <td>계좌번호:</td>--%>
<%--      <td><input type="text" name="account" id="account" value="${sessionScope.host.account}"></td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--      <td>예금주:</td>--%>
<%--      <td><input type="text" name="accountHolder" id="accountHolder" value="${sessionScope.host.accountHolder}"></td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--      <td>${errMsg}</td>--%>
<%--    </tr>--%>
<%--    <tr>--%>
<%--      <td><button type="submit" name="edit">계좌정보 수정하기</button></td>--%>
<%--    </tr>--%>
<%--  </table>--%>


