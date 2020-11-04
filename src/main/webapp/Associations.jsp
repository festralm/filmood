<%@ page import="dto.*" %><%--
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
    <title>Ассоциации</title>

    <link rel="stylesheet" type="text/css" href="styles/Associations.css">
    <script src="js/validation.js"></script>
    <script src="js/popup.js"></script>
</head>
<body>
<%
    Object button = request.getSession().getAttribute("button");
%>

<jsp:include page="includes/menu.jsp">
    <jsp:param name="button" value="<%=button%>"/>
</jsp:include>

<div class="area1" id="area1">
    <h1>СЛОВА-АССОЦИАЦИИ</h1>
    <h3>Выберите подходящие тэги, чтобы найти тот самый фильм :) </h3>

    <form method="post" action="search">
        <p>
            <input oninput="check_word()" type="search" name="search"
                   id="search" placeholder="   #любовь,   #мелодрама,   #танцы" >
        </p>

        <div class="tags" id="tahgs">
            <h5>Популярные запросы :</h5>

            <%
                Object wordsObj = request.getSession().getAttribute("words");

                if (wordsObj != null) {
                    String[] words = (String[]) wordsObj;
                    for (int i = 0; i < Math.min(words.length, 4); i++) {
                        out.print("<h6>#" + words[i] + "</h6>");
                    }
                }
            %>
        </div>

        <p>
            <input type="submit" value="Найти" id="submit" disabled>
        </p>
    </form>
</div>
<%
    Object film = session.getAttribute("film");
%>
<div class="area2" id="area2">
    <div class="new" id="new">
        <jsp:include page="includes/film.jsp">
            <jsp:param name="film" value="<%=film%>"/>
        </jsp:include>

    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
<jsp:include page="describe_popup.jsp"/>

<%
    request.getSession().setAttribute("film", null);
%>
</body>
</html>