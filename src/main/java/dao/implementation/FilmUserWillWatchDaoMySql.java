package dao.implementation;

import dao.*;
import dao.interfaces.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmUserWillWatchDaoMySql implements FilmUserWillWatchDao {
    private final MySqlConnection connection = new MySqlConnection();
    @Override
    public boolean isColumnExist(int userId, int filmId) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id " +
                    "from filmood.film_user_will_watch " +
                    "where user_id = ? and film_id = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, filmId);

                ResultSet resultSet = preparedStatement.executeQuery();

                return resultSet.next();
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean addFilmUserByIds(int filmId, int userId) {
        boolean alreadyAdded = isColumnExist(userId, filmId);
        if (!alreadyAdded) {
            try (Connection con = connection.getNewConnection()) {
                String sql = "insert into filmood.film_user_will_watch " +
                        "(film_id, user_id) " +
                        "values (?, ?)";
                try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                    preparedStatement.setInt(1, filmId);
                    preparedStatement.setInt(2, userId);

                    preparedStatement.executeUpdate();

                    return true;
                }
            } catch (SQLException exception) {
                System.out.println("Something went wrong...");
                exception.printStackTrace();
            }
        }
        return false;
    }
}
