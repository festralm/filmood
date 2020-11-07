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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (Cookies.checkCookie(request)) {
            final HttpSession session = request.getSession();
            int userId = (int) session.getAttribute("user_id");

            UserService userService = new UserService();
            Film[] favoriteFilms = userService.getFavoriteFilmsByUserId(userId);
            request.setAttribute("films", favoriteFilms);

            request.setAttribute("button", "Выйти");
            String path = "/Favorites.jsp";
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/fm/authorize");
        }
    }
}
