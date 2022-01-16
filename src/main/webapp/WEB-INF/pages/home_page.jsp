<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang=en>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
    <link href="style/header.css" rel="stylesheet">
    <link href="style/home_page.css" rel="stylesheet">
    <link href="style/footer.css" rel="stylesheet">
    <link href="style/main_style.css" rel="stylesheet">
    <title>Home page</title>
</head>

<jsp:include page="template/header.jsp"/>

<body>

<div class="form-floating mb-3">
    <input type="text" class="search" id="floatingInput" placeholder="Search...">
</div>

<h1 class="page-title">FOOD BLOG</h1>

<div class="page-content d-flex flex-row">

    <img class="home-page-pic" src="images/main_page_1.png" alt="...">

    <img class="home-page-pic" src="images/main_page_2.png" alt="...">

    <div class="CTA-block d-flex flex-column">
        <div class="main-text">
            Delicious recipes from all over the world
        </div>
        <button class="btn-primary">
            View the recipes
        </button>
    </div>

</div>
</div>
</body>

<jsp:include page="template/footer.jsp"/>

</html>