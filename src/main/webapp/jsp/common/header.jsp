<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 border-bottom" >
      <div class="col-md-3 mb-2 mb-md-0">
        <a href="${pageContext.request.contextPath}/main.jsp">
         <!-- img -->
         <img class="ps-3" alt="logo" src="${pageContext.request.contextPath}/image/logo.jpg">
        </a>                   
      </div>
      
      <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
      <!-- 현재 선택된 메뉴는 컬러가 검은색으로 나오게 처리 없애도될듯 -->
		<li><a href="#" class="nav-link px-2 link-secondary">메뉴1</a></li>
		<li><a href="../index.jsp" class="nav-link px-2">메인 페이지</a></li>
		<li><a href="../user/profile.jsp" class="nav-link px-2">마이페이지</a></li>
		<li><a href="${pageContext.request.contextPath}/login" class="nav-link px-2">로그인</a></li>
		<li><a href="../service/hostMain.jsp" class="nav-link px-2">호스트 모드</a></li>
      </ul>
    
		<!-- 호스트페이지 -->
      <div class="col-md-3 text-end">
        <button type="button" name="mode" id="gbtn" class="btn btn-outline-primary me-2">게스트 모드</button>
        <button type="button" class="btn btn-outline-primary me-2">로그인/회원가입</button>
      </div>
      <!--게스트페이지 -->
		<!--
      <div class="col-md-3 text-end">
        <button type="button" name="hmode"  id="hbtn"class="btn btn-outline-primary me-2">호스트 모드</button>
        <button type="button" class="btn btn-outline-primary me-2">로그인/회원가입</button>
      </div>
		  -->
    </header>