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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (CheckSession.check(session, request)) {
            UserService userService = new UserService();
            int userId = (int) session.getAttribute("user_id");

            int friendId = userService.getRandomFriendByUserId(userId);

            Film[] films = userService.getFavoriteFilmsByUserId(friendId);

            session.setAttribute("films", films);

            session.setAttribute("button", "Выйти");
            String path = "/Recommendation.jsp";
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        } else {
            session.setAttribute("button", "Войти");
            response.sendRedirect("/fm/authorize");
        }
    }
}
