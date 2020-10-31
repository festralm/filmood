package service;

import dao.*;
import dto.*;

public class FilmUserWillWatchService {
    private FilmUserWillWatchDao filmUserWillWatchDao = new FilmUserWillWatchDaoMySql();


    public Film[] getFilmsByUserId(int id) {
        return filmUserWillWatchDao.getFilmsByUserId(id);
    }
    public boolean addFilmAndUser(int user_id, int film_id) {
        return filmUserWillWatchDao.addFilmAndUser(user_id, film_id);
    }
}
