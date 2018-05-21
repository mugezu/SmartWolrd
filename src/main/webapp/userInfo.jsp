<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 16.04.2018
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Информация о ${pageContext.request.userPrincipal.name} </title>
    <%@include file="header.jsp" %>
    <!--TODO Условине подключения меню-->
    <c:if test=""></c:if>
    <%@include file="userMenu.jsp" %>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>

<form:form method="POST" modelAttribute="userForm" class="">
    <span>${massage}<br></span>
    <spring:bind path="id">
        <form:input type="hidden" path="id" value="${userForm.id}"></form:input>
    </spring:bind>
    <spring:bind path="username">
        <div>
            <form:input type="text" path="username"
                        autofocus="true"></form:input>
            <span> Ваш новый логин </span>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:errors path="username"></form:errors>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="passwordConfirm">
        <div>
            <form:input type="text" path="passwordConfirm"></form:input>
            <span>  Старый пароль  </span>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:errors path="passwordConfirm"></form:errors>
            </div>
        </div>
    </spring:bind>

    <spring:bind path="password">
        <div>
            <form:input type="text"  path="password"></form:input>
            <span>   Новый пароль </span>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:errors path="password"></form:errors>
            </div>
        </div>
    </spring:bind>
    <spring:bind path="email">
        <div><form:input type="email" path="email"></form:input>
            <span>  Почта </span>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:errors path="email"></form:errors>
            </div>
        </div>
    </spring:bind>
    <spring:bind path="number">
        <div>
            <form:input type="number" path="number"></form:input>
            <span>   Номер телефона  </span>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:errors path="number"></form:errors>
            </div>
        </div>
    </spring:bind>
    <spring:bind path="numberCard">
        <div>
            <form:input type="number" path="numberCard"></form:input>
            <span>  Номер карты  </span>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:errors path="numberCard"></form:errors>
            </div>
        </div>
    </spring:bind>
    <spring:bind path="address">
        <div>
            <form:input type="text" path="address"></form:input>
            <span>    Адрес  </span>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:errors path="address"></form:errors>
            </div>
        </div>
    </spring:bind>
    <sec:authorize access="hasRole('admin')">
        <spring:bind path="role">
            <div><select size="1" name="role">
                <option disabled>Изменить роль</option>
                <c:forEach var="r" items="${allRole}">
                    <option  value="${r.id}">${r.name}</option>
                </c:forEach>
            </select>
                <span>   Изменить роль  </span>
            </div>
        </spring:bind>
    </sec:authorize>
    <button class="ui-button" type="submit">Сохранить данные</button>

</form:form>

</body>
</html>
