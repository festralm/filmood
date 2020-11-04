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
    public boolean addFilmUserByIds(int filmId, int userId) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "insert into film_user_will_watch " +
                    "values(?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, filmId);
                preparedStatement.setInt(2, userId);

                preparedStatement.executeQuery();

                return true;
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return false;
    }
}
