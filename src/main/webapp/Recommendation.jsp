<%@ page import="dto.Film" %><%--
  Created by IntelliJ IDEA.
  User: katty
  Date: 29.10.2020
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Рекомендации недели</title>

    <link rel="stylesheet" type="text/css" href="styles/Recommendation.css">
</head>
<body>
<%
    Object button = request.getSession().getAttribute("button");
%>

<jsp:include page="includes/menu.jsp">
    <jsp:param name="button" value="<%=button%>"/>
</jsp:include>

<div class="rec" id="rec">
    <h2>Рекомендасьён :</h2>


    <%
        Object film = session.getAttribute("films");
    %>
    <jsp:include page="includes/films.jsp">
        <jsp:param name="films" value="<%=film%>"/>
    </jsp:include>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>