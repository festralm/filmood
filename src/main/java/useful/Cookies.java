package useful;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static java.util.Objects.nonNull;

public class Cookies {
    public static boolean checkCookie(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            javax.servlet.http.Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (javax.servlet.http.Cookie cookie : cookies) {
                    String cookieName = cookie.getName();
                    if (cookieName.equals("user_id")) {
                        session.setAttribute("user_id", Integer.parseInt(cookie.getValue()));
                        return true;
                    }
                }
            }
        }
        return false;

    }
    public static Cookie getCookie(HttpServletRequest request, String name) {
        HttpSession session = request.getSession();
        if (session != null) {
            javax.servlet.http.Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (javax.servlet.http.Cookie cookie : cookies) {
                    String cookieName = cookie.getName();
                    if (cookieName.equals(name)) {
                        return cookie;
                    }
                }
            }
        }
        return null;

    }
}
