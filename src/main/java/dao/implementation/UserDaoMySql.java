package dao.implementation;

import dao.*;
import dao.interfaces.*;
import dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoMySql implements UserDao {
    private final MySqlConnection connection = new MySqlConnection();

    @Override
    public Film[] getWatchedFilmsByUserId(int userId) {
        try  {
            String sql = "select film.id, film.name, film.photo_path," +
                    "film.start_year, film.finish_year " +
                    "from filmood.user " +
                    "inner join film_user_watched " +
                    "on user.id = film_user_watched.user_id " +
                    "inner join filmood.film " +
                    "on film_user_watched.film_id = film.id" +
                    "where user.id = ?" +
                    "order by film.name";
            return getFilmsBySql(sql, userId);
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Film[] getFavoriteFilmsByUserId(int userId) {
        try  {
            String sql = "select film.id, film.name, film.photo_path," +
                    "film.start_year, film.finish_year " +
                    "from filmood.user " +
                    "inner join film_user_favorite " +
                    "on user.id = film_user_favorite.user_id " +
                    "inner join filmood.film " +
                    "on film_user_favorite.film_id = film.id" +
                    "where user.id = ?" +
                    "order by film.name";
            return getFilmsBySql(sql, userId);
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Film[] getWillWatchFilmsByUserId(int userId) {
        try  {
            String sql = "select film.id, film.name, film.photo_path," +
                    "film.start_year, film.finish_year " +
                    "from filmood.user " +
                    "inner join film_user_will_watch " +
                    "on user.id = film_user_will_watch.user_id " +
                    "inner join filmood.film " +
                    "on film_user_will_watch.film_id = film.id" +
                    "where user.id = ?" +
                    "order by film.name";
            return getFilmsBySql(sql, userId);
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }

    private Film[] getFilmsBySql(String sql, int userId) throws SQLException {
        List<Film> films = new ArrayList<>();
        Connection con = connection.getNewConnection() ;
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Film film = new FilmDaoMySql().getFilmFromResultSet(resultSet);

            if (film != null) {
                films.add(film);
            }
        }
        return films.toArray(new Film[] {});
    }

    @Override
    public User getUserByUserId(int userId) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, username, password, email, birthdate, fullname," +
                    "photo_path " +
                    "where user.id = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, userId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String username = resultSet.getString(2);
                    String email = resultSet.getString(4);
                    Date birthdate = resultSet.getDate(5);
                    String fullname = resultSet.getString(6);
                    String photoPath = resultSet.getString(7);

                    return new User(id, username, email,
                            birthdate, fullname, photoPath);
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
    public int getRandomFriendByUserId(int userId) {
        try (Connection con = connection.getNewConnection()) {
            String sql1 = "select friend.user2_id " +
                    "from user " +
                    "inner join friend " +
                    "on user.id = friend.user1_id " +
                    "where user.id = ? and (status = 1 or status = 0) " +
                    "order by rand() " +
                    "limit 1";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql1)){
                preparedStatement.setInt(1, userId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
            String sql2 = "select friend.user1_id " +
                    "from user " +
                    "inner join friend " +
                    "on user.id = friend.user2_id " +
                    "where user.id = ? and (status = -1 or status = 0) " +
                    "order by rand() " +
                    "limit 1";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql2)){
                preparedStatement.setInt(1, userId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean isUserExist(String username) {
        try (Connection con = connection.getNewConnection()) {
            String sql1 = "select id " +
                    "from user " +
                    "where username = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql1)){
                preparedStatement.setString(1, username);

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

//    @Override
//    public User getUserById(int id) {
//        try (Connection con = connection.getNewConnection()) {
//            String sql = "select id, username, password, email, birthdate, fullname " +
//                    "from filmood.user " +
//                    "where id = ?";
//            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
//                preparedStatement.setInt(1, id);
//
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                if (resultSet.next()) {
//                    String username = resultSet.getString(2);
//                    String passwordHash = resultSet.getString(3);
//                    String email = resultSet.getString(4);
//                    Date birthdate = resultSet.getDate(5);
//                    String fullname = resultSet.getString(6);
//
//                    return new User(id, username, passwordHash, email, birthdate, fullname);
//                }
//            }
//        }
//        catch (SQLException exception) {
//            System.out.println("Something went wrong...");
//            exception.printStackTrace();
//        }
//        return null;
//    }

//    @Override
//    public User getUserByUsernamePassword(String username, String passwordHash) {
//        try (Connection con = connection.getNewConnection()) {
//            String sql = "select id, username, password, email, birthdate, fullname " +
//                    "from filmood.user " +
//                    "where (username = ? and password = ?)";
//            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
//                preparedStatement.setString(1, username);
//                preparedStatement.setString(2, passwordHash);
//
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                if (resultSet.next()) {
//                    int id = resultSet.getInt(1);
//                    String email = resultSet.getString(4);
//                    Date birthdate = resultSet.getDate(5);
//                    String fullname = resultSet.getString(6);
//
//                    return new User(id, username, passwordHash, email, birthdate, fullname);
//                }
//            }
//        }
//        catch (SQLException exception) {
//            System.out.println("Something went wrong...");
//            exception.printStackTrace();
//        }
//        return null;
//    }

//    public User getUserByUsername(String username) {
//        try (Connection con = connection.getNewConnection()) {
//            String sql = "select id, username, password, email, birthdate, fullname " +
//                    "from filmood.user " +
//                    "where (username = ?)";
//            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
//                preparedStatement.setString(1, username);
//
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                if (resultSet.next()) {
//                    int id = resultSet.getInt(1);
//                    String passwordHash = resultSet.getString(3);
//                    String email = resultSet.getString(4);
//                    Date birthdate = resultSet.getDate(5);
//                    String fullname = resultSet.getString(6);
//
//                    return new User(id, username, passwordHash, email, birthdate, fullname);
//                }
//            }
//        }
//        catch (SQLException exception) {
//            System.out.println("Something went wrong...");
//            exception.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public boolean addUser(User user) {
//        try (Connection con = connection.getNewConnection()) {
//            String sql = "insert into filmood.user " +
//                    "(username, password, email, birthdate, fullname) " +
//                    "values (?, ?, ?, ?, ?)";
//            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
//                preparedStatement.setString(1, user.getUsername());
//                preparedStatement.setString(2, user.getPassword());
//                preparedStatement.setString(3, user.getEmail());
//                preparedStatement.setDate(4, user.getBirthdate());
//                preparedStatement.setString(5, user.getFullname());
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
//    public boolean deleteUser(User user) {
//        return false;
//    }
//
//    @Override
//    public boolean isUserExist(String username, String passwordHash) {
//        return getUserByUsernamePassword(username, passwordHash) != null;
//    }
//
//    @Override
//    public boolean isUsernameExist(String username) {
//        try (Connection con = connection.getNewConnection()) {
//            String sql = "select id, username, password, email, birthdate, fullname " +
//                    "from filmood.user " +
//                    "where (username = ?)";
//            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
//                preparedStatement.setString(1, username);
//
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                if (resultSet.next()) {
//                    return true;
//                }
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
//    public boolean editUser(int id, User newUser) {
//        try (Connection con = connection.getNewConnection()) {
//            String sql = "update filmood.user set " +
//                    "username = ?, password = ?, email = ?, birthdate = ?, fullname = ? " +
//                    "where id = ?";
//            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
//                preparedStatement.setString(1, newUser.getUsername());
//                preparedStatement.setString(2, newUser.getPassword());
//                preparedStatement.setString(3, newUser.getEmail());
//                preparedStatement.setDate(4, newUser.getBirthdate());
//                preparedStatement.setString(5, newUser.getFullname());
//                preparedStatement.setInt(6, id);
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


}
