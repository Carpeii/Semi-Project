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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>1평 단기임대, 한달살기 부동산 단기계약 플랫폼</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<main>
    <div class="py-5 text-center">
        <h2>1평 회원가입</h2>
        <p class="lead">
            호스트로 회원가입을 원하시는 경우 아래의 기본정보와 추가 입력 폼을 작성하셔야합니다.
        </p>
    </div>
    <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">호스트 추가 정보 입력</h4>
        <form action="${pageContext.request.contextPath}/auth/hostJoin" method="post">
            <div class="row g-3">
                <p>은행 이름</p>
                <input type="text" class="form-control" name="bankName" id="bankName" />
            </div>
            <div>
                <p>계좌번호</p>
                <p>숫자만 입력 가능합니다.</p>
                <input type="number" class="form-control" name="account" id="account" />
            </div>
            <div>
                <p>예금주</p>
                <input type="text" class="form-control" name="accountHolder" id="accountHolder" />
            </div>
            <div>${errMsg}</div><br>
            <p>가입하시겠습니까?</p>
            <button class="w-100 btn btn-primary btn-lg" type="submit" name="action" value="join">가입하기</button>
        </form>
    </div>
</main>
</body>
</html>
