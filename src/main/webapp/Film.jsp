<%@ page import="dto.*" %>
<%--
Created by IntelliJ IDEA.
User: katty
Date: 29.10.2020
Time: 16:49
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>${film.getName()}</title>

    <link rel="stylesheet" type="text/css" href="styles/Film.css">
    <script src="js/popup.js"></script>
    <script src="js/validation.js"></script>
</head>
<body>
<jsp:include page="includes/menu.jsp"/>

<div class="film" id="film">
    <h1>${film.getName()}</h1>

    <div class="photo" id="photo">
        <img class="photo_film" src="${film.getPhotoPath()}">
    </div>
    <div class="info" id="info">
        <p>Год, страна : ${film.getStartYear()}<c:if test="${film.getFinishYear() != -1}"> - ${film.getFinishYear()}</c:if><c:forEach var="country" items="${film.getCountries()}"><c:out value=", ${country}"/></c:forEach></p>
        <p>Жанр :
            <c:forEach var="genre" varStatus="count" items="${film.getGenres()}">
                <c:if test="${count.count == 1}">${genre}</c:if>
                <c:if test="${count.count != 1}">, ${genre}</c:if>
            </c:forEach></p>
        <p>Краткое описание : ${film.getDescription()}</p>

        <input type="button" value="Сохранить на будущее" onclick="window.location.href = '/fm/save-to-will-watch?id=${film.getId()}';">
        <input class="button4" type="button" id="b4" onclick="describe()" value="Я смотрел!"/>
    </div>
</div>

<div class="other" id="other">
    <form method="post" action="comment?id=${film.getId()}">
        <p><b>Оставить комментарий к фильму :</b></p>
        <p><textarea name="comment" id="comment" required></textarea></p>
        <p><input type="submit"></p>
    </form>
</div>
<c:forEach var="comment" items="${film.getComments()}">
    <div class="user_comment" id="user_comment">
        <div class="ph" >
            <img class="ph_user" src="<c:out value="${comment.getPhotoPath()}"/>">
        </div>
        <div class="cm" id="cm">
            <a id="user_name" href="http://localhost:8080/fm/profile?id=${comment.getUserId()}">${comment.getUsername()}</a>
            <p>${comment.getDescription()}</p>
        </div>
    </div>
</c:forEach>

<jsp:include page="includes/footer.jsp"/>
<jsp:include page="includes/describe_popup.jsp"/>
</body>
</html>