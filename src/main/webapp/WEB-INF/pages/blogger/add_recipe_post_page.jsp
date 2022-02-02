<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <c:if test="${sessionScope.add_post_error != null}">
            <div id="" class="alert alert-danger d-flex flex-row" role="alert">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                     class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img"
                     aria-label="Warning:">
                    <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                </svg>
                <div>
                    <fmt:message key="${sessionScope.add_post_error}" bundle="${rb}"/>
                </div>
            </div>
        </c:if>
        <form id="form" method="post" class="form-floating mb-3 d-flex flex-column"
              action="${pageContext.request.contextPath}/controller?command=add_recipe_post"
              enctype='multipart/form-data'>
            <input type="text" class="field" id="dish-name" name="dish_name"
                   placeholder=<fmt:message key="add_recipe.dish_name" bundle="${rb}"/>>
            <div class="dropdown">
                <select class="form-select field btn-secondary " id="role_select" name="recipe_category">
                    <option class="dropdown-item" selected value="SALAD">
                        <fmt:message key="recipes.category.salad" bundle="${rb}"/>
                    </option>
                    <option class="dropdown-item" value="MAIN">
                        <fmt:message key="recipes.category.main" bundle="${rb}"/>
                    </option>
                    <option class="dropdown-item" value="SOUP">
                        <fmt:message key="recipes.category.soup" bundle="${rb}"/>
                    </option>
                    <option class="dropdown-item" value="APPETIZER">
                        <fmt:message key="recipes.category.appetizer" bundle="${rb}"/>
                    </option>
                    <option class="dropdown-item" value="DESSERT">
                        <fmt:message key="recipes.category.dessert" bundle="${rb}"/>
                    </option>
                </select>
            </div>
            <div>
                <label class="field" id="file-upload-label" for="fileInput">
                    <fmt:message key="add_recipe.choose_file" bundle="${rb}"/>
                </label>
                <input type="file" name="post_picture" id="fileInput" accept="image/png" hidden/>
            </div>
            <div class="form-floating">
                <textarea class="form-control text-area" name="recipe_text" style="height: 6vw"
                          placeholder=<fmt:message key="add_recipe.recipe_text" bundle="${rb}"/>></textarea>
            </div>
            <button class="btn-primary" type="submit" style="margin-top: 2vw;">
                <fmt:message key="add_recipe.button" bundle="${rb}"/>
            </button>
        </form>
    </div>
</div>
</body>
<script src="script/file_input.js"></script>
<jsp:include page="../template/footer.jsp"/>

</html>