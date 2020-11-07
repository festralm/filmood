package servlet;

import service.UserService;
import useful.Cookies;
import useful.LogIn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/register-in")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        final String username = request.getParameter("username");
        final char[] password1 = request.getParameter("password").toCharArray();
        final String email = request.getParameter("email");

        HttpSession session = request.getSession();

        if (Cookies.checkCookie(request)) {
            response.sendRedirect("/fm/");
        } else {
            UserService userService = new UserService();
            userService.addNewUser(username, password1, email);
            session.setAttribute("button", "Войти");
            LogIn.logIn(userService.getUserIdByUsername(username), request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
