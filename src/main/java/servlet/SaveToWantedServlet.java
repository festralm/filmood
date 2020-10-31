package servlet;

import dao.UserDao;
import dto.Film;
import dto.User;
import service.FilmService;
import service.FilmUserWillWatchService;
import service.UserService;
import useful.CheckSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/save-to-wanted")
public class SaveToWantedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (CheckSession.check(session, request)) {
            final String username = (String) session.getAttribute("username");
            final Film film = (Film) session.getAttribute("film");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            UserService userService = new UserService();
            User user = userService.getUserByUsername(username);

            FilmUserWillWatchService filmUserWillWatchService = new FilmUserWillWatchService();

            session.setAttribute("button", "Выйти");
            filmUserWillWatchService.addFilmAndUser(user.getId(), film.getId());
            response.sendRedirect("/fm");
//            if (filmUserWillWatchService.addFilmAndUser(user.getId(), film.getId())) {
//                response.getWriter().write("true");
//            } else {
//                response.getWriter().write("false");
//            }
        } else {

            session.setAttribute("button", "Войти");
            response.sendRedirect("/fm/authorize");
        }
    }
}
