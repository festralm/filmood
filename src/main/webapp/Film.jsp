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

<%
    Film film = (Film) session.getAttribute("film");
    String filmName = "";
    if (film != null) {
        filmName = film.getName();
        if (filmName == null || filmName.equals("")) {
            filmName = "Неизвестно";
        }
    }
%>
<head>
    <meta charset="UTF-8">
    <title><%=filmName%>></title>

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
    <h1><%=filmName%></h1>

    <div class="photo" id="photo">
        <%
            String outPath = "<img class=\"photo\" src=\"";
            if (film != null) {
                String photoUrl = film.getPhotoPath();
                if (photoUrl != null) {
                    outPath += photoUrl;
                }
                outPath += "\">";
            }
            out.print(outPath);
        %>
    </div>
    <div class="info" id="info">
        <p>Год, страна :
            <%
                String outYear = "";
                Country[] countries = new Country[] {};

                if (film != null) {
                    int startYear = film.getStartYear();
                    int finishYear = film.getFinishYear();

                    if (startYear != 0) {
                        outYear += finishYear == 0 ? startYear : finishYear == -1 ?
                                startYear + "настоящее время" : startYear + " - " + finishYear;
                    }

                    countries = film.getCountries();
                }

                if (!outYear.equals("")) {
                    outPath.print(outYear);
                } else {
                    out.print(" Неизвестный год");
                }

                if (countries != null && countries.length != 0) {
                    StringBuilder outCountries = new StringBuilder();
                    for (int i = 0; i < countries.length; i++) {
                        outCountries.append(", ").append(countries[i].getCountry());
                    }
                    out.print(outCountries);
                } else {
                    out.print(", Неизвестные страны");
                }
            %>
        </p>
        <p>Жанр :
            <%
                Genre[] genres = new Genre[] {};

                if (film != null) {
                    genres = film.getGenres();
                }

                if (genres != null && genres.length != 0) {
                    StringBuilder outGenres = new StringBuilder();
                    for (int i = 0; i < genres.length; i++) {
                        outGenres.append(", ").append(genres[i].getName());
                    }
                    out.print(genres);
                } else {
                    out.print(" Неизвестно");
                }
            %>
        </p>
        <p>Краткое описание :
            <%
                String outDescription = "";
                if (film != null) {
                    outDescription = film.getDescription();
                }
                if (outDescription == null || outDescription.equals("")) {
                    outDescription = "Неизвестно";
                }
                out.print(outDescription);
            %>
        </p>

        <input type="button" value="Сохранить на будущее" onclick="window.location.href = '/fm/save-to-wanted';">
        <input class="button4" type="button" id="b4" onclick="window.location.href = '#popup2';" value="Я смотрел!"/>
    </div>
</div>

<div class="other" id="other">
    <form method="post" action="comment">
        <p><b>Оставить комментарий к фильму :</b></p>
        <p><textarea name="comment" id="comment" required></textarea></p>
        <p><input type="submit"></p>
    </form>
</div>

<div class="user_comment" id="user_comment">
    <div class="ph" id="ph">
        <img src="">
    </div>

    <div class="cm" id="cm">
        <a id="user_name" href="http://localhost:8080/fm/profile">Имя пользователя</a>
        <p>*комментарий комментарий комментарий комментарий комментарий*</p>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>