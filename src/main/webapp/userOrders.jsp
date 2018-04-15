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
<c:if test="${empty userOrders || fn:length(userOrders)==0}">
    <h3> К сожелению, вы еще не делали покупок у нас на сайте</h3>
</c:if>
<c:if test="${userOrders!=null && fn:length(userOrders)!=0}">
    <h3 align="center"> Список ваших заказов</h3>
    <c:forEach var="order" items="${userOrders}">

        <div class="block2">
            <h4 align="center"> Заказ №${order.id} Сумма ${order.price}
                Статус ${order.status.status}  </h4>
            <h6 align="center">(${order.date})</h6>

            <c:forEach var="subOrder" items="${order.subOrders}">

                <h5>${subOrder.idItem.company} ${subOrder.idItem.model}(${subOrder.idItem.price})
                    в количестве ${subOrder.amount}</h5>
            </c:forEach>
        </div>
    </c:forEach>
</c:if>
<div class="block3">
    <div align="center">
        <c:if test="${page>1}">
            <a class="ui-button" href="${contextPath}/userOrders?page=${page-1}">Предыдущая </a>
        </c:if>
        <c:if test="${(amountAllItem/itemPage)>page}">
            <a class="ui-button" href="${contextPath}/userOrders?page=${page+1}"> Следущая</a>
        </c:if>
    </div>
</div>
</body>
</html>
