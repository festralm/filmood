package servlet;

import service.UserService;
import service.WordService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/check-word")
public class CheckWordInputFormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String word = request.getParameter("word");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        WordService wordService = new WordService();

        if (word != null) {
            if (wordService.isWordExist(word)) {
                response.getWriter().write("true");
            } else {
                response.getWriter().write("false");
            }
        }
    }
}
