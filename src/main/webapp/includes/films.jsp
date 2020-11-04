<%@ page import="dto.Film" %><%--
  Created by IntelliJ IDEA.
  User: katty
  Date: 30.10.2020
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Film[] films = (Film[]) session.getAttribute("films");
    if (films != null) {
        for (int i = 0; i < films.length; i++) {
            StringBuilder outString = new StringBuilder("<div class=\"f1\">" +
                    "\n<div class=\"ph\">" +
                    "\n<a href=\"");
            if (films[i] != null) {
                String href = "http://localhost:8080/fm/film";
                int id = films[i].getId();

                if (id != 0) {
                    href += "?id=" + id;
                }
                outString.append(href).append("\">").append("\n<img class=\"ph\" src=\"");

                String photoUrl = films[i].getPhotoPath();
                String name = films[i].getName();

                if (photoUrl != null) {
                    outString.append(photoUrl).append("\"/>");
                }

                outString.append("\n</a>\n</div>" +
                        "\n<a href=\"").append(href).append("\">");

                if (name != null && !name.equals("")) {
                    outString.append(name);
                }

                outString.append("</a>" +
                        "\n</div>");
            }

            out.print(outString.toString());
        }
    }
%>