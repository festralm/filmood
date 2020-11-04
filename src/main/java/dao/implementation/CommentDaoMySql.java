//package dao.implementation;
//
//import dao.MySqlConnection;
//import dao.interfaces.CommentDao;
//import dto.Comment;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CommentDaoMySql implements CommentDao {
//    private final MySqlConnection connection = new MySqlConnection();
//    @Override
//    public boolean addComment(Comment comment) {
//        try (Connection con = connection.getNewConnection()) {
//            String sql = "insert into filmood.comment " +
//                    "(film_id, user_id, description) " +
//                    "values (?, ?, ?)";
//            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
//                preparedStatement.setInt(1, comment.getFilmId());
//                preparedStatement.setInt(2, comment.getUserId());
//                preparedStatement.setString(3, comment.getDescription());
//
//                preparedStatement.executeUpdate();
//                return true;
//            }
//        }
//        catch (SQLException exception) {
//            System.out.println("Something went wrong...");
//            exception.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public Comment[] geAllComments() {
//        List<Comment> comments = new ArrayList<>();
//        try (Connection con = connection.getNewConnection()) {
//            String sql = "select id, film_id, user_id, description, datetime " +
//                    "from filmood.comment ";
//            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
//
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                while (resultSet.next()) {
//                    int id = resultSet.getInt(1);
//                    int film_id = resultSet.getInt(2);
//                    int user_id = resultSet.getInt(3);
//                    String description = resultSet.getString(4);
//                    Date datetime = resultSet.getDate(5);
//
//                    comments.add(new Comment(id, film_id, user_id, description, datetime));
//                    return comments.toArray(new Comment[0]);
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
