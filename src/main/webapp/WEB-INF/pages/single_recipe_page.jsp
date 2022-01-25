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
    <link href="style/single_recipe_page.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title>${requestScope.post.dishName}<</title>
</head>

<jsp:include page="template/header.jsp"/>

<body>

<div class="page-content d-flex flex-column">
    <h1 class="page-title">${requestScope.post.dishName}</h1>
    <div class="date">${requestScope.post.postDate}</div>
    <div class="recipe-block d-flex flex-row">
        <img class="recipe-img"
             src="${pageContext.request.contextPath}/picture?picture_path=${requestScope.post.picturePath}" alt="...">
        <div class="recipe-info d-flex flex-column">
            <div class="post-author d-flex flex-row">
                <img class="avatar"
                     src="${pageContext.request.contextPath}/picture?picture_path=${requestScope.post.userPicturePath}"
                     alt="..."/>
                <div class="author-name">
                    ${requestScope.post.userLogin}
                </div>
            </div>
            <div class="stars" style="--rating: ${requestScope.post.postRating}"></div>
            <div class="category" id="${requestScope.post.recipePostCategory}">
                ${requestScope.post.recipePostCategory}
            </div>
            <div class="mini-title">
                <fmt:message key="recipe.sub_title" bundle="${rb}"/>
            </div>
            <div class="recipe-text">
                ${requestScope.post.recipeText}
            </div>
        </div>
    </div>


    <div class="comments-block d-flex flex-column">

        <div class="sub-title">
            <fmt:message key="recipe.title.comment" bundle="${rb}"/>
        </div>

        <c:if test="${sessionScope.user != null}">
            <div class="your-comment">
                <div class="post-author d-flex flex-row">
                    <img class="avatar" src="images/default_avatar.png" alt="..."/>
                    <div class="author-name">
                        <fmt:message key="recipe.comment.your_comment" bundle="${rb}"/>
                    </div>
                </div>
                <div class="form-floating">
                <textarea class="form-control" id="floatingTextarea2"
                          style="height: 100px"></textarea>
                </div>
                <div class="rating-range d-flex flex-column">
                    <label for="customRange" class="form-label">
                        <fmt:message key="recipe.rating" bundle="${rb}"/>
                    </label>
                    <input type="range" class="form-range" min="1" max="5" step="1" id="customRange">
                </div>
                <button class="btn-primary">
                    <fmt:message key="recipe.comment.send" bundle="${rb}"/>
                </button>
            </div>
        </c:if>

        <c:forEach items="${requestScope.comments}" var="comment">
            <div class="some-comment">
                <div class="post-author d-flex flex-row">
                    <img class="avatar"
                         src="${pageContext.request.contextPath}/picture?picture_path=${comment.userPicturePath}"
                         alt="..."/>
                    <div class="author-name">
                            ${comment.userLogin}
                    </div>
                </div>
                <div class="date">${comment.commentDate}</div>
                <div class="comment-text"> ${comment.commentText}
                </div>
                <div class="stars" style="--rating: ${comment.mark};"></div>
            </div>
        </c:forEach>

    </div>
</div>

</body>

<jsp:include page="template/footer.jsp"/>

</html>