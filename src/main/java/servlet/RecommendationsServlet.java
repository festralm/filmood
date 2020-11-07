package servlet;

import dto.*;
import service.*;
import useful.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/recommendations")
public class RecommendationsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (Cookies.checkCookie(request)) {
            HttpSession session = request.getSession();
            int userId = (int) session.getAttribute("user_id");

            UserService userService = new UserService();
            int friendId = userService.getRandomFriendIdByUserId(userId);
            Film[] films = new Film[0];
            if (friendId != -1) {
                films = userService.getFavoriteFilmsByUserId(friendId);
            }
            request.setAttribute("films", films);

            request.setAttribute("button", "Выйти");
            String path = "/Recommendation.jsp";
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/fm/authorize");
        }
    }
}
