package servlet;

import dto.User;
import service.UserService;
import useful.Cookies;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (Cookies.checkCookie(request)) {
            final HttpSession session = request.getSession();
            int userId = (int) session.getAttribute("user_id");

            UserService userService = new UserService();
            int profileId = Integer.parseInt(request.getParameter("id"));
            User user;
            if (profileId == 0) {
                user = userService.getUserByUserId(userId);
            } else {
                user = userService.getUserByUserId(profileId);
            }

            request.setAttribute("photo_path", user.getPhotoPath());
            request.setAttribute("fullname", user.getFullname());
            request.setAttribute("username", user.getUsername());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("birthdate", user.getBirthdate() == null ? ""
                    : user.getBirthdate().toString());

            request.setAttribute("button", "Выйти");
            ServletContext servletContext = getServletContext();
            if (profileId == 0) {
                servletContext.getRequestDispatcher("/Account.jsp")
                        .forward(request, response);
            } else {
                servletContext.getRequestDispatcher("/Profile.jsp")
                        .forward(request, response);
            }
        } else {
            response.sendRedirect("/fm/authorize");
        }
    }
}
