package dao;

import dto.*;
import service.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmDaoMySql implements FilmDao {
    private final MySqlConnection connection = new MySqlConnection();

    @Override
    public Film getFilmById(int id) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, name, photo_path, year, description " +
                    "from filmood.film " +
                    "where id = ? ";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String name = resultSet.getString(2);
                    String photoPath = resultSet.getString(3);
                    int year = resultSet.getInt(4);
                    String description = resultSet.getString(5);

                    FilmCountryService filmCountryService = new FilmCountryService();
                    Country[] countries = filmCountryService.getCountriesByFilmId(id);

                    FilmGenreService filmGenreService = new FilmGenreService();
                    Genre[] genres = filmGenreService.getGenresByFilmId(id);

                    FilmWordService filmWordService = new FilmWordService();
                    Word[] words = filmWordService.getWordsByFilmId(id);

                    return new Film(id, name, photoPath, year, description,
                            countries, genres, words);
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Film getRandomFilm() {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, name, photo_path, year, description " +
                    "from filmood.film " +
                    "order by rand() " +
                    "limit 1";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);

                    return getFilmById(id);
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }
}
