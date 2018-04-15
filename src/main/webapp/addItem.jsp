<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 08.04.2018
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить товар</title>
    <%@include file="header.jsp" %>
    <%@include file="adminMenu.jsp" %>
</head>
<body>

<form:form method="post" enctype="multipart/form-data" modelAttribute="catalog" action="${contextPath}/admin/addItem">
    <table class="table1">
        <tr>
            <td>

            </td>
            <td>
              <h3>
                  <c:if test="${item!=null}">
                      Редактирование товара!!!
                  </c:if>
              </h3>
            </td>
        </tr>
        <form:input type="hidden" path="id" value="${item.id}" />
        <tr>
            <td>
                Компания
            </td>
            <td>
                <form:input name="company" value="${item.company}" path="company"/>
            </td>
        </tr>

        <tr>
            <td>
                Название
            </td>
            <td>
                <form:input  value="${item.model}" name="model" path="model"/>
            </td>
        </tr>

        <tr>
            <td>
                Цена
            </td>
            <td>
                <form:input type="number"  value="${item.price}" name="price" path="price"/>
            </td>
        </tr>

        <tr>
            <td>
                Тип дисплея
            </td>
            <td>
                <form:input name="displayType"  value="${item.displayType}" path="displayType"/>

            </td>
        </tr>

        <tr>
            <td>
                Размер дисплея
            </td>
            <td>
                <form:input name="displaySize" value="${item.displaySize}" path="displaySize"/>
            </td>
        </tr>

        <tr>
            <td>
                Расширение экрана
            </td>
            <td>
                <form:input name="screenResolution" value="${item.screenResolution}" path="screenResolution"/>
            </td>
        </tr>

        <tr>
            <td>
                Процессор
            </td>
            <td>
                <form:input name="processor" value="${item.processor}" path="processor"/>
            </td>
        </tr>

        <tr>
            <td>
                Количество ядер
            </td>
            <td>
                <form:input type="number" value="${item.core}" name="core" path="core"/>
            </td>
        </tr>

        <tr>
            <td>
                Камера
            </td>
            <td>
                <form:input name="camera" value="${item.camera}" path="camera"/>
            </td>
        </tr>

        <tr>
            <td>
                HDD
            </td>
            <td>
                <form:input type="number" value="${item.HDD}" name="HDD" path="HDD"/>
            </td>
        </tr>

        <tr>
            <td>
                RAM
            </td>
            <td>
                <form:input type="number" value="${item.RAM}" name="RAM" path="RAM"/>
            </td>
        </tr>


        <tr>
            <td>
                OS
            </td>
            <td>
                <form:input name="OS" value="${item.OS}" path="OS"/>
            </td>
        </tr>

        <tr>
            <td>
                Количество товара
            </td>
            <td>
                <form:input type="number" name="amount" value="${item.amount}" path="amount"/>
            </td>
        </tr>
        <tr>
            <td>
                Выбрать картинку
            </td>
            <td>
                <form:input type="file" path="picture" name="picture"/>
            </td>
        </tr>
        <tr>
            <td>
                Задать имя картинке
            </td>
            <td>
                <form:input path="URL" value="${item.URL}" type="text" name="URL"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button type="submit">Добавить товар</button>
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
