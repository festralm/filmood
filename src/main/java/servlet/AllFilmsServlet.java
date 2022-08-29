package servlet;

import dto.Film;
import service.FilmService;
import service.GenreService;
import useful.Cookies;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/all-films")
public class AllFilmsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String genre = request.getParameter("genre");

        FilmService filmService = new FilmService();
        Film[] films;
        if (genre == null) {
            films = filmService.getAllFilms();
            for (Film film : films) {
                filmService.addGenres(film);
            }
        } else {
            films = filmService.geFilmsByGenre(genre);
            for (Film film : films) {
                filmService.addGenres(film);
            }
        }

        GenreService genreService = new GenreService();
        String[] genres = genreService.getAllGenres();

        request.setAttribute("films", films);
        request.setAttribute("genres", genres);

        if (Cookies.checkCookie(request)) {
            request.setAttribute("button", "Выйти");
        } else {
            request.setAttribute("button", "Войти");
        }

        String path = "/allFilms.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
