<%--
  Created by IntelliJ IDEA.
  User: katty
  Date: 29.10.2020
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="menu" role="menu">
    <div class="page_name" id="page_name">
        <a href="http://localhost:8080/fm">FILM <span class="colortext">&</span> MOOD</a>
    </div>

    <nav>
        <a href="http://localhost:8080/fm/my-films" id="myFilms">Моё кино</a> ||
        <a href="http://localhost:8080/fm/favorites" id="favorites">Избранное</a> ||
        <a href="http://localhost:8080/fm/will-watch" id="selectedFilms">Буду смотреть</a> ||
        <a href="http://localhost:8080/fm/all-films" id="categories">Все фильмы</a> ||
        <a href="http://localhost:8080/fm/profile?id=0" id="account">Мой профиль</a> ||
        <c:if test="${button.toString().equals(\"Войти\")}">
            <a href="http://localhost:8080/fm/authorize" id="signIn">Войти</a>
        </c:if>
        <c:if test="${button.toString().equals(\"Выйти\")}">
            <a href="http://localhost:8080/fm/logout" id="signIn">Выйти</a>
        </c:if>
    </nav>
</div>