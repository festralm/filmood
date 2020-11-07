package service;


import dao.implementation.FilmWordDaoMySql;
import dao.interfaces.*;
import exception.IncorrectFilmIdException;
import exception.IncorrectWordException;
import lombok.SneakyThrows;

public class FilmWordService {
    private FilmWordDao filmWordDao = new FilmWordDaoMySql();

    @SneakyThrows
    public boolean addWordUserByIds(int filmId, String word) {
        if (filmId < 1) {
            throw new IncorrectFilmIdException();
        }
        if (word == null) {
            throw new IncorrectWordException();
        }
        return filmWordDao.addWordUserByIds(filmId, word
                .replaceAll("/  +/g", " ")
                .trim()
                .toLowerCase());
    }
}
