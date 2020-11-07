package dao.interfaces;

import dto.*;

public interface FilmWordDao {
    FilmWord getFilmWordByFilmIdAndWordId(int filmIf, int wordId);

    boolean incrementCount(int filmId, int wordId);

    boolean addNewFilmWord(int filmId, int wordId);

    boolean addWordUserByIds(int filmId, String word);
}