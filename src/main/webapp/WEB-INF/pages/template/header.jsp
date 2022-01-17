<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="page" var="rb"/>

<header class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" id="home" aria-current="page" href="#">
                        <fmt:message key="header.home" bundle="${rb}"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link header-item" href="#">
                        <fmt:message key="header.recipe" bundle="${rb}"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link header-item" href="#">
                        <fmt:message key="header.about" bundle="${rb}"/>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link header-item dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="header.EnLang" bundle="${rb}"/>
                    </a>
                    <ul class="dropdown-menu bg-dark" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="#">
                            <fmt:message key="header.RuLang" bundle="${rb}"/>
                        </a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="sign-in" href="#">
                        <fmt:message key="header.sign" bundle="${rb}"/>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</header>