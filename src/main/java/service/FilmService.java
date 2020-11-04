package service;

import dao.implementation.*;
import dao.interfaces.*;
import dto.*;

public class FilmService {
    private FilmDao filmDao = new FilmDaoMySql();


    public Comment[] getCommentsByFilmId(int id) {
        return filmDao.getCommentsByFilmId(id);
    }

    public String[] getGenresByFilmId(int id) {
        return filmDao.getGenresByFilmId(id);
    }

    public String[] getWordsByFilmId(int id) {
        return filmDao.getWordsByFilmId(id);
    }

    public String[] getCountriesByFilmId(int id) {
        return filmDao.getCountriesByFilmId(id);
    }

    public Film[] getAllFilms() {
        return filmDao.getAllFilms();
    }

    public Film getFilmByWord(String inputWord, int userId) {
        return filmDao.getFilmByWord(inputWord
                .replaceAll(" ", "")
                .toLowerCase(), userId);
    }
}
