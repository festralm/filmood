<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Authorize</title>
</head>
<body>
<div>
    <h1>Log in</h1>
    <%
        boolean check_password = true;
        Object s = session.getAttribute("check_password");
        if (s != null) {
            check_password = Boolean.parseBoolean(s.toString());
        }
        if (!check_password) {
            out.println("<p>Wrong login or password</p>");
        }
    %>
    <form method="post" action="login">
        <p><input type="text" required placeholder="login" name="login"></p>
        <p><input type="password" required placeholder="password" name="password"></p>
        <br>
        <p><input type="submit" value="Log in"></p>
        <p><input type="checkbox" name="remember" id="remember"> <label for="remember">Remember me</label></p>
    </form>
</div>
</body>
</html>
