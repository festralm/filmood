<%--
  Created by IntelliJ IDEA.
  User: katty
  Date: 03.11.2020
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="../js/popup.js"></script>
<div id="popup" class="popup">
    <div class="popup_body">
        <div class="popup_content">
            <a href="http://localhost:8080/fm" class="popup_close">Х</a>
            <div class="popup_title">Итааак ... сегодня смотрим фильм</div>
            <div class="popup_text">
                <div class="new" id="new">
                    <jsp:include page="film.jsp"/>
                </div>
            </div>
        </div>
    </div>
</div>
