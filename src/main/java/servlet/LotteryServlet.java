package servlet;

import dto.*;
import org.json.JSONObject;
import service.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/lottery")
public class LotteryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FilmService filmService = new FilmService();
        Film film = filmService.getRandomFilm();
        filmService.addCountries(film);
        filmService.addGenres(film);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", film.getId());
        jsonObject.put("name", film.getName());
        jsonObject.put("photo_path", film.getPhotoPath());
        if (film.getFinishYear() == -1) {
            jsonObject.put("year", film.getStartYear());
        } else {
            jsonObject.put("year", film.getStartYear() + " - " + film.getFinishYear());
        }
        StringBuilder countriesStringBuilder = new StringBuilder();
        for (String country : film.getCountries()) {
            countriesStringBuilder.append(", ").append(country);
        }
        jsonObject.put("countries", countriesStringBuilder);

        StringBuilder genresStringBuilder = new StringBuilder();
        for (String genre : film.getGenres()) {
            genresStringBuilder.append(", ").append(genre);
        }

        if (genresStringBuilder.indexOf(",") == 0) {
            genresStringBuilder.replace(0, 2, "");
        }
        jsonObject.put("genres", genresStringBuilder);

        response.getWriter().write(jsonObject.toString());
    }
}
