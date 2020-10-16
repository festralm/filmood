package servlet;

import dao.UserDao;
import dto.User;
import useful.CheckSession;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        final HttpSession session = request.getSession();
//        ServletContext servletContext = getServletContext();
//        if (CheckSession.check(session, request)) {
//            servletContext.getRequestDispatcher("/pages/helloPageForUser.html").forward(request, response);
//        } else {
//            servletContext.getRequestDispatcher("/pages/helloPage.html").forward(request, response);
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        if (CheckSession.check(session, request)) {
            servletContext.getRequestDispatcher("/pages/Account.html").forward(request, response);

            final UserDao userDao = (UserDao) request.getServletContext().getAttribute("userDao");

            String username = (String) session.getAttribute("username");
            User user = userDao.getUserByUsername(username);



        } else {
            response.sendRedirect("/authorize");
        }
    }
}
