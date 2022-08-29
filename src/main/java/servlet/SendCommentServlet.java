package servlet;

import lombok.SneakyThrows;
import service.CommentService;
import useful.Cookies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/comment")
public class SendCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (Cookies.checkCookie(request)) {
            HttpSession session = request.getSession();
            final int filmId = Integer.parseInt(request.getParameter("id"));
            final int userId = (int) session.getAttribute("user_id");
            String comment = request.getParameter("comment");

            CommentService commentService = new CommentService();
            commentService.addComment(filmId, userId, comment);

            request.setAttribute("button", "Выйти");
            response.sendRedirect("/fm/film?id=" + filmId);
        } else {
            response.sendRedirect("/fm/authorize");
        }

    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
