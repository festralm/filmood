package servlet;

import lombok.SneakyThrows;
import service.FilmWordService;
import service.WordService;
import useful.CheckSession;
import exception.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/send-word")
public class SendWordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (CheckSession.check(session, request)) {
            final int filmId = (int) session.getAttribute("film_id");
            final String word = request.getParameter("comment2");

            FilmWordService filmWordService = new FilmWordService();

            if (!filmWordService.addWordUserByIds(filmId, word)) {
                throw new CouldNotAddWordException();
            }

            session.setAttribute("button", "Выйти");
            response.sendRedirect("/fm");
        } else {

            session.setAttribute("button", "Войти");
            response.sendRedirect("/fm/authorize");
        }
    }
}
