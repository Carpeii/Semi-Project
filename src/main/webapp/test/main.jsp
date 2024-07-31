<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>팝업 JSP 예제</title>
    <script type="text/javascript">
    
        function openPopup() {
            //매개변수 -> (Url, 창이름 , 옵션)
            window.open('popup.jsp', 'popupWindow', 'width=600,height=400,scrollbars=yes');
        }
    </script>
</head>
<body>

<div class="container mt-5">
    <h1>JSP 팝업창 예제</h1>
    <button type="button" onclick="openPopup()">팝업창 열기</button>
</div>
</body>
</html>