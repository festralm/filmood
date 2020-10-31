package service;

import dao.*;
import dto.*;

public class FilmUserWatchedService {
    private FilmUserWatchedDao filmUserWatchedDao = new FilmUserWatchedDaoMySql();


    public Film[] getFilmsByUserId(int id) {
        return filmUserWatchedDao.getFilmsByUserId(id);
    }
}
