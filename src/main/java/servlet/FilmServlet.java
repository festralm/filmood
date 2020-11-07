package servlet;

import dto.Film;
import lombok.SneakyThrows;
import service.FilmService;
import useful.Cookies;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/film")
public class FilmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final HttpSession session = request.getSession();
        int filmId = Integer.parseInt(request.getParameter("id"));

        FilmService filmService = new FilmService();
        Film film = filmService.getFilmById(filmId);
        filmService.addCountries(film);
        filmService.addGenres(film);
        filmService.addComments(film);
        request.setAttribute("film", film);
        if (Cookies.checkCookie(request)) {
            session.setAttribute("button", "Выйти");

        } else {
            session.setAttribute("button", "Войти");
        }
        ServletContext servletContext = getServletContext();
        String path = "/Film.jsp";
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
