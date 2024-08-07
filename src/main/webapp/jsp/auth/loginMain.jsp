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
</head>
<body>
 <jsp:include page="/jsp/common/header.jsp"></jsp:include>
 
<main class="form-signin d-flex justify-content-center align-items-center">
    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
	            <div class="border rounded p-4 shadow">
	                <form action="${pageContext.request.contextPath}/auth/login" method="post">
	                    <h1 class="h3 mb-3 fw-normal text-center">Login</h1>
	
	                    <div class="form-floating">
	                        <input type="text" class="form-control" name="userId" id="userId" placeholder="userId" required>
	                        <label for="userId">User ID</label>
	                    </div>
	                    <div class="form-floating">
	                        <input type="password" class="form-control" name="password" id="password" placeholder="password" required>
	                        <label for="password">Password</label>
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