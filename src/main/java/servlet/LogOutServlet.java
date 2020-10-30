package servlet;

import useful.CheckSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (CheckSession.check(request.getSession(), request)) {
//            Cookie cookie = new Cookie("user", "");
//            cookie.setMaxAge(0);
//            //cookie.setMaxAge(-1); // удаляется тогда, когда закроет браузер
//            response.addCookie(cookie);
//        }
//        response.sendRedirect("/fm");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (CheckSession.check(session, request)) {
            Cookie cookie = new Cookie("user", "");
            cookie.setMaxAge(0);
            //cookie.setMaxAge(-1); // удаляется тогда, когда закроет браузер
            response.addCookie(cookie);
            session.setAttribute("username", null);
        }
        session.setAttribute("button", "Войти");
        response.sendRedirect("/fm/");
    }
}
