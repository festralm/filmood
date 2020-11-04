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
        List<String> words = new ArrayList<>();
        try (Connection con = connection.getNewConnection()) {
            String sql = "select name " +
                    "from filmood.word " +
                    "order by count";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    String word = resultSet.getString(1);

                    if (word != null) {
                        words.add(word);
                    }
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return words.toArray(new String[] {});
    }

    @Override
    public boolean isWordExist(String name) {
        try (Connection con = connection.getNewConnection()) {
            String sql1 = "select id " +
                    "from word " +
                    "where name = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql1)){
                preparedStatement.setString(1, name);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return true;
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
