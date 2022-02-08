<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom" %>

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
    <link href="style/home_page.css" rel="stylesheet">
    <link href="style/footer.css" rel="stylesheet">
    <link href="style/main_style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title><fmt:message key="page.name.home" bundle="${rb}"/></title>
</head>

<jsp:include page="template/header.jsp"/>

<body>

<h1 class="page-title">
    <fmt:message key="home.title" bundle="${rb}"/>
</h1>

<div class="page-content d-flex flex-row">

    <img class="home-page-pic" src="images/main_page_1.png" alt="...">

    <img class="home-page-pic" src="images/main_page_2.png" alt="...">

    <div class="CTA-block d-flex flex-column">
        <div class="main-text">
            <fmt:message key="home.main_text" bundle="${rb}"/>
        </div>
        <a class="btn-primary" href="${pageContext.request.contextPath}/controller?command=go_to_recipes_page&page=1">
            <fmt:message key="home.button.view_recipes" bundle="${rb}"/>
        </a>
    </div>
</div>
</body>

<jsp:include page="template/footer.jsp"/>

</html>