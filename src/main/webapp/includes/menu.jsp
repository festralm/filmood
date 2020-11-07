<%--
  Created by IntelliJ IDEA.
  User: katty
  Date: 29.10.2020
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="menu" role="menu">
    <div class="page_name" id="page_name">
        <a href="http://localhost:8080/fm">FILM <span class="colortext">&</span> MOOD</a>
    </div>

    <nav>
        <a href="http://localhost:8080/fm/my-films" id="myFilms">Моё кино</a> ||
        <a href="http://localhost:8080/fm/favorites" id="favorites">Избранное</a> ||
        <a href="http://localhost:8080/fm/will-watch" id="selectedFilms">Буду смотреть</a> ||
        <a href="http://localhost:8080/fm/categories" id="categories">Все фильмы</a> ||
        <a href="http://localhost:8080/fm/categories" id="subscription">Подписки</a> ||
        <a href="http://localhost:8080/fm/profile?id=0" id="account">Мой профиль</a> ||
        <%
            String buttonText = (String) session.getAttribute("button");

            if (buttonText.equals("Войти")) {
                out.print("<a href=\"http://localhost:8080/fm/authorize\" id=\"signIn\">" + buttonText + "</a>");
            } else {
                out.print("<a href=\"http://localhost:8080/fm/logout\" id=\"signIn\">" + buttonText + "</a>");
            }
        %>

    </nav>
</div>