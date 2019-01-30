(function() {
        "use strict";

        // ----------
        // Events

        document.addEventListener("DOMContentLoaded", function() {
            // To ensure coord_x_display shows actual value after page refreshment
            let curRadius = parseInt(
                document.getElementById('coord_x').value, 10);
            document.getElementById('coord_x_display').innerHTML = curRadius;
        });

        document.getElementById("btn_check").addEventListener(
            "click", submitClick);
        document.getElementById("btn_add").addEventListener(
            "click", () => changeRadius(1));
        document.getElementById("btn_sub").addEventListener(
            "click", () => changeRadius(-1));
        
        // ----------
        // Event functions

        function submitClick() {
            if (validateInput())
                document.getElementById("check_form").submit();
        }

        function changeRadius(delta) {
            if (isNaN(delta))
                throw "Argument 'delta' should be a number.";

            // Parse, increment and decrement and update value
            let curRadius = parseInt(
                document.getElementById('coord_x').value, 10) + delta;
            curRadius = clamp(curRadius, -5, 3);
            document.getElementById('coord_x').value = curRadius;
            document.getElementById('coord_x_display').innerHTML = curRadius;
        }


        // ----------
        // Input validation

        //tra ve true neu san sang
        function validateInput() {
            if (!anyRadioChecked("r_value")) {
                showError("error R.");
                return false;
            }

            let y_value = document.getElementById("coord_y").value;
            if (!isNumberAndInRange(y_value, -5, 5)) {
                showError(" pls enter Y in the range (-5; 5).");
                return false;
            }
            
            clearError();
            return true;
        }

        function anyRadioChecked(name) {
            if (typeof name !== 'string' && !(name ins
                nceof String))
                throw "Argument 'name' should be a string.";

            let elements = document.getElementsByName(name);
            for (let i = 0; i < elements.length; i++)
                if (elements[i].type === 'radio' && elements[i].checked)
                    return true;
            return false;
        }

        // Kiểm tra nếu giá trị là một số hợp lệ trong phạm vi được chỉ định
        function isNumberAndInRange(value, min, max) {
            if (isNaN(min) || isNaN(max))
                throw "Arguments 'min' and 'max' should be numbers.";

            if (isNaN(value))
                return false;
            let floatVal = parseFloat(value);
            return (floatVal > min && floatVal < max);
        }

        function clamp(value, min, max) {
            if (isNaN(value) || isNaN(min) || isNaN(max))
                throw "Arguments 'value', 'min' and 'max' should be numbers.";
            if (min > max)
                [min, max] = [max, min];

            return Math.min(max, Math.max(value, min));
        }

        function showError(errorText) {
            if (typeof errorText !== 'string' && !(name instanceof String))
                throw "Argument 'errorText' should be a string.";

            let errorMsg = document.getElementById('error_msg');
            errorMsg.innerHTML = errorText + " <br>";
            errorMsg.style.display = "inline";
        }

        function clearError() {
            document.getElementById('error_msg').style.display = "none";
        }
    })();