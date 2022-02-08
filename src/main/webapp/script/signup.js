let login = document.getElementById("login");
let email = document.getElementById("email");
let password = document.getElementById("password");
let isAdmin = document.querySelector("#is_admin");
let signupBtn = document.getElementById("sign_btn");

signupBtn.addEventListener("click", () => {
    sessionStorage.setItem("login", login.value);
    sessionStorage.setItem("email", email.value);
    sessionStorage.setItem("password", password.value);
});

let storedLogin = sessionStorage.getItem("login");
let storedEmail = sessionStorage.getItem("email");

if (storedLogin !== null) {
    login.value = storedLogin;
}

if (storedEmail !== null) {
    email.value = storedEmail;
}
