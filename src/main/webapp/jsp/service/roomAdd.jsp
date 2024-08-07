<%@ page import="com.mywebapp.model.Member" %>
<%@ page import="com.mywebapp.dto.MemberDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>방 추가하기</title>
</head>
<body>
<h1>새 방 추가하기</h1>
<form method="post" action="<%=request.getContextPath() %>/service/roomAdd">
    <!-- 기본적인 방 정보 입력란 -->
    <%
        if(session == null) {
            response.sendRedirect(request.getContextPath()+"/jsp/auth/loginMain");
            return;
        }
        MemberDto user = (MemberDto) session.getAttribute("user");
        if(user.getMemberType() == 0) {
            response.sendRedirect(request.getContextPath()+"/jsp/auth/loginMain");
            return;
        }
    %>
    <input type="hidden" id="hostId" name="hostId" value="<%=user.getId()%>" required /><br>

    <label for="roomName">방 이름:</label>
    <input type="text" id="roomName" name="roomName" value="기본방이름" required /><br>

    <input type="text" id="postcode" placeholder="우편번호">
    <input type="button" onclick="daumPostcode()" value="우편번호 찾기"><br>
    <input type="text" id="streetAddress" name="streetAddress" placeholder="도로명주소">
    <input type="text" id="jibunAddress" name="jibunAddress" placeholder="지번주소">
    <span id="guide" style="color:#999;display:none"></span>
    <input type="text" id="addressDetail" name="addressDetail" placeholder="상세주소">
    <input type="text" id="extraAddress" placeholder="참고항목">
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
        function daumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var roadAddr = data.roadAddress; // 도로명 주소 변수
                    var extraRoadAddr = ''; // 참고 항목 변수

                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraRoadAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraRoadAddr !== ''){
                        extraRoadAddr = ' (' + extraRoadAddr + ')';
                    }

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('postcode').value = data.zonecode;
                    document.getElementById("streetAddress").value = roadAddr;
                    document.getElementById("jibunAddress").value = data.jibunAddress;

                    // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                    if(roadAddr !== ''){
                        document.getElementById("extraAddress").value = extraRoadAddr;
                    } else {
                        document.getElementById("extraAddress").value = '';
                    }

                    var guideTextBox = document.getElementById("guide");
                    // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                    if(data.autoRoadAddress) {
                        var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                        guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                        guideTextBox.style.display = 'block';

                    } else if(data.autoJibunAddress) {
                        var expJibunAddr = data.autoJibunAddress;
                        guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                        guideTextBox.style.display = 'block';
                    } else {
                        guideTextBox.innerHTML = '';
                        guideTextBox.style.display = 'none';
                    }
                }
            }).open();
        }
    </script>

    <br>
<%--    <label for="jibunAddress">지번 주소:</label>--%>
<%--    <input type="text" id="jibunAddress" name="jibunAddress" value="기본지번주소" required /><br>--%>

<%--    <label for="streetAddress">도로 주소:</label>--%>
<%--    <input type="text" id="streetAddress" name="streetAddress" value="기본도로주소" required /><br>--%>

<%--    <label for="addressDetail">상세 주소:</label>--%>
<%--    <input type="text" id="addressDetail" name="addressDetail" value="기본상세주소" required /><br>--%>

    <label for="floor">층수:</label>
    <input type="number" id="floor" name="floor" value="1" required /><br>

    <label for="usableArea">사용 면적 (㎡):</label>
    <input type="number" id="usableArea" name="usableArea" value="50" required /><br>

    <label for="roomCount">방 개수:</label>
    <input type="number" id="roomCount" name="roomCount" value="2" required /><br>

    <label for="livingRoomCount">거실 개수:</label>
    <input type="number" id="livingRoomCount" name="livingRoomCount" value="1" required /><br>

    <label for="toiletCount">화장실 개수:</label>
    <input type="number" id="toiletCount" name="toiletCount" value="1" required /><br>

    <label for="kitchenCount">주방 개수:</label>
    <input type="number" id="kitchenCount" name="kitchenCount" value="1" required /><br>

    <label for="duplex">복층:</label>
    <input type="checkbox" id="duplex" name="duplex" checked /><br>

    <label for="elevator">엘리베이터:</label>
    <input type="checkbox" id="elevator" name="elevator" checked /><br>

    <label for="park">주차장:</label>
    <input type="checkbox" id="park" name="park" checked /><br>

    <label for="parkDetail">주차장 상세:</label>
    <input type="text" id="parkDetail" name="parkDetail" value="기본주차장상세" /><br>

    <label for="roomType">방 유형:</label>
    <input type="number" id="roomType" name="roomType" value="1" required /><br>

    <label for="minimumContract">최소 계약 기간 (주):</label>
    <input type="number" id="minimumContract" name="minimumContract" value="12" required /><br>

    <input type="hidden" id="approve" name="approve" value="0" required /><br>

    <input type="submit" value="방 추가하기" />
</form>
</body>
</html>
