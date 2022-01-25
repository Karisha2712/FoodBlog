<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="page" var="rb"/>

<!DOCTYPE html>
<html lang="${sessionScope.locale}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
    <link href="style/header.css" rel="stylesheet">
    <link href="style/footer.css" rel="stylesheet">
    <link href="style/main_style.css" rel="stylesheet">
    <link href="style/sign_up_page.css" rel="stylesheet">
    <link href="style/add_recipe_page.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title><fmt:message key="page.name.add_recipe" bundle="${rb}"/></title>
</head>

<jsp:include page="../template/header.jsp"/>

<body>

<div class="page-content d-flex flex-row">

    <img class="sign-up-pic" src="images/new_post_1.png" alt="...">

    <div class="sign-up-block d-flex flex-column">
        <h1 class="page-title">
            <fmt:message key="add_recipe.title" bundle="${rb}"/>
        </h1>
        <div class="form-floating mb-3 d-flex flex-column">
            <input type="text" class="field" id="dish-name"
                   placeholder=
            <fmt:message key="add_recipe.dish_name" bundle="${rb}"/>>
            <div class="dropdown">
                <button class="field btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1"
                        data-bs-toggle="dropdown" aria-expanded="false">
                    <fmt:message key="recipes.category.salad" bundle="${rb}"/>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                    <li><a class="dropdown-item" href="#">
                        <fmt:message key="recipes.category.main" bundle="${rb}"/>
                    </a></li>
                    <li><a class="dropdown-item" href="#">
                        <fmt:message key="recipes.category.soup" bundle="${rb}"/>
                    </a></li>
                    <li><a class="dropdown-item" href="#">
                        <fmt:message key="recipes.category.appetizer" bundle="${rb}"/>
                    </a></li>
                    <li><a class="dropdown-item" href="#">
                        <fmt:message key="recipes.category.dessert" bundle="${rb}"/>
                    </a></li>
                </ul>
            </div>
            <div>
                <label class="field" id="file-upload-label" for="file-upload">
                    <fmt:message key="add_recipe.choose_file" bundle="${rb}"/>
                </label>
                <input type="file" id="file-upload" hidden/>
            </div>
            <div class="form-floating">
                    <textarea class="form-control text-area"
                              placeholder=
                              <fmt:message key="add_recipe.recipe_text" bundle="${rb}"/> id=""
                              style="height: 6vw"></textarea>
            </div>

        </div>

        <button class="btn-primary" style="margin-top: 2vw;">
            <fmt:message key="add_recipe.button" bundle="${rb}"/>
        </button>
    </div>
</div>
</body>

<jsp:include page="../template/footer.jsp"/>

</html>