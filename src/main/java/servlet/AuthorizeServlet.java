package servlet;

import useful.Cookies;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authorize")
public class AuthorizeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (Cookies.checkCookie(request)) {
            response.sendRedirect("/fm");
        } else {
            Cookie cookie = Cookies.getCookie(request, "check_password");
            if (cookie != null) {
                if (cookie.getValue().equals("200px")) {
                    request.setAttribute("check_password", "200px");
                } else {
                    request.setAttribute("check_password", "0");
                }
                Cookie cookie1 = new Cookie("check_password", "");
                cookie1.setMaxAge(0);
                response.addCookie(cookie1);
            } else {
                request.setAttribute("check_password", "0");
            }
            request.setAttribute("button", "Войти");
            String path = "/SignIn.jsp";
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        }
    }
}
