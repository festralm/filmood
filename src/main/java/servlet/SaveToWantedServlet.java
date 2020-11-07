package servlet;

import lombok.SneakyThrows;
import service.FilmUserWantedService;
import useful.Cookies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/save-to-watched")
public class SaveToWantedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (Cookies.checkCookie(request)) {
            HttpSession session = request.getSession();
            final int userId = (int) session.getAttribute("user_id");
            final int filmId = (int) request.getAttribute("film_id");

            FilmUserWantedService filmUserWantedService =
                    new FilmUserWantedService();
            filmUserWantedService.addFilmUserByIds(filmId, userId);

            request.setAttribute("button", "Выйти");
            response.sendRedirect("/fm");
        } else {
            response.sendRedirect("/fm/authorize");
        }
    }
}
