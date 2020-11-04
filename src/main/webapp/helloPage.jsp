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
    <title>FILMood</title>

    <script src="js/popup.js"></script>
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

        <input class="button3" type="button"
               onclick="window.location.href = '/fm/associations';" value="Выбрать слова"/>
    </div>

    <div class="lottery" id="lottery">
        <h3>Кино-лотерея</h3>
        <p>на экране появляется набор карточек, пользователь выбирает одну из них, и ему выпадает случайный фильм</p>

        <input class="button4" id="button4"
               onclick="get_film()" type="button" value="Довериться судьбе"/>
    </div>

    <div class="recommendation" id="recommendation">
        <h3>Рекомендации недели</h3>
        <p>ознакомьтесь с лучшими фильмами этой недели</p>
        <input class="button5" type="button"
               onclick="window.location.href = '/fm/recommendations';"value="Просмотреть рекомендации"/>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
<jsp:include page="film_popup.jsp"/>
<jsp:include page="describe_popup.jsp"/>
</body>
</html>