package dao.interfaces;

import dto.*;

public interface FilmDao {

    Comment[] getCommentsByFilmId(int id);

    String[] getGenresByFilmId(int id);

    String[] getWordsByFilmId(int id);

    String[] getCountriesByFilmId(int id);

    Film[] getAllFilms();

    Film getFilmByWord(String inputWord, int userId);
}