<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 08.04.2018
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Меню администратора</title>
</head>
<body>
<div class="menu">
    <a href="${contextPath}/adminOrders" class="btn btn-lg btn-info buttonMenu"> Посмотреть список заказов</a>
    <a href="" class="btn btn-lg btn-info buttonMenu"> Посмотреть список пользователей</a>
    <a href="${contextPath}/catalog" class="btn btn-lg btn-info buttonMenu"> Посмотреть список товаров</a>
    <a href="${contextPath}/addItem" class="btn btn-lg btn-info buttonMenu"> Добавить товар</a>
</div>
</body>
</html>
