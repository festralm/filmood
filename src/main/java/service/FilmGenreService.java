package service;

import dao.*;
import dto.*;

public class FilmGenreService {
    private FilmGenreDao filmGenreDao = new FilmGenreDaoMySql();

    public Genre[] getGenresByFilmId(int id) {

        return filmGenreDao.getGenresByFilmId(id);
    }
}
