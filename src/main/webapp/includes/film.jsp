<%@ page import="dto.*" %><%--
  Created by IntelliJ IDEA.
  User: katty
  Date: 31.10.2020
  Time: 5:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Film film = (Film) session.getAttribute("film");
%>
<div class="info" id="info">
    <a href="http://localhost:8080/fm/film"><%
        if (film == null) {
            out.print(". . .");
        } else {
            out.print(film.getName());
        }
    %>
    </a>

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

    <input type="button" value="Сохранить на будущее" onclick="window.location.href = '/fm/save-to-wanted';">
</div>
