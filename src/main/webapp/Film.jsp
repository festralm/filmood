<%@ page import="dto.*" %>
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
<%--<%--%>
<%--    Object button = request.getSession().getAttribute("button");--%>
<%--%>--%>

<%--<jsp:include page="includes/menu.jsp">--%>
<%--    <jsp:param name="button" value="<%=button%>"/>--%>
<%--</jsp:include>--%>

<%
    Film film = (Film) session.getAttribute("film");
%>
<div class="film" id="film">
    <h1><%
        if (film == null) {
            out.print("Название фильма");
        } else {
            out.print(film.getName());
        }%></h1>

    <div class="photo" id="photo"></div>
    <div class="info" id="info">
        <p>Год, страна : <%
            if (film == null) {
                out.print(". . .");
            } else {
                StringBuilder countries = new StringBuilder();
                Country[] countries1 = film.getCountries();
                if (countries1 != null) {
                    for (int i = 0; i < countries1.length; i++) {
                        countries.append(", ").append(countries1[i].getCountry());
                    }
                } else {
                    countries.append(", . . .");
                }
                out.print(film.getYear() + countries.toString());
            }
        %></p>
        <p>Жанр : <%
            if (film == null) {
                out.print(". . .");
            }  else {
                StringBuilder genres = new StringBuilder();
                Genre[] genres1 = film.getGenres();
                if (genres1 != null) {
                    for (int i = 0; i < genres1.length; i++) {
                        if (i == 0) {
                            genres.append(genres1[i].getName());

                        } else {
                            genres.append(", ").append(genres1[i].getName());
                        }
                    }
                } else {
                    genres.append(", . . .");
                }
                out.print(genres.toString());
            }
        %></p>
        <p>Краткое описание : <%
            if (film == null) {
                out.print("Краткое описание");
            } else {
                out.print(film.getDescription());
            }%></p>

        <input type="button" value="Сохранить на будущее" onclick="window.location.href = '/fm/save-to-wanted';">
    </div>
</div>

<div class="other" id="other">
    <form>
        <p><b>Оставить комментарий к фильму :</b></p>
        <p><textarea name="comment" id="comment"></textarea></p>
        <p><input type="submit"></p>
    </form>


    <a href="http://localhost:8080/fm/comments" id="allComments">Посмотреть другие комментарии</a>
</div>

<div class="user_comment" id="user_comment">
    <div class="ph" id="ph">
    </div>

    <div class="cm" id="cm">
        <a id="user_name" href="Profile.html">Имя пользователя</a>
        <p>*комментарий комментарий комментарий комментарий комментарий*</p>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>