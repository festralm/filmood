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
    <title>Ассоциации</title>

    <link rel="stylesheet" type="text/css" href="styles/Associations.css">
</head>
<body>
<%
    Object button = request.getSession().getAttribute("button");
%>

<jsp:include page="includes/menu.jsp">
    <jsp:param name="button" value="<%=button%>"/>
</jsp:include>

<div class="area1" id="area1">
    <h1>СЛОВА-АССОЦИАЦИИ</h1>
    <h3>Выберите подходящие тэги, чтобы найти тот самый фильм :) </h3>

    <form>
        <p>
            <input type="search" name="search" id="search" placeholder="   #любовь,   #мелодрама,   #танцы">
        </p>

        <div class="tags" id="tahgs">
            <h5>Популярные запросы :</h5>

            <h6>#любовь</h6>
            <h6>#свобода</h6>
            <h6>#комедия</h6>
            <h6>#семья</h6>
        </div>

        <p>
            <input type="submit" value="Найти" id="submitButton">
        </p>
    </form>
</div>

<div class="area2" id="area2">
    <div class="new" id="new">
        <div class="film" id="film">
            <div class="ph" id="ph1"></div>
            <!--        <h3> . . . </h3>-->
        </div>

        <div class="info" id="info">
            <a href="Film.jsp">Название фильма</a>

            <p>Год, страна : . . .</p>
            <p>Жанр : . . .</p>

            <input type="button" value="Сохранить на будущее">
        </div>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>