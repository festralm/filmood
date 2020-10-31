package dao;

import dto.*;

public interface FilmUserWillWatchDao {
    Film[] getFilmsByUserId(int userId);

    boolean addFilmAndUser(int user_id, int film_id);
}