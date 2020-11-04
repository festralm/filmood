package dao.interfaces;

import dto.*;

public interface FilmUserWillWatchDao {
    boolean addFilmUserByIds(int filmId, int userId);
}