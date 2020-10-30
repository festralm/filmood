package servlet;

import dao.UserDao;
import dto.User;
import service.UserService;
import useful.CheckSession;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        final HttpSession session = request.getSession();
//        ServletContext servletContext = getServletContext();
//        if (CheckSession.check(session, request)) {
//            servletContext.getRequestDispatcher("/pages/helloPageForUser.jsp").forward(request, response);
//        } else {
//            servletContext.getRequestDispatcher("/pages/helloPage.jsp").forward(request, response);
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        if (CheckSession.check(session, request)) {

            UserService userService = new UserService();
            String username = (String) session.getAttribute("username");

            User user = userService.getUserByUsername(username);

            request.setAttribute("fullname", user.getFullname());
            request.setAttribute("username", username);
            request.setAttribute("email", user.getEmail());
            request.setAttribute("birthdate", user.getBirthdate() == null ? "" : user.getBirthdate().toString());

            servletContext.getRequestDispatcher("/Account.jsp").forward(request, response);
        } else {
            response.sendRedirect("/fm/authorize");
        }
    }
}
