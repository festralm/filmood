package servlet;

import exception.IncorrectFilmIdException;
import lombok.SneakyThrows;
import service.FilmUserWillWatchService;
import useful.Cookies;
import useful.FilmId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/save-to-will-watch")
public class SaveToWillWatchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (Cookies.checkCookie(request)) {
            HttpSession session = request.getSession();
            final int userId = (int) session.getAttribute("user_id");
            int filmId = -1;
            try {
                filmId = FilmId.getFilmIdForPopup(request, response);
            } catch (IncorrectFilmIdException exception) {
                response.sendRedirect("/fm");
            }
            if (filmId != -1) {
                FilmUserWillWatchService filmUserWillWatchService =
                        new FilmUserWillWatchService();
                filmUserWillWatchService.addFilmUserByIds(filmId, userId);

                request.setAttribute("button", "Выйти");
                response.sendRedirect("/fm/will-watch");
            }
        } else {
            response.sendRedirect("/fm/authorize");
        }
    }
}
