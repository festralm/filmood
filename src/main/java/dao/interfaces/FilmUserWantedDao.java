package dao.interfaces;

import dto.*;

public interface FilmUserWantedDao {
    boolean isColumnExist(int userId, int sightId);

    boolean addFilmUserByIds(int filmId, int userId);
}