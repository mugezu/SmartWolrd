<%--
  Created by IntelliJ IDEA.
  User: Роман
  Date: 28.03.2018
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ошибка</title>
    <%@include file="header.jsp" %>
</head>
<body>
<H2 align="center">Ой, что-то пошло не так:
<br>
    <c:if test="${exception==null}">
        похоже, что данной страницы не существует :(
    </c:if>


</h2>
</body>
</html>
