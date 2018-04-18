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
    <%@include file="userMenu.jsp" %>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>

<form:form method="POST" modelAttribute="userForm" class="block3">
    <span>${massage}<br></span>
    <spring:bind path="id">
        <form:input type="hidden" path="id" value="${user.id}"></form:input>
    </spring:bind>
    <spring:bind path="username">
        Ваш новый логин
        <form:input type="text" path="username"
                    autofocus="true"></form:input>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:errors path="username"></form:errors>
        </div>
    </spring:bind>

    <spring:bind path="passwordConfirm">
        Старый пароль
        <form:input type="text" path="passwordConfirm"></form:input>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:errors path="username"></form:errors>
            <form:errors path="passwordConfirm"></form:errors>
        </div>
    </spring:bind>

    <spring:bind path="password">
        Новый пароль
        <form:input type="text" path="password"></form:input>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:errors path="password"></form:errors>
        </div>
    </spring:bind>
    <spring:bind path="email">
        Почта
        <form:input type="email" path="email"></form:input>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:errors path="email"></form:errors>
        </div>
    </spring:bind>
    <spring:bind path="number">
        Номер телефона
        <form:input type="number" path="number"></form:input>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:errors path="number"></form:errors>
        </div>
    </spring:bind>
    <spring:bind path="numberCard">
        Номер карты
        <form:input type="number" path="numberCard"></form:input>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:errors path="numberCard"></form:errors>
        </div>
    </spring:bind>
    <spring:bind path="address">
        Адрес
        <form:input type="text" path="address"></form:input>
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:errors path="address"></form:errors>
        </div>
    </spring:bind>
    <c:if test="${sessionScope.get('user').role.name=='admin'}">
        <spring:bind path="role">
            Новый пароль
            <select size="1" name="role">
                <option disabled>Изменить роль</option>
                <c:forEach var="r" items="${allRole}">
                    <option value="${r.id}">${r.name}</option>
                </c:forEach>
            </select>
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:errors path="role"></form:errors>
            </div>
        </spring:bind>
    </c:if>
    <button class="ui-button" type="submit">Сохранить данные</button>

</form:form>

</body>
</html>
