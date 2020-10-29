package servlet;

import exception.CouldntAddData;
import exception.DataIsEmpty;
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
import java.util.Arrays;

@WebServlet("/registerin")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("username");
        final char[] password1 = request.getParameter("password").toCharArray();
        final char[] password2 = request.getParameter("repeat_password").toCharArray();
        final String email = request.getParameter("email");

        if (!Arrays.equals(password1, password2)) {
            request.getSession().setAttribute("passwords", false);
            response.sendRedirect("register");
        } else {
            final HttpSession session = request.getSession();

            if (CheckSession.check(session, request)) {
                response.sendRedirect(request.getContextPath());
            } else {
                UserService userService = new UserService();
                if (password1.length >= 8 && password1.length <= 32 && !userService.isUserExist(username)) {
                    try {
                        userService.enrollUser(username, password1, email);
                    } catch (DataIsEmpty | CouldntAddData dataIsEmpty) {
                        dataIsEmpty.printStackTrace();
                    }
                    LogIn.logIn(username, request, response);
                } else {
                    request.getSession().setAttribute("check_login", false);
                    response.sendRedirect("register");
                    //req.getRequestDispatcher("jsp/temp_registration.jsp").forward(req, resp);
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
