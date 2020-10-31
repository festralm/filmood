package dao;

import dto.*;

import java.sql.*;

public class WordDaoMySql implements WordDao {
    private final MySqlConnection connection = new MySqlConnection();

    @Override
    public Word getWordByWord(String word) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, word, count " +
                    "from filmood.word " +
                    "where word = ? ";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setString(1, word);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    int count = resultSet.getInt(3);

                    return new Word(id, word, count);
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
    public Word getWordByWordId(int id) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, word, count " +
                    "from filmood.word " +
                    "where id = ? ";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, id);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String word = resultSet.getString(2);
                    int count = resultSet.getInt(3);

                    return new Word(id, word, count);
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
