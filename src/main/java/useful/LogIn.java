package useful;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogIn {
    public static void logIn(String username, HttpServletRequest request,
                             HttpServletResponse response) throws IOException {

        request.getSession().setAttribute("username", username);

        Cookie cookie = new Cookie("user", username);
        if (request.getParameter("check") == null) {
            cookie.setMaxAge(-1);
        } else {
            cookie.setMaxAge(60 * 60 * 24);
        }
        response.addCookie(cookie);
        response.sendRedirect("/fm/");
        //req.getRequestDispatcher("/main").forward(req, resp);
    }
}
