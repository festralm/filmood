package useful;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogIn {
    public static void logIn(int userId, HttpServletRequest request,
                             HttpServletResponse response) throws IOException {

        request.getSession().setAttribute("user_id", userId);

        Cookie cookie = new Cookie("user_id", Integer.toString(userId));
        if (request.getParameter("remember_me") == null) {
            cookie.setMaxAge(-1);
        } else {
            cookie.setMaxAge(60 * 60 * 24);
        }
        response.addCookie(cookie);
        response.sendRedirect("/fm/");
    }
}
