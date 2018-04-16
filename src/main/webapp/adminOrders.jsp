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
<br>

<div align="left" class="block4">
    <form method="get" action="${contextPath}/admin/adminOrders">
        Найти заказ по ID <input type="number" name="idOrder"/>
        <button type="submit">Найти</button>
    </form>
</div>
<div align="right" class="block5">
<form method="get" action="${contextPath}/admin/adminOrders">
        Выборка по статусу
        <select size="1" name="idStatus">
            <option disabled>Выбрать статус</option>
            <option value="${null}">Все</option>
            <c:forEach var="status" items="${listStatus}">
                <option value="${status.id}">${status.status}</option>
            </c:forEach>
        </select>
        <p><input type="submit" value="Изменить статус"></p>
    </form>
</div>


<c:forEach var="order" items="${orders}">
    <div class="block2">
        <table>
            <tr>
                <td>
                    <h4 align="center"> Заказ №${order.id} Сумма ${order.price}
                        Статус ${order.status.status}  </h4>
                    <h6 align="center">От ${order.idBayer.username}(${order.idBayer.id}) ${order.date}</h6>
                </td>
                <td>
                    <form style="float: right" action="${contextPath}/admin/updateStatusOrder">
                        <p></p>
                        <input type="hidden" name="id" value="${order.id}">
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
            <tr>
                <th>
                    <c:forEach var="subOrder" items="${order.subOrders}">

                        <h4>${subOrder.idItem.company} ${subOrder.idItem.model}(${subOrder.idItem.price})
                            в количестве ${subOrder.amount}</h4>
                    </c:forEach>
                </th>
            </tr>
        </table>
    </div>
</c:forEach>

<div class="block3">
    <div align="center">
        <c:if test="${page>1}">
            <form action="${contextPath}/admin/adminOrders">
                <input type="hidden" value="${page-1}" name="page"/>
                <input type="hidden" value="${idStatus}" name="idStatus">
                <button type="submit" class="ui-button">Предыдущая</button>
            </form>
        </c:if>
        <c:if test="${(amountAllItem/itemPage)>page}">
            <form action="${contextPath}/admin/adminOrders">
                <input type="hidden" value="${page+1}" name="page"/>
                <input type="hidden" value="${idStatus}" name="idStatus">
                <button type="submit" class="ui-button">Следующая</button>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>
