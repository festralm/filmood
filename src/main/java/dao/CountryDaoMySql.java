package dao;

import dto.*;

import java.sql.*;

public class CountryDaoMySql implements CountryDao {
    private final MySqlConnection connection = new MySqlConnection();

    @Override
    public Country getCountryByCountryId(int id) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, country " +
                    "from filmood.country " +
                    "where id = ? ";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String country = resultSet.getString(2);

                    return new Country(id, country);
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
