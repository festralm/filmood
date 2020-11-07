package dao.implementation;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import dao.MySqlConnection;
import dao.interfaces.FilmDao;
import dao.interfaces.GenreDao;
import dto.Comment;
import dto.Film;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoMySql implements GenreDao {
    private final MySqlConnection connection = new MySqlConnection();
    @Override
    public String[] getAllGenres() {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select name " +
                    "from filmood.genre " +
                    "order by rand() " +
                    "limit 4 ";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                ResultSet resultSet = preparedStatement.executeQuery();

                List<String> genres = new ArrayList<>();
                while (resultSet.next()) {

                    String genre = resultSet.getString(1);
                    if (genre != null) {
                        genres.add(genre);
                    }
                }
                return genres.toArray(new String[] {});
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }
}
