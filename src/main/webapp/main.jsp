<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("utf-8"); %>
    
<!doctype html>
<html>
  <head>
 	 <meta charset="utf-8">
 	 <meta name="viewport" content="width=device-width, initial-scale=1">
     <title>호스트 메인</title>
     <link rel="stylesheet" href="webjars/bootstrap/5.3.3/css/bootstrap.css">
  <script src="webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
  
  </head>
  <body>
  
<!-- 호스트 게스트 조건문으로 처리? -->
<%-- <%@include file="/jsp/common/hostHeader.jsp" %> --%>
<%@include file="/jsp/common/Header.jsp" %>
<!-- START ALBUM -->
  <div class="album py-5 bg-body-tertiary">
  
  <p class="h2 text-center">단기임대, 삼삼엠투에서 찾아보세요</p>
  
    <div class="container">
     
    <div class="container d-flex justify-content-center m-5">
        <form action="/" method="post" class="col-12 col-lg-auto mb-3 mb-lg-0 w-50 " role="search">
            <input type="search" id="search" class="form-control w-200" placeholder="지역 또는 건물명을 입력 하세요" aria-label="Search">
        </form>
        <button class="btn btn-outline-info btn-lg px-3 rounded-pill ms-2" type="button">
            검색
        </button>
</div>

      <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
      
      
      
      
        <div class="col-md">
        <div class="card shadow-sm">
          <!-- 이미지를 눌렀을 때 이동하는 페이지 -->
          <a href="해당 페이지">
          <img class="bd-placeholder-img card-img-top" width="100%" height="225" src="./image/img.jpg" alt="이미지없음">
          </a>
            <div class="card-body">
            <p class="card-title">op.gg</p>
              <p class="overflow-y-hidden">롤하고싶다</p>
              <div class="d-flex justify-content-between align-items-center">
             </div>
                <small class="text-body-secondary">방 1 </small>
                <small class="text-body-secondary">화장실 1</small>
                <small class="text-body-secondary">거실 0</small>
                <small class="text-body-secondary">주방 0</small>
              </div>
                <div class="card-footer">
        <small class="text-primary">장기계약 시 최대 60% 할인</small>
      </div>
          </div>
        </div>
        
      
        <div class="col-md">
          <div class="card shadow-sm">
          <a href="해당 페이지">
          <img class="bd-placeholder-img card-img-top" width="100%" height="225" src="./image/img2.jpg" alt="이미지없음">
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
        
      
        <div class="col-md">
         <div class="card shadow-sm">
          <a href="해당 페이지">
          <img class="bd-placeholder-img card-img-top" width="100%" height="225" src="./image/img3.jpg" alt="이미지없음">
          </a>
            <div class="card-body">
            <p class="card-title">타워 팰리스</p>
              <p style="overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">이게 오버플로우 설정하긴 했는데 몇 글자 부터 안보이는건지 진짜 모르겠네</p>
              <div class="d-flex justify-content-between align-items-center">
             </div>
                <small class="text-body-secondary">방 1 </small>
                <small class="text-body-secondary">화장실 1</small>
                <small class="text-body-secondary">거실 0</small>
                <small class="text-body-secondary">주방 0</small>
              </div>
                      <div class="card-footer">
        <small class="text-primary">바퀴벌레 나옴</small>
      </div>
          </div>
        </div>
        
        

      </div>
    </div>
  </div>
<!-- END ALBUM -->
<!--  <div class="album py-5 bg-body-tertiary"> -->
<!--     <div class="container"> -->
<!--       <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3"> -->
<!-- </main> -->
 <!-- footer -->
 <%@include file="/jsp/common/footer.jsp" %>
  </body>
</html>