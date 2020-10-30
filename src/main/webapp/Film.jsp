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
    <title>Фильм</title>

    <link rel="stylesheet" type="text/css" href="styles/Film.css">
</head>
<body>
<%
    Object button = request.getSession().getAttribute("button");
%>

<jsp:include page="includes/menu.jsp">
    <jsp:param name="button" value="<%=button%>"/>
</jsp:include>

<div class="film" id="film">
    <h1>Название фильма</h1>

    <div class="photo" id="photo"></div>

    <div class="info" id="info">
        <p>Год, страна : . . .</p>
        <p>Жанр : . . .</p>
        <p>Краткое описание : . . .</p>

        <input type="button" value="Сохранить на будущее">
    </div>
</div>

<div class="other" id="other">
    <form>
        <p><b>Оставить комментарий к фильму :</b></p>
        <p><textarea name="comment" id="comment"></textarea></p>
        <p><input type="submit"></p>
    </form>


    <a href="#" id="allComments">Посмотреть другие комментарии</a>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>