package service;

import dao.interfaces.*;
import dao.implementation.*;

public class FilmUserWillWatchService {
    private FilmUserWillWatchDao filmUserWillWatchDao = new FilmUserWillWatchDaoMySql();

    public boolean addFilmUserByIds(int filmId, int userId) {
        return filmUserWillWatchDao.addFilmUserByIds(filmId, userId);
    }
}
