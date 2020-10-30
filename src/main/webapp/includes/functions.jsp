<%--
  Created by IntelliJ IDEA.
  User: katty
  Date: 30.10.2020
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="area2" id="area2">
    <div class="words" id="words">
        <h3>Слова-ассоциации</h3>
        <p>пользователь вводит слова или выбирает предложенные из списка и получает список фильмов по данным темам</p>

        <input class="button3" type="button" onclick="window.location.href = '/fm/associations';" value="Выбрать слова"/>
    </div>

    <div class="lottery" id="lottery">
        <h3>Кино-лотерея</h3>
        <p>на экране появляется набор карточек, пользователь выбирает одну из них, и ему выпадает случайный фильм</p>

        <input class="button4" type="button" onclick="window.location.href = '#popup';" value="Довериться судьбе"/>
    </div>

    <div class="recommendation" id="recommendation">
        <h3>Рекомендации недели</h3>
        <p>ознакомьтесь с лучшими фильмами этой недели</p>
        <input class="button5" type="button" onclick="window.location.href = '/fm/recommendations';"value="Просмотреть рекомендации"/>
    </div>
</div>
