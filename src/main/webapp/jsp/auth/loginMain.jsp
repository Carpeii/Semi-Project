<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("utf-8"); %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>1평 - 단기임대, 1평에서 찾아보세요</title>
    <link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginMain.css"/>
</head>
<body>
 <jsp:include page="/jsp/common/header.jsp"></jsp:include>
 
<main class="form-signin d-flex justify-content-center align-items-center"  style="height:100%;">
    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
	            <div class="border rounded p-4 shadow">
	                <form action="${pageContext.request.contextPath}/auth/login" method="post">
	                    <h1 class="h3 mb-3 fw-normal text-center">Login</h1>
	
	
	                    <div class="form-floating position-relative mb-5">
	                        <input type="text" class="form-control" name="userId" id="userId" placeholder="userId" required>
	                        <label for="userId">User ID</label>
	                        
	                        <span class="input-icon">
<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
<path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6"></path>
</svg></span>
	                    </div>
	                    
	                    
	                    <div class="form-floating position-relative">
	                        <input type="password" class="form-control" name="password" id="password" placeholder="password " required>
	                        <label for="password">Password</label>
	                        
	                        <span class="input-icon"><svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-eye-slash" viewBox="0 0 16 16">
  <path d="M13.359 11.238C15.06 9.72 16 8 16 8s-3-5.5-8-5.5a7 7 0 0 0-2.79.588l.77.771A6 6 0 0 1 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13 13 0 0 1 14.828 8q-.086.13-.195.288c-.335.48-.83 1.12-1.465 1.755q-.247.248-.517.486z"></path>
  <path d="M11.297 9.176a3.5 3.5 0 0 0-4.474-4.474l.823.823a2.5 2.5 0 0 1 2.829 2.829zm-2.943 1.299.822.822a3.5 3.5 0 0 1-4.474-4.474l.823.823a2.5 2.5 0 0 0 2.829 2.829"></path>
  <path d="M3.35 5.47q-.27.24-.518.487A13 13 0 0 0 1.172 8l.195.288c.335.48.83 1.12 1.465 1.755C4.121 11.332 5.881 12.5 8 12.5c.716 0 1.39-.133 2.02-.36l.77.772A7 7 0 0 1 8 13.5C3 13.5 0 8 0 8s.939-1.721 2.641-3.238l.708.709zm10.296 8.884-12-12 .708-.708 12 12z"></path>
</svg></span>
	                    </div>
	                    
	                    
	                    <div class="form-check text-start my-3">${errMsg}</div>
	                    <button class="btn btn-primary w-100 py-2" type="submit" name="login">Sign in</button>
	                    <p class="mt-5 mb-3 text-body-secondary text-center">© 1pyeong</p>
	                </form>
	            </div>
            </div>
        </div>
    </div>
</main>

 <jsp:include page="/jsp/common/footer.jsp"></jsp:include>
</body> 
</html>