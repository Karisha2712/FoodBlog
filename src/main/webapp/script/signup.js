let login = document.getElementById("login");
let email = document.getElementById("email");
let password = document.getElementById("password");
let isAdmin = document.querySelector("#is_admin");
let signupBtn = document.getElementById("sign_up_btn");

signupBtn.addEventListener("click", (event) => {
    sessionStorage.setItem("login", login.value);
    sessionStorage.setItem("email", email.value);
    sessionStorage.setItem("password", password.value);
    if (isAdmin.checked) {
        sessionStorage.setItem("is_admin", "true");
    } else {
        sessionStorage.setItem("is_admin", "false");
    }
});

let storedLogin = sessionStorage.getItem("login");
let storedEmail = sessionStorage.getItem("email");

if (storedLogin !== null) {
    login.value = storedLogin;
}

if (storedEmail !== null) {
    email.value = storedEmail;
}
