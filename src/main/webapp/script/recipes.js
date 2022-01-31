let searchBtn = document.getElementById("search_form");
let searchValue = document.getElementById("search_value");

searchBtn.addEventListener("submit", () => {
    sessionStorage.setItem("search", searchValue.value);
});

let storedSearchValue = sessionStorage.getItem("search");

if (storedSearchValue !== null) {
    searchValue.value = storedSearchValue;
}