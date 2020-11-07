package servlet;

import dto.Film;
import service.FilmService;
import service.WordService;
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
import java.util.Arrays;

@WebServlet("/associations")
public class AssociationsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WordService wordService = new WordService();
        String[] words = wordService.getAllWords();
        request.setAttribute("words", Arrays.copyOf(words, 4));

        request.setCharacterEncoding("UTF-8");

        if (Cookies.checkCookie(request)) {
            request.setAttribute("button", "Выйти");
        } else {
            request.setAttribute("button", "Войти");
        }

        String inputWord = request.getParameter("word");
        if (inputWord != null) {
            FilmService filmService = new FilmService();
            Film film;
            if (Cookies.checkCookie(request)) {
                HttpSession session = request.getSession();
                final int userId = (int) session.getAttribute("user_id");
                film = filmService.getFilmByWordAndUserId(inputWord, userId);
            } else {
                film = filmService.getFilmByWord(inputWord);
            }
            if (film != null) {
                request.setAttribute("film", film);
            }
        }
        String path = "/Associations.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
