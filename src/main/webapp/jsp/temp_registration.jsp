<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>
<body>
<div>
    <h1>Log in</h1>
    <%
        boolean check_login = true;
        Object s = session.getAttribute("check_login");
        if (s != null) {
            check_login = Boolean.parseBoolean(s.toString());
        }
        if (!check_login) {
            out.println("<p>That username already exists</p>");
        }
    %>
    <form method="post" action="registerin">
        <p><input type="text" required placeholder="login" name="login"></p>
        <p><input type="password" required placeholder="password" name="password"></p>
        <br>
        <p><input type="submit" value="Register"></p>
        <p><input type="checkbox" name="remember" id="remember"> <label for="remember">Remember me</label></p>
    </form>
</div>
</body>
</html>
