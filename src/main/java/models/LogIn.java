package models;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogIn {
    public static void logIn(String login, String password,
                             ServletRequest req, ServletResponse resp,
                             HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("password", password);
        request.getSession().setAttribute("check_password", true);

        Cookie cookie = new Cookie("user", login + password);
        if (req.getParameter("remember") == null) {
            cookie.setMaxAge(60 * 30);
        }
        else {
            cookie.setMaxAge(60 * 60 * 2);
        }
        response.addCookie(cookie);

        req.getRequestDispatcher("html/temp_main_page_authorized.html").forward(req, resp);
    }
}
