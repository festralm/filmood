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
    <title>Категории</title>

    <link rel="stylesheet" type="text/css" href="styles/allFilms.css">
    <script src="js/filter.js"></script>
</head>
<body>
<jsp:include page="includes/menu.jsp"/>
<div class="container">
    <nav>
        <ul>
            <li data-f="all">ВСЕ КАТЕГОРИИ</li>
            <li data-f="melodramas" class="text-melodramas">МЕЛОДРАМЫ</li>
            <li data-f="comedies" class="text-comedies">КОМЕДИИ</li>
            <li data-f="thrillers" class="text-thrillers">ТРИЛЛЕРЫ</li>
            <li data-f="dramas" class="text-dramas">ДРАМЫ</li>
        </ul>
    </nav>

    <div class="rec" id="rec">

        <c:forEach var="film" items="${films}">
            <div class="col-auto box f1-comedies">
                <div class="ph">
                    <a href="http://localhost:8080/fm/film?id=<c:out value="${film.getId()}"/>">
                        <img class="ph" src="<c:out value="${film.getPhotoPath()}"/>">
                    </a>
                </div>
                <a class="a_for_film" href="http://localhost:8080/fm/film?id=<c:out value="${film.getId()}"/>"><c:out value="${film.getName()}" /></a>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>