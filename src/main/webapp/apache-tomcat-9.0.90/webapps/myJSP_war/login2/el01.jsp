<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="EUC-KR">
  <title>Insert title here</title>
</head>
<body>
<!--JavaServer Pages Standard Tag Library-->
<!--jstl 문법: 태그 형식으로 코딩하는 방법(디자이너에게 직관적임)-->
<c:forEach var ="i" begin = "1" end = "10">
  ${i}
</c:forEach>

</body>
</html>