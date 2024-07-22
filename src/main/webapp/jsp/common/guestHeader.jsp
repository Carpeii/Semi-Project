<%--
  Created by IntelliJ IDEA.
  User: kimjiwoong
  Date: 2024. 7. 16.
  Time: 오후 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom" >
      <div class="col-md-3 mb-2 mb-md-0">
        <a href="/" class="d-inline-flex link-body-emphasis text-decoration-none">
         <!-- img -->
        </a>
      </div>


      <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><a href="#" class="nav-link px-2 link-secondary">Home</a></li>
        <li><a href="#" class="nav-link px-2">Features</a></li>
        <li><a href="#" class="nav-link px-2">Pricing</a></li>
        <li><a href="#" class="nav-link px-2">FAQs</a></li>
        <li><a href="#" class="nav-link px-2">About</a></li>
      </ul>
      
      <div class="col-md-3 text-end">
		<!-- 호스트페이지 -->
        <button type="button" class="btn btn-outline-primary me-2">게스트 전용 페이지</button>
        <button type="button" class="btn btn-outline-primary me-2">로그인/회원가입</button>
      </div>
      
      <!--게스트페이지 -->
      <div class="col-md-3 text-end">
        <button type="button" class="btn btn-outline-primary me-2">호스트 모드</button>
        <button type="button" class="btn btn-outline-primary me-2">로그인/회원가입</button>
      </div>
    </header>