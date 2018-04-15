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
        <a class="ui-button" href="${contextPath}/admin/adminOrders?page=${page-1}">Предыдущая </a>
    </c:if>
    <c:if test="${(amountAllItem/itemPage)>page}">
        <a class="ui-button" href="${contextPath}/admin/adminOrders?page=${page+1}"> Следущая</a>
    </c:if>
    </div>
</div>
</body>
</html>
