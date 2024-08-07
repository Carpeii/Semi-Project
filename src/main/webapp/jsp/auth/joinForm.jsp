<%@ page import="com.mywebapp.dao.MemberDao" %>
<%@ page import="com.mywebapp.util.JdbcUtil" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: now
  Date: 2024. 7. 23.
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>1평 단기임대, 한달살기 부동산 단기계약 플랫폼</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
        <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
    </head>
</head>
<body>
<div class="container">
    <main>
        <div class="py-5 text-center">
            <h2>1평 회원가입</h2>
            <p class="lead">
            게스트로 회원가입을 원하시는 경우 아래의 기본정보를 기입하시고 게스트 회원가입을 눌러주세요.
            </p>
            <p class="lead">
            호스트로 회원가입을 원하시는 경우 아래의 기본정보와 추가 입력 폼을 작성하셔야합니다.
            </p>
        </div>
        <div class="col-md-7 col-lg-8">
            <h4 class="mb-3">개인정보 입력</h4>
            <form action="${pageContext.request.contextPath}/auth/join" method="post">
                <input type="hidden" name="isDuplicate" value="${isDuplicate}">
                <div class="row g-3">
                    <div class="col-sm-6">
                        <p>아이디</p>
                        <input type="text" class="form-control" name="userId" id="userId" value="${userId != null ? userId : ''}"/>
                        <button class="w-100 btn btn-primary btn-lg" type="submit" name="action" value="checkId">아이디 중복 검사</button>
                    </div>
                    <div id="idErrMsg">${idErrMsg}</div><br>
                    <div class="col-sm-6">
                        <p>비밀번호</p>
                        <input type="password" class="form-control" name="password">
                    </div>
                    <div class="col-sm-6">
                        <p>비밀번호 확인</p>
                        <input type="password" class="form-control" name="pwConfirm">
                    </div>
                    <div id="pwErrMsg">${pwErrMsg}</div><br>
                    <div class="col-sm-6">
                        <p>이름</p>
                        <input type="text" class="form-control" name="name" id="name" value="${param.name != null ? param.name : ''}"/>
                    </div>
                    <div class="col-sm-6">
                        <p>휴대폰 번호</p>
                        <input type="text" class="form-control" name="phone" id="phone" value="${param.phone != null ? param.phone : ''}"/>
                    </div>
                    <div id="errMsg">${errMsg}</div>
                    <hr class="my-4">
                    <button class="w-100 btn btn-primary btn-lg" type="submit" name="action" value="guest">게스트로 회원가입하기</button><br>
                    <button class="w-100 btn btn-primary btn-lg" type="submit" name="action" value="host">호스트로 추가 정보 작성하기</button>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>