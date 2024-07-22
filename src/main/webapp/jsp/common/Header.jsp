<%--
  Created by IntelliJ IDEA.
  User: kimjiwoong
  Date: 2024. 7. 16.
  Time: 오후 2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%!boolean mode = true; %>

<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom" >
      <div class="col-md-3 mb-2 mb-md-0">
        <a href="./image/img.jpg" class="d-inline-flex link-body-emphasis text-decoration-none">
         <!-- img -->
        </a>
      </div>
      <%if(mode =true) {%>
      
      <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
      <!-- 현재 선택된 메뉴는 컬러가 검은색으로 나오게 처리 없애도될듯 -->
        <li><a href="#" class="nav-link px-2 link-secondary">메뉴1</a></li>
        <li><a href="#" class="nav-link px-2">메뉴2</a></li>
        <li><a href="#" class="nav-link px-2">메뉴3</a></li>
        <li><a href="#" class="nav-link px-2">메뉴4</a></li>
        <li><a href="#" class="nav-link px-2">메뉴5</a></li>
      </ul>
    
      <div class="col-md-3 text-end">
		<!-- 호스트페이지 -->
		 <form action="/index3.jsp" method="post" name="setmode">
		       	<input type="hidden" id="mode" value="guest"/>
        <button type="button" name="mode" id="gbtn" class="btn btn-outline-primary me-2">게스트 모드</button>
        <button type="button" class="btn btn-outline-primary me-2">로그인/회원가입</button>
        </form>
      </div>
      <%}else{ %>
      <!--게스트페이지 -->
      <div class="col-md-3 text-end">
      <form action="/index3.jsp" method="post" name="setmode">
      	<input type="text" id="mode" value="host"/>
        <button type="button" name="hmode"  id="hbtn"class="btn btn-outline-primary me-2">호스트 모드</button>
        <button type="button" class="btn btn-outline-primary me-2">로그인/회원가입</button>
      </form>
      </div>
<%} %>      
    </header>