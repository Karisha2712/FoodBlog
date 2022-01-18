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
    <title><fmt:message key="page.name.sign.up" bundle="${rb}"/></title>
</head>

<jsp:include page="template/header.jsp"/>
<body>

<div class="page-content d-flex flex-row">

    <img class="sign-up-pic" src="images/sign_up_page.png" alt="...">

    <div class="sign-up-block d-flex flex-column">
        <h1 class="page-title">
            <fmt:message key="sign_up.title" bundle="${rb}"/>
        </h1>
        <div class="sub-text">
            <fmt:message key="sign_up.text" bundle="${rb}"/>
            <a class="sign-in-link" href="#"><b>
                <fmt:message key="sign_in.button" bundle="${rb}"/>
            </b></a>
        </div>
        <div class="form-floating mb-3 d-flex flex-column">
            <input type="text" class="field" id="floatingInput" placeholder=<fmt:message key="input.login.placeholder"
                                                                                         bundle="${rb}"/>>
            <input type="email" class="field" id="floatingEmail" placeholder=<fmt:message key="input.email.placeholder"
                                                                                          bundle="${rb}"/>>
            <input type="password" class="field" id="floatingPassword" placeholder=<fmt:message
                    key="input.password.placeholder" bundle="${rb}"/>>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                    <fmt:message key="sign_up.choose_role" bundle="${rb}"/>
                </label>
            </div>


            <button class="btn-primary">
                <fmt:message key="sign_up.button" bundle="${rb}"/>
            </button>
        </div>
    </div>
</div>
</body>

<jsp:include page="template/footer.jsp"/>

</html>