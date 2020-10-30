<%--
  Created by IntelliJ IDEA.
  User: katty
  Date: 29.10.2020
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Просмотренные фильмы</title>

    <link rel="stylesheet" type="text/css" href="styles/MyMovies.css">
</head>
<body>
<div class="menu" role="menu">
    <div class="page_name" id="page_name">
        <a href="http://localhost:8080/fm">FILM <span class="colortext">&</span> MOOD</a>
    </div>

    <nav>
        <jsp:include page="includes/menu.jsp"/>
    </nav>
</div>

<div class="rec" id="rec">
    <h2>Список просмотренных фильмов</h2>

    <div class="f1" id="f1">
        <div class="ph" id="ph1"></div>
        <a href="Film.jsp">. . . </a>
    </div>

    <div class="f2" id="f2">
        <div class="ph" id="ph2"></div>
        <a href="Film.jsp">. . . </a>
    </div>

    <div class="f3" id="f3">
        <div class="ph" id="ph3"></div>
        <a href="Film.jsp">. . . </a>
    </div>

    <div class="f4" id="f4">
        <div class="ph" id="ph4"></div>
        <a href="Film.jsp">. . . </a>
    </div>

    <div class="f5" id="f5">
        <div class="ph" id="ph5"></div>
        <a href="Film.jsp">. . . </a>
    </div>

    <div class="f6" id="f6">
        <div class="ph" id="ph6"></div>
        <a href="Film.jsp">. . . </a>
    </div>

    <div class="f7" id="f7">
        <div class="ph" id="ph7"></div>
        <a href="Film.jsp">. . . </a>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>