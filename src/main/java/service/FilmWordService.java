package service;


import dao.implementation.FilmWordDaoMySql;
import dao.interfaces.*;

public class FilmWordService {
    private FilmWordDao filmWordDao = new FilmWordDaoMySql();

    public boolean addWordUserByIds(int filmId, String word) {
        return filmWordDao.addWordUserByIds(filmId, word
                .replaceAll(" ", "")
                .toLowerCase());
    }
}
