package dao.implementation;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import dao.MySqlConnection;
import dao.interfaces.WordDao;
import dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WordDaoMySql implements WordDao {
    private final MySqlConnection connection = new MySqlConnection();

    @Override
    public String[] getAllWords() {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select name " +
                    "from filmood.word " +
                    "order by count";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                List<String> words = new ArrayList<>();
                while (resultSet.next()) {
                    String word = resultSet.getString(1);

                    if (word != null) {
                        words.add(word);
                    }
                }
                return words.toArray(new String[]{});
            }
        } catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isWordExist(String name) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id " +
                    "from word " +
                    "where name = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, name);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean incrementCount(int id, int count) {

        try (Connection con = connection.getNewConnection()) {
            String sql = "update word " +
                    "set count = ? " +
                    "where id = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setInt(1, count + 1);
                preparedStatement.setInt(2, id);

                preparedStatement.executeUpdate();
            }
        }catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public Word getWordByName(String name) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, count " +
                    "from word " +
                    "where name = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, name);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    int count = resultSet.getInt(2);

                    return new Word(id, name, count);
                }
            }
        } catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }
}
