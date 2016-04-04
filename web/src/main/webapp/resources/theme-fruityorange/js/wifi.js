window.onload = init;

var hasPassword;
var password;


function init() {
    hasPassword = document.getElementById("box_hasPassword");
    password = document.getElementById("passwordfield");

    hasPassword.addEventListener("change", togglePassword);
    togglePassword();
}

function togglePassword() {
    if (hasPassword.checked)
        password.classList.remove("invisible");
    else
        password.classList.add("invisible");
}
