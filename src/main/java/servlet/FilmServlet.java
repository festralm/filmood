//package servlet;
//
//import dto.Film;
//import dto.User;
//import lombok.SneakyThrows;
//import service.CommentService;
//import service.FilmUserWatchedService;
//import service.UserService;
//import useful.CheckSession;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebServlet("/film")
//public class FilmServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    @SneakyThrows
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        final HttpSession session = request.getSession();
//        ServletContext servletContext = getServletContext();
//        final String username = (String) session.getAttribute("username");
//        final String description = request.getParameter("comment");;
//        final Film film = (Film) session.getAttribute("film");
//
//        String path = "/Film.jsp";
//        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
//        if (CheckSession.check(session, request)) {
//            session.setAttribute("button", "Выйти");
//            if (description != null && !description.equals("")) {
//                CommentService commentService = new CommentService();
//                UserService userService = new UserService();
//                User user = userService.getUserByUsername(username);
//
//                session.setAttribute("button", "Выйти");
//                commentService.addComment(film.getId(), user.getId(), description );
//            }
//
//            requestDispatcher.forward(request, response);
//        } else {
//            session.setAttribute("button", "Войти");
//            if (description != null && !description.equals("")) {
//                response.sendRedirect("/fm/authorize");
//            }
//            else {
//                requestDispatcher.forward(request, response);
//            }
//        }
//    }
//}
