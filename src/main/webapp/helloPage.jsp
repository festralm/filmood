<%@ page import="dto.*" %><%--
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
    <title>FILMood</title>
</head>

<link rel="stylesheet" type="text/css" href="styles/helloPage.css">

<body>

<div class="area1" id="area1">
    <%
        Object buttonText = request.getSession().getAttribute("button");
    %>

    <jsp:include page="includes/menu.jsp">
        <jsp:param name="button" value="<%=(String)buttonText%>"/>
    </jsp:include>

    <header role="banner">
        <h1>Не просто смотри кино,</h1>
        <h1>. . . чувствуй его . . .</h1>
    </header>
</div>
<div class="area2" id="area2">
    <div class="words" id="words">
        <h3>Слова-ассоциации</h3>
        <p>пользователь вводит слова или выбирает предложенные из списка и получает список фильмов по данным темам</p>

        <input class="button3" type="button" onclick="window.location.href = '/fm/associations';" value="Выбрать слова"/>
    </div>

    <div class="lottery" id="lottery">
        <h3>Кино-лотерея</h3>
        <p>на экране появляется набор карточек, пользователь выбирает одну из них, и ему выпадает случайный фильм</p>

        <input class="button4" type="button" onclick="window.location.href = '/fm/lottery';" value="Довериться судьбе"/>
    </div>

    <div class="recommendation" id="recommendation">
        <h3>Рекомендации недели</h3>
        <p>ознакомьтесь с лучшими фильмами этой недели</p>
        <input class="button5" type="button" onclick="window.location.href = '/fm/recommendations';"value="Просмотреть рекомендации"/>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
<div id="popup" class="popup">
    <div class="popup_body">
        <div class="popup_content">
            <a href="#" class="popup_close">Х</a>
            <div class="popup_title">Итааак ... сегодня смотрим фильм</div>
            <div class="popup_text">
                <div class="new" id="new">
                    <div class="film" id="film">
                        <div class="ph" id="ph1"></div>
                        <!--        <h3> . . . </h3>-->
                    </div>

                    <%
                        Object film = session.getAttribute("film");
                    %>

                    <jsp:include page="includes/film.jsp">
                        <jsp:param name="film" value="<%=film%>"/>
                    </jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>