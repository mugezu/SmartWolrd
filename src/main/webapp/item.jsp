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

    <script src="http://code.jquery.com/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/js/smartWorldJS.js"></script>
</head>
<body onload="getReviews(${item.id}),init(${item.id})">
<img class="pictureItem" src="${contextPath}/image/${item.id}" width="235" height="300">

<div class="infoItem">
    <h3 align="center">Характеристики</h3>
    <h1 align="center"> Цена ${item.price}</h1>
    <h4> Компания производитель ${item.company} </h4>
    <h4> Модель ${item.model} </h4>
    <h4> Дисплей ${item.displayType} ${item.screenResolution} (${item.displaySize} дюймов) </h4>
    <h4> Процессор ${item.processor} (${item.core} ядер) </h4>
    <h4>Камера ${item.camera} </h4>
    <h4>Операционная система ${item.OS} </h4>
    <h4> Количество внутренней памяти ${item.HDD} </h4>
    <h4>Количество оперативной памяти ${item.RAM} </h4>
    <a class="btn btn-default btn-danger" href="${contextPath}/buyItem?id=${item.id}"> Добавить в
        корзину</a>
    <br>
    <span> ${message}</span>
    <sec:authorize access="hasRole('admin')">
        <a class="btn btn-default btn-danger" href="${contextPath}/admin/addItem?id=${item.id}"> Изменить
            товар</a>
        <form action="${contextPath}/deleteItemDB" method="post">
            <input type="hidden" name="id" value="${item.id}">
        </form>
    </sec:authorize>
</div>

 
<div class="comments">
    <h3 align="center">Коментарии</h3>

    <p id="result_text"></p>
    <p id="page"></p>
</div>
<div class="sendComment">
    <h3>Оставить отзыв</h3>
    <form id="review">
        <p>Понравилось <input name="liked" type="textarea"/></p>
        <p>Не понравилось <input name="notLike" type="textarea"/></p>
        <p>Отзыв <input name="comment" type="textarea"/></p>
        <input type="button" value="Оставить отзыв" onclick="sendComment()">
    </form>
</div>
</body>
</html>
