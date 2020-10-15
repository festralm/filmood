package useful;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogIn {
    public static void logIn(String username, String password,
                             ServletRequest req, ServletResponse resp,
                             HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("username", username);
        request.getSession().setAttribute("password", password);
        request.getSession().setAttribute("check_password", true);

        Cookie cookie = new Cookie("user", username + password);
        if (req.getParameter("check") == null) {
            cookie.setMaxAge(-1);
        } else {
            cookie.setMaxAge(60 * 60 * 2);
        }
        response.addCookie(cookie);
        response.sendRedirect("/fm/");
        //req.getRequestDispatcher("/main").forward(req, resp);
    }
}
