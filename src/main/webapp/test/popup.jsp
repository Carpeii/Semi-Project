<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
  <style>
        /* 커스텀 컨테이너 크기 */
        .custom-container {
            width: 500px; /* 컨테이너 너비 */
            height: 400px; /* 컨테이너 높이 */
            border: 1px solid #ddd; /* 테두리 추가 (옵션) */
            padding: 20px; /* 패딩 추가 (옵션) */
        }

        /* 달력 테이블의 사이즈 조정 */
        .calendar-table {
            width: 100%; /* 테이블 너비 */
            height: 100%; /* 테이블 높이 */
        }
        
        .calendar-table td, .calendar-table th {
            width: 14.28%; /* 셀 너비 (7개의 열) */
            height: calc(100% / 6); /* 셀 높이 (6개의 행) */
            vertical-align: middle;
            text-align: center;
            border: 1px solid #ddd; /* 테두리 추가 */
            cursor: pointer;
        }

        .calendar-table .today {
            background-color: #dff0d8;
        }

        .calendar-table .disabled {
        color: red;
            cursor: default;
        text-decoration: line-through;
        }
    </style>
<meta charset="UTF-8">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
  <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>

<title>Insert title here</title>
</head>
<body>
<div class="container mt-4 custom-container">
	<c:out value="${sb}" escapeXml="false"/>
<form action="${pageContext.request.contextPath }/calendar/move" method="post">
<button value="before" name="moveMonth">이전달</button>
<button value="next" name="moveMonth">다음달</button>
</form>
</div>

</body>
</html>	