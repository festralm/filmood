package servlet;

import dto.Comment;
import dto.Film;
import dto.User;
import service.CommentService;
import service.FilmUserWatchedService;
import service.UserService;
import useful.CheckSession;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/comments")
public class CommentsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        if (CheckSession.check(session, request)) {
            CommentService commentService = new CommentService();
            Comment[] comments = commentService.geAllComments();

            session.setAttribute("button", "Выйти");
//            String path = "/MyMovies.jsp";
//            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
//            requestDispatcher.forward(request, response);
        } else {
            session.setAttribute("button", "Войти");
            response.sendRedirect("/fm/authorize");
        }
    }
}
