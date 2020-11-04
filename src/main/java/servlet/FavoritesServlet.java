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

@WebServlet("/favorites")
public class FavoritesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        if (CheckSession.check(session, request)) {
            UserService userService = new UserService();
            int userId = (int)session.getAttribute("user_id");

            Film[] favoriteFilms = userService.getFavoriteFilmsByUserId(userId);

            session.setAttribute("films", favoriteFilms);

            session.setAttribute("button", "Выйти");
            String path = "/Favorites.jsp";
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        } else {
            session.setAttribute("button", "Войти");
            response.sendRedirect("/fm/authorize");
        }
    }
}
