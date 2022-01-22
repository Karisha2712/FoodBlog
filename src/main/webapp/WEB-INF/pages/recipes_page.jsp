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
    <link href="style/recipes_page.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title><fmt:message key="page.name.recipes" bundle="${rb}"/></title>
</head>

<jsp:include page="template/header.jsp"/>

<body>
<div class="form-floating mb-3">
    <input type="text" class="search" id="floatingInput" placeholder=<fmt:message key="input.search.placeholder"
                                                                                  bundle="${rb}"/>>
</div>

<h1 class="page-title"><fmt:message key="recipes.title" bundle="${rb}"/></h1>

<div class="page-content d-flex flex-row">
    <div class="recipe d-flex flex-column">
        <div class="post-author d-flex flex-row">
            <img class="avatar" src="images/default_avatar.png" alt="..."/>
            <div class="author-name">
                AuthorName
            </div>
        </div>
        <img class="recipe-img" src="images/recipe_1.png" alt="...">
        <div class="rating-with-category d-flex flex-row">
            <div class="category" style="background-color: #7CE43C">
                <fmt:message key="recipes.category.salad" bundle="${rb}"/>
            </div>
            <div class="stars" style="--rating: 3.4;"></div>
        </div>
        <div class="recipe-block">
            <div class="dish-name">
                Cesar salad
            </div>
            <div class="recipe-text">
                Ingredients: cucumbers, chicken, green salad, eggs, salt, pepper, tomatoes, cheese, garlic, mustard,
                olive oil.<br/>
                Steps: Chop together anchovy fillets, garlic, and pinch of salt. Use the side of a knife blade to mash
                into a paste,
                then scrape into a medium bowl. Whisk in egg yolks, 2 Tbsp. lemon juice, and mustard. Adding drop by
                drop to start,
                gradually whisk in olive oil, then vegetable oil; whisk until dressing is thick and glossy.
            </div>

        </div>
        <a href="#" class="view-full-recipe">
            <fmt:message key="recipes.full_recipe_link" bundle="${rb}"/>
        </a>
    </div>

    <div class="recipe d-flex flex-column">
        <div class="post-author d-flex flex-row">
            <img class="avatar" src="images/default_avatar.png" alt="..."/>
            <div class="author-name">
                AuthorName
            </div>
        </div>
        <img class="recipe-img" src="images/recipe_2.png" alt="...">
        <div class="rating-with-category d-flex flex-row">
            <div class="category" style="background-color: #ff7b64">
                <fmt:message key="recipes.category.soup" bundle="${rb}"/>
            </div>
            <div class="stars" style="--rating: 3.4;"></div>
        </div>
        <div class="recipe-block">
            <div class="dish-name">
                Cesar salad
            </div>
            <div class="recipe-text">
                Ingredients: cucumbers, chicken, green salad, eggs, salt, pepper, tomatoes, cheese, garlic, mustard,
                olive oil.<br/>
                Steps: Chop together anchovy fillets, garlic, and pinch of salt. Use the side of a knife blade to mash
                into a paste,
                then scrape into a medium bowl. Whisk in egg yolks, 2 Tbsp. lemon juice, and mustard. Adding drop by
                drop to start,
                gradually whisk in olive oil, then vegetable oil; whisk until dressing is thick and glossy.
            </div>

        </div>
        <a href="#" class="view-full-recipe">
            <fmt:message key="recipes.full_recipe_link" bundle="${rb}"/>
        </a>
    </div>

    <div class="recipe d-flex flex-column">
        <div class="post-author d-flex flex-row">
            <img class="avatar" src="images/default_avatar.png" alt="..."/>
            <div class="author-name">
                AuthorName
            </div>
        </div>
        <img class="recipe-img" src="images/recipe_3.png" alt="...">
        <div class="rating-with-category d-flex flex-row">
            <div class="category" style="background-color: #ff9f5f">
                <fmt:message key="recipes.category.main" bundle="${rb}"/>
            </div>
            <div class="stars" style="--rating: 3.4;"></div>
        </div>
        <div class="recipe-block">
            <div class="dish-name">
                Cesar salad
            </div>
            <div class="recipe-text">
                Ingredients: cucumbers, chicken, green salad, eggs, salt, pepper, tomatoes, cheese, garlic, mustard,
                olive oil.<br/>
                Steps: Chop together anchovy fillets, garlic, and pinch of salt. Use the side of a knife blade to mash
                into a paste,
                then scrape into a medium bowl. Whisk in egg yolks, 2 Tbsp. lemon juice, and mustard. Adding drop by
                drop to start,
                gradually whisk in olive oil, then vegetable oil; whisk until dressing is thick and glossy.
            </div>

        </div>
        <a href="#" class="view-full-recipe">
            <fmt:message key="recipes.full_recipe_link" bundle="${rb}"/>
        </a>
    </div>


</div>

<nav aria-label="...">
    <ul class="pagination pagination-lg">
        <li class="page-item active" aria-current="page">
            <span class="page-link">1</span>
        </li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
    </ul>
</nav>


</body>

<jsp:include page="template/footer.jsp"/>

</html>