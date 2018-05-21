<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 19.04.2018
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список пользователей</title>
    <%@include file="header.jsp" %>
    <%@include file="adminMenu.jsp" %>
</head>
<body>
<c:forEach var="user" items="${users}">
    <div class="block6">
        <table>
            <tr>
                <td>
                  Пользователь ${user.username}(${user.id})
                </td>
                <td>
                 <a href="${contextPath}/admin/userInfo?idUser=${user.id}">Изменить данные</a>
                </td>
            </tr>
        </table>
    </div>
</c:forEach>

<div class="block3">
    <div align="center">
        <c:if test="${page>1}">
            <form action="${contextPath}/admin/usersList">
                <input type="hidden" value="${page-1}" name="page"/>
                <input type="hidden" value="${idStatus}" name="idStatus">
                <button type="submit" class="ui-button">Предыдущая</button>
            </form>
        </c:if>
        <c:if test="${(amountAllItem/itemPage)>page}">
            <form action="${contextPath}/admin/usersList">
                <input type="hidden" value="${page+1}" name="page"/>
                <input type="hidden" value="${idStatus}" name="idStatus">
                <button type="submit" class="ui-button">Следующая</button>
            </form>
        </c:if>
    </div>
</div>
</body>
</html>
