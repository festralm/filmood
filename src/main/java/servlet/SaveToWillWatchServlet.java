package servlet;

import exception.CouldNotAddWillWatchFilmException;
import lombok.SneakyThrows;
import service.FilmUserWillWatchService;
import useful.CheckSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/save-to-will-watch")
public class SaveToWillWatchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (CheckSession.check(session, request)) {
            final int userId = (int) session.getAttribute("user_id");
            final int filmId = (int) session.getAttribute("film_id");

            FilmUserWillWatchService filmUserWillWatchService = new FilmUserWillWatchService();

            if (!filmUserWillWatchService.addFilmUserByIds(filmId, userId)) {
                throw new CouldNotAddWillWatchFilmException();
            }

            session.setAttribute("button", "Выйти");
            response.sendRedirect("/fm");
        } else {

            session.setAttribute("button", "Войти");
            response.sendRedirect("/fm/authorize");
        }
    }
}
