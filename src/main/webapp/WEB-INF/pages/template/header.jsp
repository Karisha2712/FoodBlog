<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="page" var="rb"/>

<header class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" id="home" aria-current="page"
                       href="${pageContext.request.contextPath}/controller?command=go_to_home_page">
                        <fmt:message key="header.home" bundle="${rb}"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link header-item"
                       href="${pageContext.request.contextPath}/controller?command=go_to_recipes_page">
                        <fmt:message key="header.recipe" bundle="${rb}"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link header-item"
                       href="${pageContext.request.contextPath}/controller?command=go_to_about_page">
                        <fmt:message key="header.about" bundle="${rb}"/>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link header-item dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <fmt:message key="header.en_lang" bundle="${rb}"/>
                    </a>
                    <ul class="dropdown-menu bg-dark" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="#">
                            <fmt:message key="header.ru_lang" bundle="${rb}"/>
                        </a></li>
                    </ul>
                </li>
                <c:if test="${sessionScope.user == null}">
                    <li class="nav-item">
                        <a class="nav-link" id="sign-in"
                           href="${pageContext.request.contextPath}/controller?command=go_to_sign_in_page">
                            <fmt:message key="header.sign" bundle="${rb}"/>
                        </a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.user != null}">
                    <li class="nav-item">
                        <a class="nav-link" id="profile"
                           href="${pageContext.request.contextPath}/controller?command=go_to_profile_page">
                                ${sessionScope.user.login}
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link header-item" id="logout"
                           href="${pageContext.request.contextPath}/controller?command=logout">
                            <fmt:message key="profile.logout" bundle="${rb}"/>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</header>