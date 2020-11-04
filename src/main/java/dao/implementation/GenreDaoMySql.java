//package dao.implementation;
//
//import dao.MySqlConnection;
//import dao.interfaces.GenreDao;
//import dto.*;
//
//import java.sql.*;
//
//public class GenreDaoMySql implements GenreDao {
//    private final MySqlConnection connection = new MySqlConnection();
//
//    @Override
//    public Genre getGenreByGenreId(int id) {
//        try (Connection con = connection.getNewConnection()) {
//            String sql = "select id, name " +
//                    "from filmood.genre " +
//                    "where id = ? ";
//            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
//                preparedStatement.setInt(1, id);
//
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                if (resultSet.next()) {
//                    String genre = resultSet.getString(2);
//
//                    return new Genre(id, genre);
//                }
//            }
//        }
//        catch (SQLException exception) {
//            System.out.println("Something went wrong...");
//            exception.printStackTrace();
//        }
//        return null;
//    }
//}
