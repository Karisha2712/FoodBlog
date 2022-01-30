let href = new URL(window.location.href);
let command = href.searchParams.get("command");
console.log(sessionStorage.getItem("locale"));

let id;
switch (command) {
    case "go_to_home_page":
        id = "home";
        break;
    case "go_to_recipes_page":
    case "view_full_recipe":
        id = "recipe";
        break;
    case "go_to_about_page":
        id = "about";
        break;
    case "go_to_profile_page":
        id = "profile";
        break;
    default :
        id = "home";
}

document.getElementById(id).classList.add("active");

