package servlet;

import service.UserService;
import useful.CheckSession;
import useful.LogIn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class AuthorizationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();

        if (CheckSession.check(session, request)) {
            response.sendRedirect("/fm/");
        } else {
            final String username = request.getParameter("username");
            final char[] password = request.getParameter("password").toCharArray();

            UserService userService = new UserService();

            if (userService.authenticateUser(username, password)) {
                LogIn.logIn(username, request, response);
            } else {

                session.setAttribute("check_password", false);
                response.sendRedirect("authorize");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
