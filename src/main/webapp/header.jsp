<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 19.02.2018
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/mainStyle.css" rel="stylesheet">
    <link rel="stylesheet" href="${contextPath}/resources/css/jquery-ui.css">

</head>
<body>
<table>
    <tr>
        <th>
            <form method="GET" style="float: left" action="${contextPath}/catalog" class="form-signin">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Каталог</button>
            </form>
        </th>
        <th>
            <a href="${contextPath}/main44">
                <img src="${contextPath}/resources/picture/kpssnhuwk7zzr5dr.png" width="720" height="121">
            </a>
        </th>
        <th>
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name != null}">
                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>

                    <h3><a href="${contextPath}/userPage">
                            ${pageContext.request.userPrincipal.name} </a><a style="float: right"
                                                                             onclick="document.forms['logoutForm'].submit()">Выйти</a>
                    </h3>

                    <a href="${contex1tPath}/basket" style="float: right" class="btn btn-lg btn-primary btn-block">
                        Ваша корзина
                    </a>

                    <sec:authorize access="hasRole('admin') or hasRole('manager')">
                        <a href="${contextPath}/admin/adminPage" style="float: right"
                           class="btn btn-lg btn-primary btn-block">
                            Админка
                        </a>
                    </sec:authorize>


                </c:when>
                <c:otherwise>
                    <form method="GET" style="float: right" action="${contextPath}/login" class="form-signin">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Вход</button>
                    </form>
                    <form method="GET" style="float: right" action="${contextPath}/registration" class="form-signin">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Реестрация</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </th>
    </tr>
</table>

</body>
</html>
