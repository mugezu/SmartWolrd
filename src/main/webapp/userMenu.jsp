<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 14.04.2018
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Меню пользователя</title>
</head>
<body>
<div class="menu">
    <a href="${contextPath}/userOrders" class="btn btn-lg btn-info buttonMenu"> Посмотреть список ваших заказов</a>
    <a href="" class="btn btn-lg btn-info buttonMenu"> Изменить данные о себе</a>
    <a href="${contextPath}/catalog" class="btn btn-lg btn-info buttonMenu"> Каталог</a>
    <a href="${contextPath}/basket" class="btn btn-lg btn-info buttonMenu"> Корзина</a>
</div>
</body>
</html>