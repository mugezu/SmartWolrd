<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 23.03.2018
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Корзина покупок</title>
    <%@include file="header.jsp" %>
    <%@include file="userMenu.jsp" %>

</head>
<body>
<c:if test="${fn:length(basketList)!=0}">
    <c:set var="allMoney" value="${0}"></c:set>
    <table class="table1">
        <tr>
            <th><h3> Название товара </h3></th>
            <th><h3 align="center">Количество </h3></th>
            <td hidden></td>
            <th><h3 align="right">Стоимость</h3></th>
        </tr>
        <c:forEach var="basket" items="${basketList}">
            <tr>
                <td>${basket.idItem.company} ${basket.idItem.model}(${basket.price})</td>
                <td>
                    <form action="${contextPath}/buyItem">
                        <input type="hidden" name="id" value="${basket.idItem.id}">
                        <input type="number" name="amount"
                               placeholder="${basket.amount}"/>
                        <button type="submit">Изменить количество</button>
                    </form>
                </td>
                <td>
                    <form action="${contextPath}/deleteItem">
                        <input type="hidden" name="id" value="${basket.idItem.id}">
                        <button type="submit">
                            <img src="${contextPath}/resources/picture/крестик.jpg" width="20" href="20">
                        </button>
                    </form>
                </td>
                <td> ${basket.amount*basket.price}
                    <c:set var="allMoney" value="${allMoney+basket.amount*basket.price}"></c:set>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td>
                Общая сумма заказа ${allMoney}
                <br>
                <form method="post" action="${contextPath}/buyBasket">
                    <input type="hidden" name="allMoney" value="${allMoney}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit">Оплатить</button>
                </form>
            </td>
        </tr>
    </table>
</c:if>
<c:if test="${fn:length(basketList)==0}">
    <h2> К сожалению, в корзине ничего нет :(</h2>
</c:if>


</body>
</html>
