<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>객실 옵션 추가</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h1>객실 옵션 추가</h1>
<form id="optionForm" method="post" action="<%=request.getContextPath() %>/service/roomOptionAdd">
    <input type="hidden" name="optionsJson" id="optionsJson">

    <h2>기본 옵션</h2>
    <label><input type="checkbox" name="options" value="refrigerator"> 냉장고</label><br>
    <label><input type="checkbox" name="options" value="washingMachine"> 세탁기</label><br>
    <label><input type="checkbox" name="options" value="airConditioner"> 에어컨</label><br>
    <label><input type="checkbox" name="options" value="sink"> 싱크대</label><br>
    <label><input type="checkbox" name="options" value="bed"> 침대</label><br>
    <label><input type="checkbox" name="options" value="tv"> TV</label><br>
    <label><input type="checkbox" name="options" value="internet"> 인터넷</label><br>

    <h2>추가 옵션</h2>
    <label><input type="checkbox" name="options" value="doorLock"> 문 잠금 장치</label><br>
    <label><input type="checkbox" name="options" value="cctv"> CCTV</label><br>
    <label><input type="checkbox" name="options" value="managementOffice"> 관리 사무소</label><br>
    <label><input type="checkbox" name="options" value="gasRange"> 가스 레인지</label><br>
    <label><input type="checkbox" name="options" value="induction"> 인덕션</label><br>
    <label><input type="checkbox" name="options" value="microwave"> 전자레인지</label><br>
    <label><input type="checkbox" name="options" value="diningTable"> 식탁</label><br>
    <label><input type="checkbox" name="options" value="shoeRack"> 신발장</label><br>
    <label><input type="checkbox" name="options" value="wardrobe"> 옷장</label><br>
    <label><input type="checkbox" name="options" value="dressingRoom"> 드레스룸</label><br>
    <label><input type="checkbox" name="options" value="vanity"> 화장대</label><br>
    <label><input type="checkbox" name="options" value="cableTv"> 케이블 TV</label><br>
    <label><input type="checkbox" name="options" value="sofa"> 소파</label><br>
    <label><input type="checkbox" name="options" value="desk"> 책상</label><br>
    <label><input type="checkbox" name="options" value="curtains"> 커튼</label><br>
    <label><input type="checkbox" name="options" value="balconyVeranda"> 발코니/베란다</label><br>

    <h2>편의 옵션</h2>
    <label><input type="checkbox" name="options" value="heater"> 난방기</label><br>
    <label><input type="checkbox" name="options" value="airPurifier"> 공기청정기</label><br>
    <label><input type="checkbox" name="options" value="dryer"> 건조기</label><br>
    <label><input type="checkbox" name="options" value="iron"> 다리미</label><br>
    <label><input type="checkbox" name="options" value="waterDispenser"> 정수기</label><br>
    <label><input type="checkbox" name="options" value="riceCooker"> 밥솥</label><br>
    <label><input type="checkbox" name="options" value="electricKettle"> 전기주전자</label><br>
    <label><input type="checkbox" name="options" value="tableware"> 식기</label><br>
    <label><input type="checkbox" name="options" value="cookingUtensils"> 조리기구</label><br>
    <label><input type="checkbox" name="options" value="bathtub"> 욕조</label><br>
    <label><input type="checkbox" name="options" value="hairDryer"> 헤어 드라이어</label><br>
    <label><input type="checkbox" name="options" value="bidet"> 비데</label><br>

    <h2>반려동물 허용</h2>
    <label><input type="checkbox" name="options" value="petFriendly"> 반려동물 허용</label><br>

    <input type="submit" value="옵션 제출" onclick="submitForm(event)">
</form>

<script>
    function submitForm(event) {
        event.preventDefault(); // 기본 폼 제출 방지
        var checkboxes = document.querySelectorAll('input[name="options"]:checked');
        var selectedValues = [];
        checkboxes.forEach((checkbox) => {
            selectedValues.push(checkbox.value);
        });
        var optionsJson = JSON.stringify({ options: selectedValues });
        document.getElementById('optionsJson').value = optionsJson; // JSON 문자열을 숨겨진 필드에 설정
        document.getElementById('optionForm').submit(); // 폼 제출
    }
</script>

</body>
</html>
