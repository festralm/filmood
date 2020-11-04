package useful;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static java.util.Objects.nonNull;

public class CheckSession {
    public static boolean check(HttpSession session, HttpServletRequest request) {
        if (nonNull(session)) {
            Object userId = session.getAttribute("user_id");
            if (nonNull(userId)) {
                if (checkCookie(request, (int) userId)) {
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

    private static boolean checkCookie(HttpServletRequest request, int userId) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (cookieName.equals("userId") &&
                    Integer.parseInt(cookie.getValue()) == userId) {
                return true;
            }
        }
        return false;
    }
}
