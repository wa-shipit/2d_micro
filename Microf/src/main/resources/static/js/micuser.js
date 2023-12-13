// js/validation.js

function validateForm() {
    var loginid = document.getElementById("loginid").value;
    var password = document.getElementById("password").value;

    if (loginid.length < 11 || password.length < 11) {
        alert("Login ID and Password must be 10 characters or fewer.");
        return false;
    }

    return true;
}
