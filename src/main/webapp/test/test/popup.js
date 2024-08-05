function openPopup() {
           var popup = window.open('popup', 'popupWindow', 'width=600,height=400');
           popup.focus();
       }

       function receiveData(data) {
           // 받은 데이터를 request 객체에 담는 대신, 페이지에 표시
           document.getElementById("result").innerText = data;
       }
	   
	   function sendData() {
	              var inputData = document.getElementById("inputData").value;
	              // 부모 창의 receiveData 함수 호출
	              window.opener.receiveData(inputData);
	              window.close(); // 팝업 닫기
	          }
			  
			 fnuction 