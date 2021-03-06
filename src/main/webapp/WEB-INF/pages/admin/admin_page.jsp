<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.radyuk.foodblog.entity.UserStatus" %>
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
    <link href="style/admin_page.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <title><fmt:message key="page.name.admin" bundle="${rb}"/></title>
</head>

<jsp:include page="../template/header.jsp"/>

<body>
<div class="body">
    <c:if test="${requestScope.unapproved_users.size() != 0}">
        <h1 class="page-title"><fmt:message key="admin.awaiting_confirmation" bundle="${rb}"/></h1>
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="admin.table_column_name.login" bundle="${rb}"/></th>
                <th scope="col"><fmt:message key="admin.table_column_name.email" bundle="${rb}"/></th>
                <th scope="col"><fmt:message key="admin.table_column_name.role" bundle="${rb}"/></th>
                <th scope="col"><fmt:message key="admin.table_column_name.action" bundle="${rb}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.unapproved_users}" var="user">
                <tr>
                    <th scope="row">${user.login}</th>
                    <td>${user.email}</td>
                    <td>${user.userRole}</td>
                    <td>
                        <a class="action"
                           href="${pageContext.request.contextPath}/controller?command=change_user_status&user_id=${user.entityId}&user_status=${user.userStatus}">
                            <strong><fmt:message key="admin.confirm" bundle="${rb}"/></strong>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    <h1 class="page-title"><fmt:message key="admin.users" bundle="${rb}"/></h1>

    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="admin.table_column_name.login" bundle="${rb}"/></th>
            <th scope="col"><fmt:message key="admin.table_column_name.email" bundle="${rb}"/></th>
            <th scope="col"><fmt:message key="admin.table_column_name.role" bundle="${rb}"/></th>
            <th scope="col"><fmt:message key="admin.table_column_name.action" bundle="${rb}"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.approved_users}" var="user">
            <tr>
                <th scope="row">
                    <a class="action"
                       href="${pageContext.request.contextPath}/controller?command=go_to_profile_page&user_id=${user.entityId}">
                            ${user.login}
                    </a>
                </th>
                <td>${user.email}</td>
                <td>${user.userRole}</td>
                <td>
                    <a class="action"
                       href="${pageContext.request.contextPath}/controller?command=change_user_status&user_id=${user.entityId}&user_status=${user.userStatus}">
                        <strong>
                            <c:choose>
                                <c:when test="${user.userStatus eq UserStatus.ACTIVE}">
                                    <fmt:message key="admin.action.block" bundle="${rb}"/>
                                </c:when>
                                <c:when test="${user.userStatus eq UserStatus.BLOCKED}">
                                    <fmt:message key="admin.action.unblock" bundle="${rb}"/>
                                </c:when>
                            </c:choose>
                        </strong>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>

<jsp:include page="../template/footer.jsp"/>

</html>