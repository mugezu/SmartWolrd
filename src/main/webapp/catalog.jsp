<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 19.02.2018
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Каталог товаров</title>
    <script src="${contextPath}/resources/js/jquery-1.12.4.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.js"></script>


    <%@include file="header.jsp" %>

</head>
<body>


<c:set var="amountItem" value="${0}"/>
<table class="table1">

    <c:if test="${fn:length(listCatalog)!=0}">
        <c:forEach var="catalog" items="${listCatalog}">
            <tr>
                <th>
                    <img src="resources/picture/smartfone/${catalog.URL}" width="235" height="300">
                </th>
                <th>

                    <b2>${catalog.company} ${catalog.model}</b2>
                    <br/>
                    <b2><h2>${catalog.price} грн</h2></b2>
                    <br/>
                    <b2><a class="btn btn-default btn-danger" href="${contextPath}/item?id=${catalog.id}">Подробнее</a>
                        <a class="btn btn-default btn-danger" href="${contextPath}/buyItem?id=${catalog.id}"> Добавить в
                            корзину</a>

                    </b2>
                    <br/>
                    <c:if test="${sessionScope.get('user').role.name=='admin'}">
                        <a class="btn btn-default btn-danger" href="${contextPath}/addItem?id=${catalog.id}"> Изменить
                            товар</a>

                        <form action="${contextPath}/admin/deleteItemDB" method="post">
                            <input type="hidden" name="id" value="${catalog.id}">
                            <button type="submit" class="btn btn-default btn-danger">Удалить товар</button>
                        </form>
                    </c:if>
                </th>
            </tr>
        </c:forEach>
        <tr>
            <th></th>
            <th>
                <c:if test="${page>1}">
                    <a class="ui-button" href="${contextPath}/catalog?page=${page-1}">Предыдущая </a>
                </c:if>
                <c:if test="${(amountAllItem/itemPage)>page}">
                    <a class="ui-button" href="${contextPath}/catalog?page=${page+1}"> Следущая</a>
                </c:if>
            </th>
        </tr>
    </c:if>
    <c:if test="${fn:length(listCatalog)==0}">
        <tr>
            <th> Очень жаль, но нет товаров по данному запросу</th>
        </tr>
    </c:if>
</table>

<div class="buttonMenu">
    <c:set var="filter" value="${sessionScope.get('filters')}"/>
    <c:if test="${filter!=null}">
        Выбранные фильтры: ${filter.company}  ${filter.displaySize}  ${filter.displayType}  ${filter.HDD}
        ${filter.OS} ${filter.RAM} ${filter.priceMin} - ${filter.priceMax}
    </c:if>
    <form:form method="get" modelAttribute="filters" action="${contextPath}/filters">
        <h2>Фильтры </h2>
        <span>${message}</span>

        <script>
            $(function () {
                $("#slider-range").slider({
                    range: true,
                    min: ${minPrice},
                    max:  ${maxPrice},
                    values: [${minPrice}, ${maxPrice}],
                    slide: function (event, ui) {
                        $("#price1").val(ui.values[0]);
                        $("#price2").val(ui.values[1]);
                        $("#price").val(ui.values[0] + " - " + ui.values[1]);
                    }

                });
                $("#price").val(' ' + $("#slider-range").slider("values", 0) + ' - ' + $("#slider-range").slider("values", 1));
                $("#price1").val($("#slider-range").slider("values", 0));
                $("#price2").val($("#slider-range").slider("values", 1));
            });
        </script>

        <p>
        <h3>Цена</h3>
        <label>Разброс ценны:</label>
        <input id="price" readonly="true">
        <form:input path="priceMin" type="hidden" id="price1"></form:input>
        <form:input path="priceMax" type="hidden" id="price2"></form:input>

        </p>

        <div id="slider-range" class="TABLE2">
        </div>

        <h3>Компания</h3>
        <c:forEach var="item" items="${company}">
            <label>
                <form:radiobutton name="company" value="${item}" path="company"/> ${item}
            </label>
        </c:forEach>
        <h3>Размер дисплей</h3>
        <c:forEach var="item" items="${displaySize}">
            <label>
                <form:radiobutton path="displaySize" name="displaySize" value="${item}"/> ${item}
            </label>
        </c:forEach>
        <h3>Тип дисплея</h3>
        <c:forEach var="item" items="${displayType}">
            <label>
                <form:radiobutton path="displayType" name="displayType" value="${item}"/> ${item}
            </label>
        </c:forEach>
        <h3>Внетрення память</h3>
        <c:forEach var="item" items="${HDD}">
            <label>
                <form:radiobutton path="HDD" name="HDD" value="${item}"/> ${item}
            </label>
        </c:forEach>
        <h3>Операционная система</h3>
        <c:forEach var="item" items="${OS}">
            <label>
                <form:radiobutton path="OS" name="OS" value="${item}"/> ${item}
            </label>
        </c:forEach>
        <h4>Оперативная память</h4>
        <c:forEach var="item" items="${RAM}">
            <label>
                <form:radiobutton path="RAM" name="RAM" value="${item}"/> ${item}
            </label>
        </c:forEach>
        <br>
        <button style="float: left" class="ui-button" type="submit">Отфильтровать</button>
        <a href="${contextPath}/filtersClean" class="ui-button">Очистить фильтр</a>
    </form:form>

</div>


</body>
</html>
