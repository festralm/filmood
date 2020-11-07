package servlet;

import dto.*;
import exception.*;
import liquibase.pro.packaged.F;
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
public class SearchFilmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String inputWord = request.getParameter("search");
        if (inputWord.charAt(0) == '#') {
            inputWord = inputWord.substring(1);
        }
        response.sendRedirect("/fm/associations?word=" + inputWord);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
