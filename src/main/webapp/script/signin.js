let email = document.getElementById("email");
let password = document.getElementById("password");
let isAdmin = document.querySelector("#is-admin");
let signInBtn = document.getElementById("sign_btn");

signInBtn.addEventListener("click", () => {
    sessionStorage.setItem("email", email.value);
    sessionStorage.setItem("password", password.value);
    if (isAdmin.checked) {
        sessionStorage.setItem("is_admin", "true");
    } else {
        sessionStorage.setItem("is_admin", "false");
    }
});

let storedEmail = sessionStorage.getItem("email");

if (storedEmail !== null) {
    email.value = storedEmail;
}
