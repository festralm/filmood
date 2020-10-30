<%--
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
    <title>FILMood</title>
</head>

<link rel="stylesheet" type="text/css" href="styles/helloPage.css">

<body>

<div class="area1" id="area1">
    <%
        Object buttonText = request.getSession().getAttribute("button");
    %>

    <jsp:include page="includes/menu.jsp">
        <jsp:param name="button" value="<%=(String)buttonText%>"/>
    </jsp:include>

    <header role="banner">
        <h1>Не просто смотри кино,</h1>
        <h1>. . . чувствуй его . . .</h1>
    </header>
</div>
<jsp:include page="includes/functions.jsp"/>
<jsp:include page="includes/footer.jsp"/>
<jsp:include page="includes/popup.jsp"/>
</body>
</html>