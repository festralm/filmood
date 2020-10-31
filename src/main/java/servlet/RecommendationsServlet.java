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
            String username = (String)session.getAttribute("username");

            User user = userService.getUserByUsername(username);
            int id = user.getId();
            FriendService friendService = new FriendService();

            User friend = friendService.getFriendByUserId(id);
            int friendsId = friend.getId();

            FilmUserFavoriteService filmUserFavoriteService = new FilmUserFavoriteService();
            Film[] films = filmUserFavoriteService.getFilmsByUserId(friendsId, 10);
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
