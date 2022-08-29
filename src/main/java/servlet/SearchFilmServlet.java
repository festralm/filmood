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
import java.net.URLEncoder;
import java.util.Arrays;

@WebServlet("/search")
public class SearchFilmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String inputWord = request.getParameter("word");
        if (inputWord.charAt(0) == '#') {
            inputWord = inputWord.substring(1);
        }
        response.sendRedirect("/fm/associations?word=" + URLEncoder.encode(inputWord, "UTF-8") + "#area2");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
