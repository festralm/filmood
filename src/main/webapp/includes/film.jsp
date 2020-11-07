<%@ page import="dto.*" %>
<%--
  Created by IntelliJ IDEA.
  User: katty
  Date: 31.10.2020
  Time: 5:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="film">
    <div class="ph">
        <a href="http://localhost:8080/fm/film?id=${film.getId()}" id="photo_href">
            <img class="ph" src="${film.getPhotoPath()}" id="photo">
        </a>
    </div>
</div>
<div class="info">
    <a id="name_href" href="http://localhost:8080/fm/film?id=${film.getId()}">${film.getName()}</a>


    <p id="year_country">Год, страна : ${film.getStartYear()}<c:if test="${film.getFinishYear() != -1}"> - ${film.getFinishYear()}</c:if><c:forEach var="country" items="${film.getCountries()}"><c:out value=", ${country}"/></c:forEach></p>
    <p id="genre">Жанр : <c:forEach var="genre" items="${film.getGenres()}"><c:out value=", ${genre}"/></c:forEach></p>

    <input type="button" value="Сохранить на будущее" onclick="window.location.href = 'http://localhost:8080/fm/save-to-will-watch?film_id=${film.getId()}'">
    <input class="button4" type="button" id="b4" onclick="describe()" value="Я смотрел!"/>
</div>
