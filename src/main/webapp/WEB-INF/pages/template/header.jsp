<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.radyuk.foodblog.entity.UserRole" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="page" var="rb"/>

<header class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" id="home" aria-current="page"
                       href="${pageContext.request.contextPath}/controller?command=go_to_home_page">
                        <fmt:message key="header.home" bundle="${rb}"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link header-item" id="recipe"
                       href="${pageContext.request.contextPath}/controller?command=go_to_recipes_page&page=1">
                        <fmt:message key="header.recipe" bundle="${rb}"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link header-item" id="about"
                       href="${pageContext.request.contextPath}/controller?command=go_to_about_page">
                        <fmt:message key="header.about" bundle="${rb}"/>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link header-item dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <c:if test="${sessionScope.locale == 'en'}">
                            <fmt:message key="header.en_lang" bundle="${rb}"/>
                        </c:if>
                        <c:if test="${sessionScope.locale == 'ru'}">
                            <fmt:message key="header.ru_lang" bundle="${rb}"/>
                        </c:if>
                    </a>
                    <ul class="dropdown-menu bg-dark" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=change_language">
                            <c:if test="${sessionScope.locale == 'en'}">
                                <fmt:message key="header.ru_lang" bundle="${rb}"/>
                            </c:if>
                            <c:if test="${sessionScope.locale == 'ru'}">
                                <fmt:message key="header.en_lang" bundle="${rb}"/>
                            </c:if>
                        </a></li>
                    </ul>
                </li>
                <c:choose>
                    <c:when test="${sessionScope.user == null}">
                        <li class="nav-item">
                            <a class="nav-link" id="sign-in"
                               href="${pageContext.request.contextPath}/controller?command=go_to_sign_in_page">
                                <fmt:message key="header.sign" bundle="${rb}"/>
                            </a>
                        </li>
                    </c:when>
                    <c:when test="${sessionScope.user.userRole ne UserRole.ADMIN}">
                        <li class="nav-item">
                            <a class="nav-link" id="profile"
                               href="${pageContext.request.contextPath}/controller?command=go_to_profile_page&user_id=${sessionScope.user.entityId}">
                                    ${sessionScope.user.login}
                            </a>
                        </li>
                    </c:when>
                    <c:when test="${sessionScope.user.userRole eq UserRole.ADMIN}">
                        <li class="nav-item">
                            <a class="nav-link" id="admin"
                               href="${pageContext.request.contextPath}/controller?command=go_to_admin_page">
                                    ${sessionScope.user.login}
                            </a>
                        </li>
                    </c:when>
                </c:choose>
                <c:if test="${sessionScope.user != null}">
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
    <script src="${pageContext.request.contextPath}/script/header.js"></script>
</header>