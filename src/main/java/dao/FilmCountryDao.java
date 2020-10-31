package dao;

import dto.*;

public interface FilmCountryDao {
    Country[] getCountriesByFilmId(int userId);
}