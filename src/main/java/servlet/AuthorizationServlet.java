package servlet;

import service.UserService;
import useful.Cookies;
import useful.LogIn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class AuthorizationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();

        if (Cookies.checkCookie(request)) {
            response.sendRedirect("/fm/");
        } else {
            final String username = request.getParameter("username");
            final char[] password = request.getParameter("password").toCharArray();

            UserService userService = new UserService();

            if (userService.authenticateUser(username, password)) {
                session.setAttribute("button", "Выйти");
                LogIn.logIn(userService.getUserIdByUsername(username), request, response);
            } else {
                Cookie cookie = new Cookie("check_password", "200px");
                    cookie.setMaxAge(-1);
                response.addCookie(cookie);

                response.sendRedirect("/fm/authorize");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
