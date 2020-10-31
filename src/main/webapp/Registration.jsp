<%--
  Created by IntelliJ IDEA.
  User: katty
  Date: 29.10.2020
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>

    <link rel="stylesheet" type="text/css" href="styles/Registration.css">
</head>
<body>
<%
    Object button = request.getSession().getAttribute("button");
%>

<jsp:include page="includes/menu.jsp">
    <jsp:param name="button" value="<%=button%>"/>
</jsp:include>

<div class="form">
    <div class="authorization_form">
        <form method="post" action="registerin">
            <h1>Регистрация</h1>
            <div>
                <input type="text" name="username" id="username" placeholder=" " required/>
                <label for="username">Имя пользователя</label>
            </div>

            <div>
                <input type="email" name="email" id="email" placeholder=" " required/>
                <label for="email">E-mail</label>

                <div class="requirements">
                    E-mail введён некорректно
                </div>
            </div>

            <div>
                <input type="password" name="password" id="password" placeholder=" " minlength="8" maxlength="32"
                       required/>
                <label for="password">Введите пароль</label>

                <div class="requirements">
                    Пароль должен содержать не менее 8 знаков
                </div>
            </div>

            <div>
                <input type="password" name="repeat_password" id="repeat_password" placeholder=" "
                       required/>
                <label for="repeat_password">Повторите пароль</label>

                <div class="requirements" style="max-height:
            <%
                Object passwordsAreEqual = request.getSession().getAttribute("passwords");
                if (passwordsAreEqual != null) {
                    out.print("200px");
                } else {
                    out.print("0");
                }
                request.getSession().setAttribute("passwords", null);
            %>">
                    Введённые пароли не совпадают
                </div>
            </div>

            <p>
                <label><input class="check" type="checkbox" name="remember_me">Запомнить меня</label>
            </p>

            <p>
                <input class="button" type="submit" name="submit" id="submit" value="Г О Т О В О !"/>
            </p>
            <%
                Object loginExists = request.getSession().getAttribute("check_login");
                if (loginExists != null) {
                    out.print("200px");
                } else {
                    out.print("0");
                }
                request.getSession().setAttribute("check_login", null);
            %>
        </form>
    </div>
</div>
<jsp:include page="includes/footer.jsp"/>
</body>
</html>
