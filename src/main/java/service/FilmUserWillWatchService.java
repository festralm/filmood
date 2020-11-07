package service;

import dao.interfaces.*;
import dao.implementation.*;
import exception.IncorrectFilmIdException;
import exception.IncorrectUserIdException;
import lombok.SneakyThrows;

public class FilmUserWillWatchService {
    private FilmUserWillWatchDao filmUserWillWatchDao = new FilmUserWillWatchDaoMySql();

    @SneakyThrows
    public boolean addFilmUserByIds(int filmId, int userId) {
        if (filmId < 1) {
            throw new IncorrectFilmIdException();
        }
        if (userId < 1) {
            throw new IncorrectUserIdException();
        }
        return filmUserWillWatchDao.addFilmUserByIds(filmId, userId);
    }
}
