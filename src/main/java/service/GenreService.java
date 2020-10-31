package service;

import dao.*;
import dto.*;

public class GenreService {
    private GenreDao genreDao = new GenreDaoMySql();

    public Genre getGenreByGenreId(int id) {

        return genreDao.getGenreByGenreId(id);
    }

}
