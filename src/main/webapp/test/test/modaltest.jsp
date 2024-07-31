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
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
        Open Modal
    </button>
</div>

<!-- 모달 구조 -->
<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="myModalLabel">Modal Title</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- 모달 안에 표시할 내용 -->
                <div class="col-md">
          <div class="card shadow-sm">
          <a href="해당 페이지">
          <img class="bd-placeholder-img card-img-top" width="100%" height="225" src="/image/img2.jpg" alt="이미지없음">
          </a>
            <div class="card-body">
            <p class="card-title">롯데 시그니엘</p>
              <p class="overflow-y-hidden">뷰 보면서 캔맥마렵네</p>
              <div class="d-flex justify-content-between align-items-center">
             </div>
                <small class="text-body-secondary">방 1</small>
                <small class="text-body-secondary">화장실 1</small>
                <small class="text-body-secondary">거실 0</small>
                <small class="text-body-secondary">주방 0</small>
              </div>
              <div class="card-footer">
       			 <small class="text-primary">에누리 ㄴ , 바퀴벌레나옴ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ</small>
      		  </div>
              
          </div>
        </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>