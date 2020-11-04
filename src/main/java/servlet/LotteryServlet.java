//package servlet;
//
//import dto.*;
//import service.*;
//
//import javax.servlet.*;
//import javax.servlet.annotation.*;
//import javax.servlet.http.*;
//import java.io.*;
//
//@WebServlet("/lottery")
//public class LotteryServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//
//        FilmService filmService = new FilmService();
//        Film film = filmService.getRandomFilm();
//
//        session.setAttribute("film", film);
//        response.sendRedirect("/fm/");
//    }
//}
