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
            "게스트로 회원가입을 원하시는 경우 아래의 기본정보를 기입하시고 게스트 회원가입을 눌러주세요. 호스트로 회원가입을 원하시는 경우 아래의 기본정보와 추가 입력 폼을 작성하셔야합니다."
            </p>
        </div>
<%--        <div class="row g-5">--%>
<%--            <div class="col-md-5 col-lg-4 order-md-last">--%>
<%--                <h4 class="d-flex justify-content-between align-items-center mb-3">--%>
<%--                    <span class="text-primary">기본 개인정보 입력 폼</span>--%>
<%--                    <span class="badge bg-primary rounded-pill">5</span>--%>
<%--                </h4>--%>
<%--                <ul class="list-group mb-3">--%>
<%--                    <li class="list-group-item d-flex justify-content-between lh-sm">--%>
<%--                        <div>--%>
<%--                            <h6 class="my-0">아이디</h6>--%>
<%--                        </div>--%>
<%--                    </li>flex--%>
<%--                    <li class="list-group-item d-flex justify-content-between lh-sm">…</li>flex--%>
<%--                    <li class="list-group-item d-flex justify-content-between lh-sm">…</li>flex--%>
<%--                    <li class="list-group-item d-flex justify-content-between lh-sm">…</li>flex--%>
<%--                    <li class="list-group-item d-flex justify-content-between lh-sm">…</li>flex--%>
        <div class="col-md-7 col-lg-8">
            <h4 class="mb-3">개인정보 입력</h4>
            <form action="${pageContext.request.contextPath}/auth/join" method="post">
                <input type="hidden" name="isDuplicate" value="${isDuplicate}">
                <div class="row g-3">
                    <div class="col-sm-6">
                        <label for="userId" class="form-label">아이디</label>
                        <input type="text" class="form-control" name="userId" placeholder value required>
                        <button type="submit" name="action" value="checkId">아이디 중복 검사</button>
                    </div>
                    <div class="col-sm-6">
                        <label for="password" class="form-label">비밀번호</label>
                        <input type="password" class="form-control" name="password" placeholder value required>
                    </div>
                    <div class="col-sm-6">
                        <label for="pwConfirm" class="form-label">비밀번호 확인</label>
                        <input type="text" class="form-control" name="pwConfirm" placeholder value required>
                    </div>
                    <div class="col-sm-6">
                        <label for="name" class="form-label">이름</label>
                        <input type="text" class="form-control" name="name" placeholder value required>
                    </div>
                    <div class="col-sm-6">
                        <label for="phone" class="form-label">휴대폰 번호</label>
                        <input type="text" class="form-control" name="phone" placeholder value required>
                    </div>
                    <hr class="my-4">
                    <button class="w-100 btn btn-primary btn-lg" type="submit">Continue to checkout</button>
                </div>
            </form>
        </div>
    </main>
<h1>1평 회원가입</h1>
<form action="${pageContext.request.contextPath}/auth/join" method="post">
    <input type="hidden" name="isDuplicate" value="${isDuplicate}">
    <fieldset>
        <legend>회원가입 폼을 작성해주세요.</legend>
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
                <td colspan="3"> <div id="errMsg">${errMsg}</div><br></td>
            </tr>
        </table>
        <table>
            회원 타입을 선택해주세요<br>
            <button type="submit" name="action" value="guest">게스트로 회원가입하기</button><br>
            <button type="submit" name="action" value="host">호스트로 추가 정보 작성하기</button>
        </table>
    </fieldset>
</form>
</div>
</body>
</html>