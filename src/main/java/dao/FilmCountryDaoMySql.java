package dao;

import dto.*;
import service.*;

import java.sql.*;
import java.util.*;

public class FilmCountryDaoMySql implements FilmCountryDao {
    private final MySqlConnection connection = new MySqlConnection();

    @Override
    public Country[] getCountriesByFilmId(int filmId) {
        List<Country> countries = new ArrayList<>();
        CountryService countryService = new CountryService();
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, film_id, country_id " +
                    "from filmood.film_country " +
                    "where film_id = ? ";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, filmId);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int countryId = resultSet.getInt(3);
                    countries.add(countryService.getCountryByCountryId(countryId));
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
