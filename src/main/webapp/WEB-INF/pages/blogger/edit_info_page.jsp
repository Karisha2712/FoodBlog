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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title><fmt:message key="page.name.edit_info" bundle="${rb}"/></title>
</head>

<jsp:include page="../template/header.jsp"/>

<body>

<div class="page-content d-flex flex-row">

    <img class="sign-up-pic" src="images/about_me_1.png" alt="...">

    <div class="sign-up-block d-flex flex-column">
        <h1 class="page-title">
            <fmt:message key="edit_info.title" bundle="${rb}"/>
        </h1>
        <div class="form-floating mb-3 d-flex flex-column">
            <input type="text" class="field" id="age"
                   placeholder= <fmt:message key="edit_info.age" bundle="${rb}"/>>
            <input type="text" class="field" id="city"
                   placeholder= <fmt:message key="edit_info.city" bundle="${rb}"/>>
            <input type="text" class="field" id="country"
                   placeholder= <fmt:message key="edit_info.country" bundle="${rb}"/>>
            <input type="text" class="field" id="info-text"
                   placeholder= <fmt:message key="edit_info.info_text" bundle="${rb}"/>>

        </div>

        <button class="btn-primary" style="margin-top: 2vw;">
            <fmt:message key="edit_info.save_button" bundle="${rb}"/>
        </button>
    </div>
</div>

</body>

<jsp:include page="../template/footer.jsp"/>

</html>