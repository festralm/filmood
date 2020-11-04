package dao.interfaces;

import dto.*;

public interface FilmWordDao {

    boolean addWordUserByIds(int filmId, String word);
}