<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 09.04.2018
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список заказов</title>
    <%@include file="header.jsp" %>
    <%@include file="adminMenu.jsp" %>

</head>
<body>

<c:set var="preIdOrder" value="${orders[0].idOrder-1}"> </c:set>
<c:forEach var="order" items="${orders}">
<c:set var="curOrder" value="${order.idOrder}"></c:set>
<c:if test="${curOrder!=preIdOrder}">
</div>
<div class="block2">
    <table>
        <tr>
            <td>
                <h4 align="center"> Заказ №${order.idOrder} Сумма ${order.price}
                    Статус ${order.status.status}  </h4>
                <h6 align="center">От ${order.idBayer.username}(${order.idBayer.id}) ${order.date}</h6>
            </td>
            <td>
                <form style="float: right" action="${contextPath}/updateStatusOrder">
                    <p></p>
                    <input type="hidden" name="id" value="${order.idOrder}">
                   <select size="1" name="status">
                        <option disabled>Изменить статус</option>
                        <c:forEach var="status" items="${listStatus}">
                        <option value="${status.id}">${status.status}</option>
                        </c:forEach>
                    </select>
                    <p><input type="submit" value="Изменить статус"></p>
                </form>
            </td>
        </tr>
    </table>
    </c:if>

    <h4>${order.idItem.company} ${order.idItem.model}(${order.idItem.price})
        в количестве ${order.amount}</h4>

        <c:set var="preIdOrder" value="${order.idOrder}"></c:set>
    </c:forEach>
<div>
    <c:if test="${page>1}">
        <a class="ui-button" href="${contextPath}/adminOrders?page=${page-1}">Предыдущая </a>
    </c:if>
    <c:if test="${(amountAllItem/itemPage)>page}">
        <a class="ui-button" href="${contextPath}/adminOrders?page=${page+1}"> Следущая</a>
    </c:if>
</div>
</body>
</html>
