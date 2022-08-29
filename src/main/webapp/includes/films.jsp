<%@ page import="dto.Film" %><%--
  Created by IntelliJ IDEA.
  User: katty
  Date: 30.10.2020
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="film" items="${films}">
    <div class="f1">
        <div class="ph">
            <a href="http://localhost:8080/fm/film?id=<c:out value="${film.getId()}"/>">
                <img src="<c:out value="${film.getPhotoPath()}"/>" class="ph">
            </a>
        </div>
        <a href="http://localhost:8080/fm/film?id=<c:out value="${film.getId()}"/>"><c:out value="${film.getName()}" /></a>
    </div>
</c:forEach>