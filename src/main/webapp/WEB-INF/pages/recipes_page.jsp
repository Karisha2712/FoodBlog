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
    <link href="style/recipes_page.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title><fmt:message key="page.name.recipes" bundle="${rb}"/></title>
</head>

<jsp:include page="template/header.jsp"/>

<body>
<div class="form-floating mb-3">
    <input type="text" class="search" id="floatingInput"
           placeholder=<fmt:message key="input.search.placeholder" bundle="${rb}"/>>
</div>

<h1 class="page-title"><fmt:message key="recipes.title" bundle="${rb}"/></h1>

<c:if test="${requestScope.no_posts != null}">
    <div class="page-title">
        <fmt:message key="recipes.no_any_recipes" bundle="${rb}"/>
    </div>
</c:if>

<div class="page-content d-flex flex-row">

    <c:forEach items="${requestScope.recipe_posts}" var="post">
        <div class="recipe d-flex flex-column">
            <div class="post-author d-flex flex-row">
                <img class="avatar"
                     src="${pageContext.request.contextPath}/picture?picture_path=${post.userPicturePath}" alt="..."/>
                <div class="author-name">
                        ${post.userLogin}
                </div>
            </div>
            <img class="recipe-img"
                 src="${pageContext.request.contextPath}/picture?picture_path=${post.picturePath}"
                 alt="${post.dishName}">
            <div class="rating-with-category d-flex flex-row">
                <div class="category" id="${post.recipePostCategory}">
                        ${post.recipePostCategory}
                </div>
                <div class="stars" style="--rating: ${post.postRating};"></div>
            </div>
            <div class="recipe-block">
                <div class="dish-name">
                        ${post.dishName}
                </div>
                <div class="recipe-text">
                        ${post.recipeText}
                </div>

            </div>
            <a href="${pageContext.request.contextPath}/controller?command=view_full_recipe&post_id=${post.postId}"
               class="view-full-recipe">
                <fmt:message key="recipes.full_recipe_link" bundle="${rb}"/>
            </a>
        </div>
    </c:forEach>
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