<%--
Created by IntelliJ IDEA.
User: katty
Date: 29.10.2020
Time: 16:49
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Профиль пользователя</title>

    <link rel="stylesheet" type="text/css" href="styles/Profile.css">
</head>
<body>
<%
    Object button = request.getSession().getAttribute("button");
%>

<jsp:include page="includes/menu.jsp">
    <jsp:param name="button" value="<%=button%>"/>
</jsp:include>

<div class="anketa" id="anketa">
    <div class="photo" id="photo">
        <img src="https://sun1-24.userapi.com/Se9Kf-XIcLjI0hkHL7zIYMYV1fJtWk3omc6O4A/q2u4_QgJaG4.jpg" alt="Avatar" class="avatar">
    </div>

    <div class="info" id="info">
        <h1>МИХАИЛ АБРАМСКИЙ</h1>
        <h2>@abramichael</h2>

        <div class="categories" id="category">
            <h3>Любимые жанры:</h3>
            <h4>Жанр 1</h4>
            <h4>Жанр 2</h4>
            <h4>Жанр 3</h4>
        </div>
        <h3></h3>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>