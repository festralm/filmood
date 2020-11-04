<%@ page import="dto.*" %>
<%--
  Created by IntelliJ IDEA.
  User: katty
  Date: 31.10.2020
  Time: 5:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object filmObject = session.getAttribute("film");
    Film film = null;
    String href = "http://localhost:8080/fm/film";
    if (filmObject != null) {
        film = (Film) filmObject;
        int id = film.getId();

        if (id != 0) {
            href += "?id=" + id;
        }
    }
%>
<div class="film">
    <div class="ph">
        <a href="<%=href%>"><%
            String outPath = "<img class=\"ph\" src=\"";
            if (film != null) {
                String photoUrl = film.getPhotoPath();
                if (photoUrl != null) {
                    outPath += photoUrl;
                }
            }
            outPath += "\">";
            out.print(outPath);
        %></a>
    </div>
</div>
<div class="info">
    <a href="<%=href%>"><%
        String outName = "";
        if (film != null) {
            outName = film.getName();
        }

        if (outName != null && !outName.equals("")) {
            out.print(outName);
        } else {
            out.print("Неизвестно");
        }
    %></a>

    <p>Год, страна :<%
        String outYear = "";
        String[] countries = new String[] {};

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
            out.print(" " + outYear);
        } else {
            out.print(" Неизвестный год");
        }

        if (countries != null && countries.length != 0) {
            StringBuilder outCountries = new StringBuilder();
            for (String country : countries) {
                outCountries.append(", ").append(country);
            }
            out.print(outCountries);
        } else {
            out.print(", Неизвестные страны");
        }
    %></p>
    <p>Жанр :<%
        String[] genres = new String[] {};

        if (film != null) {
            genres = film.getGenres();
        }

        if (genres != null && genres.length != 0) {
            StringBuilder outGenres = new StringBuilder();
            for (String genre : genres) {
                outGenres.append(", ").append(genre);
            }
            out.print(outGenres);
        } else {
            out.print(" Неизвестно");
        }
    %></p>

    <input type="button" value="Сохранить на будущее" onclick="window.location.href = <%
        int filmId = 0;
        if (film != null) {
            filmId = film.getId();

        }
        if (filmId != 0) {
                out.print("\'http://localhost:8080/fm/save-to-will-watch?film_id=" +
                filmId + "\'");
        } else {
                out.print("\'http://localhost:8080/fm/associations\'");
        }
    %>">
    <input class="button4" type="button" id="b4" onclick="<%
        if (filmId == 0) {
                out.print("window.location.href = \'http://localhost:8080/fm/associations\'");
        } else {
                out.print("describe()");
        }
    %>;" value="Я смотрел!"/>
</div>
