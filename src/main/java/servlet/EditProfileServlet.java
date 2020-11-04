//package servlet;
//
//import exception.CouldntAddData;
//import exception.DataIsEmpty;
//import service.UserService;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Date;
//
//@WebServlet("/edit_profile")
//public class EditProfileServlet extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        final String fullname = request.getParameter("fullname");
//        final String username = request.getParameter("username");
//        final String email = request.getParameter("email");
//        final String birthdate = request.getParameter("birthdate");
//        final char[] password = request.getParameter("password").toCharArray();
//
//        UserService userService = new UserService();
//        try {
//            userService.editUser((String) request.getSession().getAttribute("username"), username,
//                    password, email, birthdate.equals("") ? null : Date.valueOf(birthdate), fullname);
//        } catch (DataIsEmpty | CouldntAddData dataIsEmpty) {
//            dataIsEmpty.printStackTrace();
//        }
//        response.sendRedirect("profile");
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
//}
