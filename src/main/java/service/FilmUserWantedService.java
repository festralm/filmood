package service;

import dao.implementation.FilmUserWantedDaoMySql;
import dao.interfaces.FilmUserWantedDao;
import exception.IncorrectFilmIdException;
import exception.IncorrectUserIdException;
import lombok.SneakyThrows;

public class FilmUserWantedService {
    private FilmUserWantedDao filmUserWantedDao = new FilmUserWantedDaoMySql();

    @SneakyThrows
    public boolean addFilmUserByIds(int filmId, int userId) {
        if (filmId < 1) {
            throw new IncorrectFilmIdException();
        }
        if (userId < 1) {
            throw new IncorrectUserIdException();
        }
        return filmUserWantedDao.addFilmUserByIds(filmId, userId);
    }
}
