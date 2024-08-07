<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%request.setCharacterEncoding("utf-8"); %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
<script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
    <title>팝업 JSP 예제</title>
    <script type="text/javascript">
        	let url = '${pageContext.request.contextPath}/calendar/call';
        function openPopup() {
            //매개변수 -> (Url, 창이름 , 옵션)
            window.open(url, 'popupWindow', 'width=1005,height=545,scrollbars=yes');
        }
    </script>
</head>
<body>

<div class="container mt-5">
    <h1>달력호출창</h1>
    <button type="button" class="btn btn-primary"onclick="openPopup()">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar3" viewBox="0 0 16 16">
  <path d="M14 0H2a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2M1 3.857C1 3.384 1.448 3 2 3h12c.552 0 1 .384 1 .857v10.286c0 .473-.448.857-1 .857H2c-.552 0-1-.384-1-.857z"></path>
  <path d="M6.5 7a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2"></path>
</svg>
                달력
              </button>
</div>
<p>넘어온값</p>
checkIn  : <input type="text"  id="checkInDate" name="checkInDate" value="${selectDate }" class="form-control" disabled required/> <br><br>
checkOut : <input type="text" id="checkOutDate" name="checkOutDate" value="${selectEndDate }" class="form-control" disabled required/>
</body>
				
			

</html>

