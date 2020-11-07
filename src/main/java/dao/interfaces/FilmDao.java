package dao.interfaces;

import dto.*;

public interface FilmDao {
    String[] getGenresByFilmId(int id);

    Film[] getAllFilms();
    Film[] getFilmsByGenre(String genre);

    Film getFilmByWordAndUserId(String inputWord, int userId);
    Film getFilmByWord(String inputWord);

    Film getFilmById(int filmId);

    String[] getCountriesByFilmId(int id);

    Comment[] geCommentsByFilmId(int id);

    Film getRandomFilm();
}