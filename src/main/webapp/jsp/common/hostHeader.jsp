<%@ page import="com.mywebapp.dto.UserDto" %>
<%@ page import="com.mywebapp.dto.MemberDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 border-bottom" >
      <div class="col-md-3 mb-2 mb-md-0">
        <a href="${pageContext.request.contextPath}/guestMain.jsp">
         <!-- img -->
         <img class="ps-3" alt="logo" src="${pageContext.request.contextPath}/image/logo.jpg">
        </a>                   
      </div>
      <div class="col-md-3 text-end">
          <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
              <!-- 현재 선택된 메뉴는 컬러가 검은색으로 나오게 처리 없애도될듯 -->
              <!-- guestHeader -->
              <%
                  MemberDto user = (session != null) ? (MemberDto) session.getAttribute("user") : null;
              %>
              <%
                  if (user != null) {
                      System.out.println(user);
              %>
                      <p>호스트 ${sessionScope.user.userId}님 환영합니다</p>
                      <li><button type="button" onclick="location.href='${pageContext.request.contextPath}/auth/logout'" class="btn btn-outline-primary me-2">로그아웃</button></li>
                      <li><button type="button" onclick="location.href='${pageContext.request.contextPath}/auth/myPage'" class="btn btn-outline-primary me-2">마이 페이지</button></li>
                      <li><button type="button" onclick="location.href='${pageContext.request.contextPath}/guestMain.jsp'" class="btn btn-outline-primary me-2">게스트 페이지</button></li>
                      <li><button type="button" onclick="location.href='${pageContext.request.contextPath}/service/hostRoomList'" class="btn btn-outline-primary me-2">방 관리</button></li>
                      <li><button type="button" onclick="location.href='${pageContext.request.contextPath}/service/roomAdd'" class="btn btn-outline-primary me-2">방 등록하기</button></li>
              <%
                  } else if (user == null) {
                      System.out.println(user);
              %>
              <li><button type="button" onclick="location.href='${pageContext.request.contextPath}/auth/login'" class="btn btn-outline-primary me-2">로그인</button></li>
              <li><button type="button" onclick="location.href='${pageContext.request.contextPath}/auth/join'" class="btn btn-outline-primary me-2">회원가입</button></li>
              <%
                  }
              %>
          </ul>
      </div>
    </header>