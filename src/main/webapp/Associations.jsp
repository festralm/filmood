<%@ page import="dto.*" %><%--
  Created by IntelliJ IDEA.
  User: katty
  Date: 29.10.2020
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ассоциации</title>

    <link rel="stylesheet" type="text/css" href="styles/Associations.css">
    <script src="js/validation.js"></script>
    <script src="js/popup.js"></script>
    <script src="js/hide_area2.js"></script>
</head>
<body>

<jsp:include page="includes/menu.jsp"/>

<div class="area1" id="area1">
    <h1>СЛОВА-АССОЦИАЦИИ</h1>
    <h3>Выберите подходящие тэги, чтобы найти тот самый фильм :) </h3>

    <form id="search_from" method="post" action="search">
        <p>
            <input oninput="check_word()" type="search" name="search"
                   id="search" placeholder="   #любовь,   #мелодрама,   #танцы" >
        </p>

        <div class="tags" id="tahgs">
            <h5>Популярные запросы :</h5>

            <c:forEach var="word" items="${words}">
                <h6>#<c:out value="${word}"/></h6>
            </c:forEach>
        </div>

        <p>
            <input type="submit" value="Найти" id="submit" disabled>
            <%--                   onclick="window.location.href = '#area2';" >--%>
        </p>
    </form>
</div>
<c:if test="${film != null}">
    <div name="area2" class="area2" id="area2">
        <div class="new" id="new">
            <jsp:include page="includes/film.jsp"/>
        </div>
    </div>
</c:if>
<jsp:include page="includes/footer.jsp"/>
<jsp:include page="includes/describe_popup.jsp"/>
</body>
</html>