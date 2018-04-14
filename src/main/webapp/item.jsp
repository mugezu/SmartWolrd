<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 19.02.2018
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${item.company} ${item.model}</title>
    <%@include file="header.jsp" %>
</head>
<body>
<table>
    <tr>
        <th>
            <img src="resources/picture/smartfone/${item.URL}" height="420" width="360">
        </th>
        <th>
            <h1 align="center"> Цена ${item.price}</h1>
            <h3 align="center">Характеристики</h3>
            <div style="float:left">
                <h4> Компания производитель ${item.company} </h4>
                <h4> Модель ${item.model} </h4>
                <h4> Дисплей ${item.displayType} ${item.screenResolution} (${item.displaySize} дюймов) </h4>
                <h4> Процессор ${item.processor} (${item.core} ядер) </h4>
                <h4>Камера ${item.camera} </h4>
                <h4>Операционная система ${item.OS} </h4>
                <h4> Количество внутренней памяти ${item.HDD} </h4>
                <h4>Количество оперативной памяти ${item.RAM} </h4>
            </div>
        </th>
        <th>
            <a class="btn btn-default btn-danger" href="${contextPath}/buyItem?id=${item.id}"> Добавить в корзину</a>
            <br>
            <span> ${message}</span>
            <c:if test="${sessionScope.get('user').role.name=='admin'}">
                <a class="btn btn-default btn-danger" href="${contextPath}/addItem?id=${item.id}"> Изменить товар</a>
                <form action="${contextPath}/deleteItemDB" method="post">
                    <input type="hidden" name="id" value="${item.id}">
                    <button type="submit" class="btn btn-default btn-danger">Удалить товар</button>
                </form>

            </c:if>
        </th>
    </tr>
</table>

</body>
</html>
