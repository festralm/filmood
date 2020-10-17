package useful;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static java.util.Objects.nonNull;

public class CheckSession {
    public static boolean check(HttpSession httpSession, HttpServletRequest request) {
        if (nonNull(httpSession)) {
            Object login = httpSession.getAttribute("username");
            if (nonNull(login)) {
                if (checkCookie(request, (String) login)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    private static boolean checkCookie(HttpServletRequest request, String login) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (cookieName.equals("user") && cookie.getValue().equals(login)) {
                return true;
            }
        }
        return false;
    }
}
