package dao.interfaces;

import dto.*;

public interface FilmUserWillWatchDao {
    boolean isColumnExist(int userId, int sightId);

    boolean addFilmUserByIds(int filmId, int userId);
}