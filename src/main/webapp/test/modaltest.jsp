<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("utf-8"); %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bootstrap Modal Example</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
  <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
</head>
<body>

<div class="container mt-5">
    <h1>Main Page</h1>
    <!-- 버튼 클릭 시 모달을 여는 버튼 -->
    <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
        Open Modal
    </button>
</div>

<!-- 모달 구조 -->
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="myModalLabel">Modal Title</h5>
                
                <button type="submit" name="calendar" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- 모달 안에 표시할 내용 -->
                <div class="col-md">
                <p>tset : ${test}</p>
        </div>
            </div>
            <div class="modal-footer">
                
                <form action="${pageContext.request.contextPath }/calendar" method="post">
                <button type="submit" class="btn btn-primary">Save changes</button>
       </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>