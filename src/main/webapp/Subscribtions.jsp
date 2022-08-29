<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Мои подписки (друзья)</title>

    <link rel="stylesheet" type="text/css" href="styles/Subscribtions.css">
</head>
<body>
<jsp:include page="includes/menu.jsp"/>
<h2>Список ваших подписок :</h2>

<div class="my_friends">
    <div class="user_info" id="user_info">
        <div class="ph" id="ph">
            <img class="ph_user" src="${photo_path}">
        </div>

        <div class="info" id="info">
            <a id="user_name" href="http://localhost:8080/fm/profile">${name}</a>
        </div>

        <div class="buttons">
            <button class="btn1" onclick="window.location.href = 'http://localhost:8080/fm/desubscribe?id=${id}'">Отписаться</button>
        </div>
    </div>
</div>

<footer>
    <p>Мы всегда готовы Вам помочь!</p>
    <p>По всем вопросам пишите на почту: email.example@gmail.com</p>
</footer>
</body>
</html>