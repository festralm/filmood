package servlet;

import exception.IncorrectFilmIdException;
import lombok.SneakyThrows;
import service.FilmWordService;
import useful.Cookies;
import useful.FilmId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/send-word")
public class SendWordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (Cookies.checkCookie(request)) {
            int filmId = -1;
            try {
                filmId = FilmId.getFilmIdForPopup(request, response);
            } catch (IncorrectFilmIdException exception) {
                response.sendRedirect("/fm");
            }
            if (filmId != -1) {
                FilmWordService filmWordService = new FilmWordService();
                final String word = request.getParameter("comment2");
                filmWordService.addWordUserByIds(filmId, word);

                request.setAttribute("button", "Выйти");
                response.sendRedirect("/fm");
            }
        } else {
            response.sendRedirect("/fm/authorize");
        }
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
