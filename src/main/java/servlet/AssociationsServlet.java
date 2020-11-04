package servlet;

import service.WordService;
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

@WebServlet("/associations")
public class AssociationsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/Associations.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        HttpSession session = request.getSession();

        WordService wordService = new WordService();
        response.setCharacterEncoding("UTF-8");

        if (CheckSession.check(session, request)) {
            session.setAttribute("button", "Выйти");
        } else {
            session.setAttribute("button", "Войти");
        }
        String[] words = wordService.getAllWords();

        session.setAttribute("words", words);
        request.setCharacterEncoding("UTF-8");
        requestDispatcher.forward(request, response);
    }
}
