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
    <link href="${pageContext.request.contextPath}/style/header.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/error_page.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/footer.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/style/main_style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title><fmt:message key="page.name.error" bundle="${rb}"/></title>
</head>

<jsp:include page="../WEB-INF/pages/template/header.jsp"/>

<body>
<div class="page-content d-flex flex-column">
    <h1 class="page-main-title">
        500
    </h1>
    <h1 class="page-title">
        <fmt:message key="error.main.message" bundle="${rb}"/>
    </h1>
    <div class="sub-text">
        <fmt:message key="error500.sub.message" bundle="${rb}"/>
    </div>
    <form action="${pageContext.request.contextPath}/controller?command=go_to_home_page" method="post">
        <button class="btn-primary">
            <fmt:message key="error.button.backHome" bundle="${rb}"/>
        </button>
    </form>

</div>
</body>

<jsp:include page="../WEB-INF/pages/template/footer.jsp"/>

</html>