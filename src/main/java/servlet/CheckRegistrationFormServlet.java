package servlet;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/check_registration")
public class CheckRegistrationFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("username");
        final String password1 = request.getParameter("password1");
        final String password2 = request.getParameter("password2");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        final UserDao dao = (UserDao) request.getServletContext().getAttribute("userDao");

        if (username != null) {
            if (!dao.isUsernameExist(username)) {
                response.getWriter().write("true");
            } else {
                response.getWriter().write("false");
            }
        } else {
            if (password1.equals(password2)) {
                response.getWriter().write("true");
            } else {
                response.getWriter().write("false");
            }
        }
    }
}
