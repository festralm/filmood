package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/edit-profile")
public class EditProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        final String fullname = request.getParameter("fullname");
        final String username = request.getParameter("username");
        final String email = request.getParameter("email");
        final String birthdate = request.getParameter("birthdate");
        final char[] password = request.getParameter("password").toCharArray();

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("user_id");

        UserService userService = new UserService();
        userService.editUser(userId, username, password, email, fullname, birthdate);
        response.sendRedirect("/fm/profile");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
