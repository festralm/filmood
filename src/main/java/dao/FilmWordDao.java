package dao;

import dto.*;

public interface FilmWordDao {
    Film getFilmByWordId(int wordId);
    Word[] getWordsByFilmId(int userId);

}