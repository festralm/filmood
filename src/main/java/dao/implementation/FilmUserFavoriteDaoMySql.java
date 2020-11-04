//package dao;
//
//import dto.*;
//
//import java.sql.*;
//import java.util.*;
//
//public class FilmUserFavoriteDaoMySql implements FilmUserFavoriteDao {
//    private final MySqlConnection connection = new MySqlConnection();
//
//    @Override
//    public Film[] getFilmsByUserId(int userId, int count) {
//        List<Integer> filmIds = new ArrayList<>();
//        try (Connection con = connection.getNewConnection()) {
//            String sql = "select id, film_id, user_id " +
//                    "from filmood.film_user_favorite " +
//                    "where user_id = ? " +
//                    "order by rand() " +
//                    "limit ?";
//            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
//                preparedStatement.setInt(1, userId);
//                preparedStatement.setInt(2, count);
//
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                while (resultSet.next()) {
//                    int film_id = resultSet.getInt(2);
//
//                    filmIds.add(film_id);
//                }
//            }
//        }
//        catch (SQLException exception) {
//            System.out.println("Something went wrong...");
//            exception.printStackTrace();
//        }
//        if (filmIds.isEmpty()) {
//            return null;
//        } else {
//            return Film.getFilmsByIds(filmIds);
//        }
//
//    }
//
//    @Override
//    public Film[] getFilmsByUserId(int userId) {
//        List<Integer> filmIds = new ArrayList<>();
//        try (Connection con = connection.getNewConnection()) {
//            String sql = "select id, film_id, user_id " +
//                    "from filmood.film_user_favorite " +
//                    "where user_id = ? ";
//            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
//                preparedStatement.setInt(1, userId);
//
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                while (resultSet.next()) {
//                    int film_id = resultSet.getInt(2);
//
//                    filmIds.add(film_id);
//                }
//            }
//        }
//        catch (SQLException exception) {
//            System.out.println("Something went wrong...");
//            exception.printStackTrace();
//        }
//        if (filmIds.isEmpty()) {
//            return null;
//        } else {
//            return Film.getFilmsByIds(filmIds);
//        }
//
//    }
//}
