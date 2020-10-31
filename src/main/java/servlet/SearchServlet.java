package servlet;

import dto.*;
import exception.*;
import service.*;
import useful.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputWord = request.getParameter("search");

        if (inputWord != null) {
            if (inputWord.charAt(0) == '#') {
                inputWord = inputWord.substring(1);
            }
            HttpSession session = request.getSession();

            FilmWordService filmWordService = new FilmWordService();
            Film film = filmWordService.getFilmByWord(inputWord);

            session.setAttribute("film", film);
            response.sendRedirect("associations");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
