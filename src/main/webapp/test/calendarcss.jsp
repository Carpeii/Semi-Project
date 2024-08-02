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
            color: #ccc;
            cursor: default;
        }
    </style>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/css/bootstrap.css">
  <script src="${pageContext.request.contextPath}/webjars/bootstrap/5.3.3/js/bootstrap.js"></script>
</head>
<body>
<div class="container mt-4 custom-container">
    <table class="table calendar-table">
        <thead>
            <tr>
                <th>Sun</th>
                <th>Mon</th>
                <th>Tue</th>
                <th>Wed</th>
                <th>Thu</th>
                <th>Fri</th>
                <th>Sat</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="disabled"></td>
                <td class="disabled"></td>
                <td class="disabled"></td>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
            </tr>
            <tr>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>10</td>
                <td>11</td>
            </tr>
            <tr>
                <td>12</td>
                <td>13</td>
                <td>14</td>
                <td>15</td>
                <td>16</td>
                <td>17</td>
                <td>18</td>
            </tr>
            <tr>
                <td>19</td>
                <td>20</td>
                <td>21</td>
                <td>22</td>
                <td>23</td>
                <td>24</td>
                <td>25</td>
            </tr>
            <tr>
                <td>26</td>
                <td>27</td>
                <td>28</td>
                <td>29</td>
                <td>30</td>
                <td>31</td>
                <td></td>
            </tr>
        </tbody>
    </table>

    <div class="mt-4">
        <h5>Selected Date</h5>
        <div id="selected-date" class="alert alert-info" role="alert">
            <!-- Clicked date will appear here -->
        </div>
    </div>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const calendarTable = document.getElementById('calendar-table');
        const selectedTableBody = document.getElementById('selected-table').getElementsByTagName('tbody')[0];
        
        calendarTable.addEventListener('click', function(event) {
            const target = event.target;
            if (target.tagName === 'TD' && target.classList.contains('clickable') && target.textContent.trim() !== '') {
                // 클릭된 셀의 값 가져오기
                const cellValue = target.textContent.trim();
                
                // 선택된 날짜를 두 번째 테이블에 추가하기
                const newRow = document.createElement('tr');
                const newCell = document.createElement('td');
                newCell.textContent = cellValue;
                newRow.appendChild(newCell);
                selectedTableBody.appendChild(newRow);
            }
        });
    });
</script>
</body>
</html>