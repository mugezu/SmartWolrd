<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 14.04.2018
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заказы ${pageContext.request.userPrincipal.name}</title>
    <%@include file="header.jsp" %>
    <%@include file="userMenu.jsp" %>
</head>
<body>
<h3 align="center"> Список ваших заказов</h3>
<c:if test="${empty userOrders}">
    <h3> К сожелению, вы еще не делали покупок у нас на сайте</h3>
</c:if>
<c:set var="preIdOrder" value="${userOrders[0].idOrder-1}"> </c:set>
<c:forEach var="order" items="${userOrders}">
<c:set var="curOrder" value="${order.idOrder}"></c:set>
<c:if test="${curOrder!=preIdOrder}">
</div>
<div class="block2">
    <h3 align="center"> Заказ №${order.idOrder} Сумма ${order.price}
        Статус ${order.status.status}  </h3>
    <h5 align="center">(${order.date})</h5>
    </c:if>

    <h4>${order.idItem.company} ${order.idItem.model}(${order.idItem.price})
        в количестве ${order.amount}</h4>

        <c:set var="preIdOrder" value="${order.idOrder}"></c:set>
    </c:forEach>
</body>
</html>
