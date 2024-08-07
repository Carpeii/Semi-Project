<%@ page import="com.mywebapp.dto.MemberDto" %>
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
            <h2>기본 회원정보 수정</h2>
            <p class="lead">
                기본적인 회원정보를 수정하실 수 있습니다.
            </p>
        </div>
        <div class="col-md-7 col-lg-8">
            <h4 class="mb-3">개인정보 입력</h4>
            <form action="${pageContext.request.contextPath}/user/edit" method="post">
                <div class="row g-3">
                    <div class="col-sm-6">
                        <p>이름</p>
                        <input type="text" class="form-control" name="name" value="${sessionScope.user.name}"/>
                    </div>
                    <div class="col-sm-6">
                        <p>휴대폰 번호</p>
                        <input type="text" class="form-control" name="phone" value="${sessionScope.user.phone}"/>
                    </div>
                    <div class="col-sm-6">
                        <p>비밀번호</p>
                        <input type="password" class="form-control" name="password">
                    </div>
                    <div class="col-sm-6">
                        <p>새로운 비밀번호</p>
                        <input type="password" class="form-control" name="newPassword">
                    </div>
                    <div class="col-sm-6">
                        <p>비밀번호 확인</p>
                        <input type="password" class="form-control" name="pwConfirm">
                    </div>
                    <div id="pwErrMsg">${pwErrMsg}</div>
                    <div id="newPwErrMsg">${newPwErrMsg}</div><br>
                    <div id="errMsg">${errMsg}</div>
                    <hr class="my-4">
                    <button class="w-100 btn btn-primary btn-lg" type="submit" name="edit">회원정보 수정하기</button>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>

