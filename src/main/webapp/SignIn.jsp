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
    <title>Вход</title>

    <link rel="stylesheet" type="text/css" href="styles/SignIn.css">
</head>
<body>
<div class="menu" role="menu">
    <div class="page_name" id="page_name">
        <a href="http://localhost:8080/fm">FILM <span class="colortext">&</span> MOOD</a>
    </div>

    <nav>
        <nav>
            <jsp:include page="includes/menu.jsp"/>
        </nav>
    </nav>
</div>

<div class="form" id="form">
    <div class="image">
    </div>

    <div class="authorization_form">
        <form method="post" action="login">
            <h1>Вход</h1>
            <div>
                <input type="text" name="username" id="username" placeholder=" " required/>
                <label for="username">Имя пользователя</label>
            </div>


            <div>
                <input type="password" name="password" id="password" placeholder=" " minlength="8" maxlength="32" required/>
                <label for="password">Введите пароль</label>

                <div class="requirements" style="max-height: <%
                Object passwordIsTrue = request.getSession().getAttribute("check_password");
                    if (passwordIsTrue != null) {
                        out.print("200px");
                    } else {
                        out.print("0");
                    }
                request.getSession().setAttribute("check_password", null);
                 %>">
                    Пароль неверный
                </div>
            </div>


            <p>
                <label><input type="checkbox" name="remember_me">Запомнить меня</label>
            </p>

            <p>
                <input class="button" type="submit" name="submit" id="submit" value="Г О Т О В О !"/>
            </p>


            <a class="registration" id="registration" href="http://localhost:8080/fm/register">Ещё не зарегистрированы?</a>


        </form>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>
