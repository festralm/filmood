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
            out.print("<div class=\"f1\" id=\"f1\">" +
                    "\n<div class=\"ph\" id=\"ph1\"></div>" +
                    "\n<a href=\"http://localhost:8080/fm/film\">" + films[i].getName() + "</a>" +
                    "\n</div>");
        }
    }
%>