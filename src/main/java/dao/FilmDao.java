package dao;

import dto.*;

public interface FilmDao {
    Film getFilmById(int id);

    Film getRandomFilm();
}