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
    <title>Буду смотреть</title>

    <link rel="stylesheet" type="text/css" href="styles/MyMovies.css">
</head>
<body>
<jsp:include page="includes/menu.jsp"/>

<div class="rec" id="rec">
    <h2>Буду смотреть (когда-нибудь)</h2>
    <jsp:include page="includes/films.jsp"/>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>