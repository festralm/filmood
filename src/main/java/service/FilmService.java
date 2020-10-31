package service;

import dao.*;
import dto.*;

public class FilmService {
    private FilmDao filmDao = new FilmDaoMySql();

    public Film getFilmById(int id) {
        return filmDao.getFilmById(id);
    }

    public Film getRandomFilm() {
        return filmDao.getRandomFilm();
    }
}
