package dao.implementation;

import dao.MySqlConnection;
import dao.interfaces.FilmWordDao;
import dto.*;
import service.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FilmWordDaoMySql implements FilmWordDao {
    private final MySqlConnection connection = new MySqlConnection();

    @Override
    public boolean addWordUserByIds(int filmId, String word) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select film_word.id, word.id, film_word.count, word.count " +
                    "from film_word " +
                    "inner join word " +
                    "on film_word.word_id = word.id " +
                    "where film.id = ? and word.name = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, filmId);
                preparedStatement.setString(2, word);

                ResultSet resultSet = preparedStatement.executeQuery();

                int filmWordId = resultSet.getInt(1);
                int wordId = resultSet.getInt(2);
                int filmWordCount = resultSet.getInt(3);
                int wordCount = resultSet.getInt(4);

                if (resultSet.next()) {
                    String sql1 = "update film_word " +
                            "set count = ? " +
                            "where id = ?";
                    PreparedStatement preparedStatement1 = con.prepareStatement(sql1);
                    preparedStatement1.setInt(1, filmWordCount + 1);
                    preparedStatement1.setInt(2, filmWordId);

                    preparedStatement1.executeQuery();
                    String sql2 = "update word " +
                            "set count = ? " +
                            "where id = ?";
                    PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
                    preparedStatement2.setInt(1, wordCount + 1);
                    preparedStatement2.setInt(2, wordId);

                    preparedStatement2.executeQuery();

                } else {
                    String sql3 = "insert into film_word " +
                            "values(?, ?, 1)";
                    PreparedStatement preparedStatement3 = con.prepareStatement(sql3);
                    preparedStatement3.setInt(1, filmId);
                    preparedStatement3.setString(2, word);

                    preparedStatement3.executeQuery();
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return false;
    }
}
