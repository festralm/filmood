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
    <title>${fullname}</title>

    <link rel="stylesheet" type="text/css" href="styles/Profile.css">
</head>
<body>
<%
    Object button = request.getSession().getAttribute("button");
%>

<jsp:include page="includes/menu.jsp">
    <jsp:param name="button" value="<%=button%>"/>
</jsp:include>

<div class="anketa" id="anketa">
    <div class="photo" id="photo">
        <img src="${photo_path}" alt="Avatar" class="avatar">
    </div>

    <div class="info" id="info">
        <h1>${fullname}</h1>
        <h2>${username}</h2>

        <div class="categories" id="category">
            <h3>Любимые жанры:</h3>
            <h4>${favorite_genres[0]}</h4>
            <h4>${favorite_genres[1]}</h4>
            <h4>${favorite_genres[2]}</h4>
        </div>
        <h3></h3>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>