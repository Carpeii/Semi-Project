<%@ page import="com.mywebapp.dto.MemberDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>hostMain.jsp</title>
<link href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<style>
        .custom-button {
            background: linear-gradient(45deg, #007bff, #00d2ff);
            border: none;
            color: white;
            border-radius: 50px;
            padding: 15px 30px;
            font-size: 1.25rem;
            font-weight: bold;
            transition: all 0.3s ease;
        }
        .custom-button:hover {
            background: linear-gradient(45deg, #0056b3, #00aaff);
            transform: scale(1.05);
        }
        .custom-button:focus {
            outline: none;
            box-shadow: 0 0 0 0.2rem rgba(38, 143, 255, 0.5);
        }
</style>
</head>
<body>

<jsp:include page="/jsp/common/header.jsp"></jsp:include>
<div class="p-5 mb-4 bg-body-tertiary rounded-3">
      <div class="container-fluid py-5">
        <h1 class="display-5 fw-bold">1평에 방을 등록하고
        <br>
        쉽고, 안전하게 임대 수익을 올려 보세요</h1>
        <br>
    <button class="custom-button" type="button" onclick="location.href='${pageContext.request.contextPath}/service/roomAdd'">방 등록하기</button>
      </div>
    </div>

    <div class="row align-items-md-stretch">
      <div class="col-md-6">
        <div class="h-100 p-5 text-bg-dark rounded-3">
          <h2><i class="bi bi-wallet2"></i> 높은 임대 수익</h2>
          <p>월세 대비 높은 임대료를 받을 수 있고, 낮은 수수료로 이용이 가능합니다.</p>
        </div>
      </div>
      <div class="col-md-6">
        <div class="h-100 p-5 bg-body-tertiary border rounded-3">
          <h2><i class="bi bi-clock"></i> 빠른 공실 해결</h2>
          <p>계약 체결까지 평균 3일 소요되어 공실을 빠르게 해결할 수 있습니다.</p>
        </div>
      </div>
      <div class="col-md-6">
        <div class="h-100 p-5 bg-body-tertiary border rounded-3">
          <h2><i class="bi bi-clipboard"></i> 편리한 관리</h2>
          <p>방 등록부터 계약까지 온라인으로 편리하게 관리하세요.</p>
        </div>
      </div>
      <div class="col-md-6">
        <div class="h-100 p-5 text-bg-dark rounded-3">
          <h2><i class="bi bi-shield-check"></i> 안전한 임대</h2>
          <p>호스트를 위한 보험 가입을 통해 다양한 손해를 보상해 드려요.</p>
        </div>
      </div>
    </div>



<jsp:include page="/jsp/common/footer.jsp"></jsp:include>

</body>
</html>