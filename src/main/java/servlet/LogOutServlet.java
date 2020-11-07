package servlet;

import useful.Cookies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (Cookies.checkCookie(request)) {
            Cookie cookie = new Cookie("user_id", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            request.getSession().setAttribute("user_id", null);
        }
        response.sendRedirect("/fm/");
    }
}
