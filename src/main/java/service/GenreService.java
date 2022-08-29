package service;

import dao.implementation.FilmDaoMySql;
import dao.implementation.GenreDaoMySql;
import dao.interfaces.FilmDao;
import dao.interfaces.GenreDao;
import dto.Comment;
import dto.Film;
import dto.Genre;
import exception.*;
import lombok.SneakyThrows;


public class GenreService {
    private GenreDao genreDao = new GenreDaoMySql();

    @SneakyThrows
    public String[] getAllGenres() {
        String[] genres = genreDao.getAllGenres();
        if (genres == null) {
            throw new IncorrectGenresException();
        }

        return genres;
    }
}
