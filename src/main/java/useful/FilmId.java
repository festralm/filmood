package useful;

import exception.IncorrectFilmIdException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilmId {
    public static int getFilmIdForPopup(HttpServletRequest request, HttpServletResponse response) throws IncorrectFilmIdException {

        Object filmIdObj = request.getParameter("id");
        int filmId;
        if (filmIdObj == null) {
            Cookie cookie = Cookies.getCookie(request, "film_id");
            if (cookie == null) {
                throw new IncorrectFilmIdException();
            }
            filmId = Integer.parseInt(cookie.getValue());
            cookie.setMaxAge(0);
            response.addCookie(cookie);

        } else {
            filmId = Integer.parseInt(request.getParameter("id"));
        }
        return filmId;
    }
}
