package servlet;

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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        if (CheckSession.check(session, request)) {
            session.setAttribute("button", "Выйти");
            int profileId = (int) request.getAttribute("id");

            UserService userService = new UserService();
            int userId = (int) session.getAttribute("user_id");

            User user = userService.getUserByUserId(userId);

            request.setAttribute("photo_path", user.getPhotoPath());
            request.setAttribute("fullname", user.getFullname());
            request.setAttribute("username", user.getUsername());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("birthdate", user.getBirthdate() == null ? "" : user.getBirthdate().toString());

            if (profileId == 0) {
                servletContext.getRequestDispatcher("/Account.jsp")
                        .forward(request, response);
            } else {
                servletContext.getRequestDispatcher("/Profile.jsp")
                        .forward(request, response);
            }
        } else {
            session.setAttribute("button", "Войти");
            response.sendRedirect("/fm/authorize");
        }
    }
}
