<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calendar Example</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    <style>
        .calendar {
            display: inline-block;
            border: 1px solid #ccc;
            padding: 10px;
            font-family: Arial, sans-serif;
        }
        .calendar table {
            width: 100%;
        }
        .calendar th,
        .calendar td {
            text-align: center;
            padding: 5px;
        }
        .calendar .day {
            width: 30px;
            height: 30px;
            line-height: 30px;
            border: 1px solid #ccc;
            cursor: pointer;
        }
        .calendar .day:hover {
            background-color: lightblue;
        }
        .calendar .selected {
            background-color: lightgreen;
        }
    </style>
</head>
<body>

<div class="calendar" id="calendar">
    <div class="month-year" id="monthYear"></div>
    <table id="calendarTable">
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
        <tbody id="calendarBody">
            <!-- Calendar days will be generated here -->
        </tbody>
    </table>
</div>

<script>
    // Function to generate calendar days
    function generateCalendar(year, month) {
        var startDate = new Date(year, month - 1, 1);
        var endDate = new Date(year, month, 0);

        var calendarBody = document.getElementById("calendarBody");
        calendarBody.innerHTML = "";

        var dayOfMonth = 1;
        var today = new Date();
        
        for (var i = 0; i < 6; i++) { // 6 weeks max
            var row = document.createElement("tr");

            for (var j = 0; j < 7; j++) {
                var cell = document.createElement("td");
                if (i === 0 && j < startDate.getDay()) {
                    // Blank cells before the first day of the month
                } else if (dayOfMonth <= endDate.getDate()) {
                    var day = document.createElement("div");
                    day.textContent = dayOfMonth;
                    day.className = "day";
                    day.addEventListener("click", function() {
                        // Handle day selection logic here
                        var selectedDay = this.textContent;
                        var selectedDate = year + "-" + (month < 10 ? '0' : '') + month + "-" + (selectedDay < 10 ? '0' : '') + selectedDay;
                        console.log("Selected date:", selectedDate);
                        // Example: highlight selected day
                        var selectedDays = document.querySelectorAll(".day.selected");
                        selectedDays.forEach(function(day) {
                            day.classList.remove("selected");
                        });
                        this.classList.add("selected");
                    });
                    if (today.getFullYear() === year && today.getMonth() + 1 === month && today.getDate() === dayOfMonth) {
                        day.classList.add("selected"); // Highlight today's date
                    }
                    cell.appendChild(day);
                    dayOfMonth++;
                }
                row.appendChild(cell);
            }
            calendarBody.appendChild(row);
            if (dayOfMonth > endDate.getDate()) {
                break;
            }
        }
    }

    // Initial calendar setup (current month)
    var currentDate = new Date();
    generateCalendar(currentDate.getFullYear(), currentDate.getMonth() + 1);

    // Example: Change month when clicking navigation buttons
    function prevMonth() {
        var currentMonth = currentDate.getMonth();
        currentDate.setMonth(currentMonth - 1);
        updateCalendar(currentDate);
    }

    function nextMonth() {
        var currentMonth = currentDate.getMonth();
        currentDate.setMonth(currentMonth + 1);
        updateCalendar(currentDate);
    }

    function updateCalendar(date) {
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var monthYear = document.getElementById("monthYear");
        monthYear.textContent = getMonthName(month) + " " + year;
        generateCalendar(year, month);
    }

    function getMonthName(month) {
        var months = ["January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"];
        return months[month - 1];
    }
</script>

</body>
</html>
