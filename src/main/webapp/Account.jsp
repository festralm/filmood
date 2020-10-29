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
    <title>Мой профиль</title>

    <link rel="stylesheet" type="text/css" href="styles/Account.css">
</head>
<body>
<div class="menu" role="menu">
    <div class="page_name" id="page_name">
        <a href="http://localhost:8080/fm">FILM <span class="colortext">&</span> MOOD</a>
    </div>

    <nav>
        <a href="pages/forFeature/myFilms.html" id="myFilms">Моё кино</a> ||
        <a href="pages/forFeature/Favorites.html" id="favorites">Избранное</a> ||
        <a href="pages/forFeature/selectedFilms.html" id="selectedFilms">Буду смотреть</a> ||
        <a href="pages/forFeature/Categories.html" id="categories">Категории</a> ||
        <a href="http://localhost:8080/fm/profile" id="account">Мой профиль</a>
    </nav>
</div>

<div class="photo" id="photo">
    <img src="https://sun1-24.userapi.com/Se9Kf-XIcLjI0hkHL7zIYMYV1fJtWk3omc6O4A/q2u4_QgJaG4.jpg" alt="Avatar" class="avatar">
</div>
<div class="refactorImg">
    <a class="rimg" href="Refactor.html">Сменить фото профиля</a>
</div>


<div class="box">
    <%--    <h2>Имя и фамилия:</h2>--%>
    <%--    <input type="text" class="input" value="Абрамский Михаил">--%>
    <form action="edit_profile" method="post">
        <h2>Имя и фамилия:</h2>
        <input name="fullname" type="text" class="input" value="${fullname}">

        <h2>Имя пользователя: </h2>
        <input name="username" type="text" class="input" value="${username}">

        <h2>E-mail:</h2>
        <input name="email" type="text" class="input" value="${email}">

        <h2>Дата рождения:</h2>
        <input name="birthdate" type="text" class="input" value="${birthdate}">

        <h2>Пароль</h2>
        <input name="password" type="password" class="input" value="********">

        <div class="button">
            <button class="btn">Редактировать</button>
        </div>
    </form>
</div>

<footer>
    <p>Мы всегда готовы Вам помочь!</p>
    <p>По всем вопросам пишите на почту: email.example@gmail.com</p>
</footer>
</body>
</html>
