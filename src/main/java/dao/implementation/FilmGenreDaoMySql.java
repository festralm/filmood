//package dao;
//
//import dto.*;
//import service.*;
//
//import java.sql.*;
//import java.util.*;
//
//public class FilmGenreDaoMySql implements FilmGenreDao {
//    private final MySqlConnection connection = new MySqlConnection();
//
//    @Override
//    public Genre[] getGenresByFilmId(int filmId) {
//        List<Integer> genreIds = new ArrayList<>();
//        try (Connection con = connection.getNewConnection()) {
//            String sql = "select id, film_id, genre_id " +
//                    "from filmood.film_genre " +
//                    "where film_id = ? ";
//            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
//                preparedStatement.setInt(1, filmId);
//
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                while (resultSet.next()) {
//                    int genreId = resultSet.getInt(3);
//
//                    genreIds.add(genreId);
//                }
//            }
//        }
//        catch (SQLException exception) {
//            System.out.println("Something went wrong...");
//            exception.printStackTrace();
//        }
//        if (genreIds.isEmpty()) {
//            return null;
//        } else {
//            Genre[] res = new Genre[genreIds.size()];
//            GenreService genreService = new GenreService();
//
//            for (int i = 0; i < genreIds.size(); i++) {
//                res[i] = genreService.getGenreByGenreId(genreIds.get(i));
//            }
//
//            return res;
//        }
//
//    }
//}
