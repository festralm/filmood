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
    <title>Категории</title>

    <link rel="stylesheet" type="text/css" href="styles/allFilms.css">
    <script src="filtr.js"></script>
</head>
<body>
<%
    Object button = request.getSession().getAttribute("button");
%>

<jsp:include page="includes/menu.jsp">
    <jsp:param name="button" value="<%=button%>"/>
</jsp:include>

<div class="container">
    <nav>
        <ul>
            <li data-f="all">ВСЕ КАТЕГОРИИ</li>
            <li data-f="alizarin" class="text-alizarin">МЕЛОДРАМЫ</li>
            <li data-f="wisteria" class="text-wisteria">КОМЕДИИ</li>
            <li data-f="emerland" class="text-emerland">ТРИЛЛЕРЫ</li>
            <li data-f="sunflower" class="text-sunflower">ДРАМЫ</li>
        </ul>
    </nav>

    <div class="rec" id="rec">
        <%
            Object film = session.getAttribute("films");
        %>
        <jsp:include page="includes/films.jsp">
            <jsp:param name="films" value="<%=film%>"/>
        </jsp:include>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>