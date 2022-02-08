<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.radyuk.foodblog.entity.UserRole" %>
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
    <link href="style/profile_page.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title><fmt:message key="page.name.home" bundle="${rb}"/></title>
</head>

<jsp:include page="../template/header.jsp"/>


<body>
<div class="body">
    <c:if test="${requestScope.blogger_info != null}">
        <div class="profile-info d-flex flex-row">
            <div class="blogger-avatar d-flex flex-column">
                <img alt="..."
                     src="${pageContext.request.contextPath}/picture?picture_path=${requestScope.blogger_info.avatarPath}">
            </div>
            <div class="blogger-info d-flex flex-column">
                <div class="blogger-name">${requestScope.blogger_info.userLogin}</div>
                <div class="blogger-age">${requestScope.blogger_info.bloggerAge}</div>
                <div class="blogger-city">${requestScope.blogger_info.city},
                        ${requestScope.blogger_info.country}</div>
                <div class="personal-info">
                        ${requestScope.blogger_info.personalInfo}
                </div>
                <c:if test="${sessionScope.user != null
            && requestScope.blogger_info.userLogin == sessionScope.user.login}">
                    <a href="${pageContext.request.contextPath}/controller?command=go_to_edit_blogger_info"
                       class="edit-info">
                        <fmt:message key="profile.edit.info" bundle="${rb}"/>
                    </a>
                </c:if>
            </div>
        </div>
    </c:if>

    <div class="page-head d-flex flex-row">
    <span class="page-title">
        <fmt:message key="recipes.title" bundle="${rb}"/>
    </span>
        <c:if test="${sessionScope.user != null
     && requestScope.blogger_info != null && requestScope.blogger_info.userLogin == sessionScope.user.login}">
            <a class="btn-primary" href="${pageContext.request.contextPath}/controller?command=go_to_add_new_post">
                <fmt:message key="profile.new.recipe" bundle="${rb}"/>
            </a>
        </c:if>
    </div>

    <div class="page-content d-flex flex-row flex-wrap">
        <c:if test="${requestScope.no_posts != null}">
            <div>
                <fmt:message key="recipes.no_any_recipes" bundle="${rb}"/>
            </div>
        </c:if>
        <c:forEach items="${requestScope.user_recipe_posts}" var="post">
            <div class="recipe d-flex flex-column">
                <img class="recipe-img"
                     src="${pageContext.request.contextPath}/picture?picture_path=${post.picturePath}"
                     alt="${post.dishName}">
                <form class="rating-with-category"
                      action="${pageContext.request.contextPath}/controller?command=delete_post&post_id=${post.postId}"
                      method="post">
                    <div class="category" id="${post.recipePostCategory}">
                            ${post.recipePostCategory}
                    </div>
                    <div class="stars" style="--rating: ${post.postRating};"></div>
                    <c:if test="${sessionScope.user != null && requestScope.blogger_info != null
                && (requestScope.blogger_info.userLogin == sessionScope.user.login
                || sessionScope.user.userRole eq UserRole.ADMIN)}">
                        <button class="delete-button">
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
                                 class="bi bi-trash3" viewBox="0 0 16 16">
                                <path fill-rule="evenodd"
                                      d="M6.5 1a.5.5 0 0 0-.5.5v1h4v-1a.5.5 0 0 0-.5-.5h-3ZM11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3A1.5 1.5 0 0 0 5 1.5v1H2.506a.58.58 0 0 0-.01 0H1.5a.5.5 0 0 0 0 1h.538l.853 10.66A2 2 0 0 0 4.885 16h6.23a2 2 0 0 0 1.994-1.84l.853-10.66h.538a.5.5 0 0 0 0-1h-.995a.59.59 0 0 0-.01 0H11Zm1.958 1H3.042l.846 10.58a1 1 0 0 0 .997.92h6.23a1 1 0 0 0 .997-.92l.846-10.58Zm-7.487 1a.5.5 0 0 1 .528.47l.5 8.5a.5.5 0 0 1-.998.06L5 5.03a.5.5 0 0 1 .47-.53Zm5.058 0a.5.5 0 0 1 .47.53l-.5 8.5a.5.5 0 1 1-.998-.06l.5-8.5a.5.5 0 0 1 .528-.47ZM8 4.5a.5.5 0 0 1 .5.5v8.5a.5.5 0 0 1-1 0V5a.5.5 0 0 1 .5-.5Z"/>
                            </svg>
                        </button>
                    </c:if>
                </form>
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
</div>
</body>


<jsp:include page="../template/footer.jsp"/>

</html>