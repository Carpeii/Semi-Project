<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Room Options</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h1>Add Room Options</h1>
<form id="optionForm" method="post" action="<%=request.getContextPath() %>/service/roomOptionAdd">
    <input type="hidden" name="roomId" value="<%= request.getAttribute("roomId") %>">
    <input type="hidden" name="optionsJson" id="optionsJson">

    <h2>Basic Options</h2>
    <label><input type="checkbox" name="options" value="Refrigerator"> Refrigerator</label><br>
    <label><input type="checkbox" name="options" value="Washing Machine"> Washing Machine</label><br>
    <label><input type="checkbox" name="options" value="Air Conditioner"> Air Conditioner</label><br>
    <label><input type="checkbox" name="options" value="Sink"> Sink</label><br>
    <label><input type="checkbox" name="options" value="Bed"> Bed</label><br>
    <label><input type="checkbox" name="options" value="TV"> TV</label><br>
    <label><input type="checkbox" name="options" value="Internet"> Internet</label><br>

    <h2>Additional Options</h2>
    <label><input type="checkbox" name="options" value="Door Lock"> Door Lock</label><br>
    <label><input type="checkbox" name="options" value="CCTV"> CCTV</label><br>
    <label><input type="checkbox" name="options" value="Management Office"> Management Office</label><br>
    <label><input type="checkbox" name="options" value="Gas Range"> Gas Range</label><br>
    <label><input type="checkbox" name="options" value="Induction"> Induction</label><br>
    <label><input type="checkbox" name="options" value="Microwave"> Microwave</label><br>
    <label><input type="checkbox" name="options" value="Dining Table"> Dining Table</label><br>
    <label><input type="checkbox" name="options" value="Shoe Rack"> Shoe Rack</label><br>
    <label><input type="checkbox" name="options" value="Wardrobe"> Wardrobe</label><br>
    <label><input type="checkbox" name="options" value="Dressing Room"> Dressing Room</label><br>
    <label><input type="checkbox" name="options" value="Vanity"> Vanity</label><br>
    <label><input type="checkbox" name="options" value="Cable TV"> Cable TV</label><br>
    <label><input type="checkbox" name="options" value="Sofa"> Sofa</label><br>
    <label><input type="checkbox" name="options" value="Desk"> Desk</label><br>
    <label><input type="checkbox" name="options" value="Curtains"> Curtains</label><br>
    <label><input type="checkbox" name="options" value="Balcony/Veranda"> Balcony/Veranda</label><br>

    <h2>Convenience Options</h2>
    <label><input type="checkbox" name="options" value="Heater"> Heater</label><br>
    <label><input type="checkbox" name="options" value="Air Purifier"> Air Purifier</label><br>
    <label><input type="checkbox" name="options" value="Dryer"> Dryer</label><br>
    <label><input type="checkbox" name="options" value="Iron"> Iron</label><br>
    <label><input type="checkbox" name="options" value="Water Dispenser"> Water Dispenser</label><br>
    <label><input type="checkbox" name="options" value="Rice Cooker"> Rice Cooker</label><br>
    <label><input type="checkbox" name="options" value="Electric Kettle"> Electric Kettle</label><br>
    <label><input type="checkbox" name="options" value="Tableware"> Tableware</label><br>
    <label><input type="checkbox" name="options" value="Cooking Utensils"> Cooking Utensils</label><br>
    <label><input type="checkbox" name="options" value="Bathtub"> Bathtub</label><br>
    <label><input type="checkbox" name="options" value="Hair Dryer"> Hair Dryer</label><br>
    <label><input type="checkbox" name="options" value="Bidet"> Bidet</label><br>

    <h2>Pet Friendly</h2>
    <label><input type="checkbox" name="options" value="Pet Friendly"> Pet Friendly</label><br>

    <input type="submit" value="Submit Options" onclick="submitForm(event)">
</form>

<script>
    function submitForm(event) {
        event.preventDefault(); // Prevent the default form submission
        var checkboxes = document.querySelectorAll('input[name="options"]:checked');
        var selectedValues = [];
        checkboxes.forEach((checkbox) => {
            selectedValues.push(checkbox.value);
        });
        var optionsJson = JSON.stringify({ options: selectedValues });
        document.getElementById('optionsJson').value = optionsJson; // Set the JSON string to the hidden field
        document.getElementById('optionForm').submit(); // Submit the form
    }
</script>

</body>
</html>
