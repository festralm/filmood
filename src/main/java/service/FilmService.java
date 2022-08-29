package service;

import dao.implementation.*;
import dao.interfaces.*;
import dto.*;
import exception.*;
import lombok.SneakyThrows;


public class FilmService {
    private FilmDao filmDao = new FilmDaoMySql();

    @SneakyThrows
    public String[] getGenresByFilmId(int id) {
        if (id < 1) {
            throw new IncorrectFilmIdException();
        }

        String[] genres = filmDao.getGenresByFilmId(id);

        if (genres == null) {
            throw new IncorrectFilmException();
        }

        return genres;
    }

    @SneakyThrows
    public Film[] getAllFilms() {
        Film[] films = filmDao.getAllFilms();
        if (films == null) {
            throw new IncorrectFilmException();
        }

        return films;
    }
    @SneakyThrows
    public Film[] geFilmsByGenre(String genre) {
        if (genre == null) {
            throw new IncorrectGenresException();
        }
        Film[] films = filmDao.getFilmsByGenre(genre);
        if (films == null) {
            throw new IncorrectFilmException();
        }

        return films;
    }

    @SneakyThrows
    public Film getFilmByWordAndUserId(String inputWord, int userId) {
        if (inputWord == null) {
            throw new IncorrectWordException();
        }
        if (userId < 1) {
            throw new IncorrectUserIdException();
        }
        Film film = filmDao.getFilmByWordAndUserId(inputWord
                .replaceAll("/  +/g", " ")
                .trim()
                .toLowerCase(), userId);
        return film;
    }

    @SneakyThrows
    public Film getFilmByWord(String inputWord) {
        if (inputWord == null) {
            throw new IncorrectWordException();
        }
        Film film = filmDao.getFilmByWord(inputWord
                .replaceAll("/  +/g", " ")
                .replace("/[^A-Za-zА-Яа-яЁё]/g", "")
                .trim()
                .toLowerCase());
        return film;
    }

    @SneakyThrows
    public Film getFilmById(int filmId) {
        if (filmId < 1) {
            throw new IncorrectFilmIdException();
        }

        Film film = filmDao.getFilmById(filmId);

        if (film == null) {
            throw new IncorrectFilmException();
        }
        return film;
    }

    @SneakyThrows
    public void addCountries(Film film) {
        if (film == null) {
            throw new IncorrectFilmIdException();
        }
        String[] countries = filmDao.getCountriesByFilmId(film.getId());

        if (countries == null) {
            throw new IncorrectCountriesException();
        }

        film.setCountries(countries);
    }

    @SneakyThrows
    public void addGenres(Film film) {
        if (film == null) {
            throw new IncorrectFilmIdException();
        }
        String[] genres = filmDao.getGenresByFilmId(film.getId());

        if (genres == null) {
            throw new IncorrectGenresException();
        }

        film.setGenres(genres);
    }

    @SneakyThrows
    public void addComments(Film film) {
        if (film == null) {
            throw new IncorrectFilmIdException();
        }
        Comment[] comments = filmDao.geCommentsByFilmId(film.getId());

        if (comments == null) {
            throw new IncorrectGenresException();
        }

        film.setComments(comments);
    }

    @SneakyThrows
    public Film getRandomFilm() {
        Film film = filmDao.getRandomFilm();
        if (film == null) {
            throw new IncorrectFilmException();
        }
        return film;
    }
}
