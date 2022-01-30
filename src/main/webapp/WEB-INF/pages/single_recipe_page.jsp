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
                <div class="avatar d-flex flex-row">
                    <img src="${pageContext.request.contextPath}/picture?picture_path=${requestScope.post.userPicturePath}"
                         alt="..."/>
                </div>
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
            <form class="your-comment"
                  action="${pageContext.request.contextPath}/controller?command=comment&user_id=${sessionScope.user.entityId}&post_id=${requestScope.post.postId}"
                  method="post">
                <div class="post-author d-flex flex-row">
                    src="${pageContext.request.contextPath}/picture?picture_path=${requestScope.post.userPicturePath}"
                    <div class="avatar d-flex flex-row">
                        <img src="images/default_avatar.png" alt="..."/>
                    </div>
                    <div class="author-name">
                        <fmt:message key="recipe.comment.your_comment" bundle="${rb}"/>
                    </div>
                </div>
                <c:if test="${sessionScope.comment_error != null}">
                    <div id="" class="alert alert-danger d-flex flex-row" role="alert">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img"
                             aria-label="Warning:">
                            <path
                                    d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                        </svg>
                        <div>
                            <fmt:message key="${sessionScope.comment_error}" bundle="${rb}"/>
                        </div>
                    </div>
                </c:if>
                <div class="form-floating">
                <textarea class="form-control" id="comment_text" name="comment_text"
                          style="height: 100px" placeholder="Comment text"></textarea>
                </div>
                <div class="rating-range d-flex flex-column">
                    <label for="mark" class="form-label">
                        <fmt:message key="recipe.rating" bundle="${rb}"/>
                    </label>
                    <input type="range" class="form-range" min="0" max="5" step="1" id="mark" name="mark">
                </div>
                <button class="btn-primary" type="submit">
                    <fmt:message key="recipe.comment.send" bundle="${rb}"/>
                </button>
            </form>
        </c:if>

        <c:forEach items="${requestScope.comments}" var="comment">
            <div class="some-comment">
                <div class="post-author d-flex flex-row">
                    <div class="avatar d-flex flex-row">
                        <img src="${pageContext.request.contextPath}/picture?picture_path=${comment.userPicturePath}"
                             alt="..."/>
                    </div>
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