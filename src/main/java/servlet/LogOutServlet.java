package servlet;

import useful.CheckSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CheckSession.check(request.getSession(), request)) {
            Cookie cookie = new Cookie("user", "");
            cookie.setMaxAge(0);
            //cookie.setMaxAge(-1); // удаляется тогда, когда закроет браузер
            response.addCookie(cookie);
        }
        response.sendRedirect("/fm");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (CheckSession.check(request.getSession(), request)) {
            Cookie cookie = new Cookie("user", "");
            cookie.setMaxAge(0);
            //cookie.setMaxAge(-1); // удаляется тогда, когда закроет браузер
            response.addCookie(cookie);
            request.getSession().setAttribute("username", null);
        }
        response.sendRedirect("/fm/");
    }
}
