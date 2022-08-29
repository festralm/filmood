package dao.implementation;

import dao.*;
import dao.interfaces.*;
import dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserDaoMySql implements UserDao {
    private final MySqlConnection connection = new MySqlConnection();

    @Override
    public Film[] getWatchedFilmsByUserId(int userId) {
        try  {
            String sql = "select film.id, film.name, film.photo_path," +
                    "film.start_year, film.description, film.finish_year " +
                    "from filmood.user " +
                    "inner join filmood.film_user_watched " +
                    "on user.id = film_user_watched.user_id " +
                    "inner join filmood.film " +
                    "on film_user_watched.film_id = film.id " +
                    "where user.id = ? " +
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
        try {
            String sql = "select film.id, film.name, film.photo_path, " +
                    "film.start_year, film.description, film.finish_year " +
                    "from filmood.user " +
                    "inner join filmood.film_user_favorite " +
                    "on user.id = film_user_favorite.user_id " +
                    "inner join filmood.film " +
                    "on film_user_favorite.film_id = film.id " +
                    "where user.id = ? " +
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
            String sql = "select film.id, film.name, film.photo_path, " +
                    "film.start_year, film.description, film.finish_year " +
                    "from filmood.user " +
                    "inner join filmood.film_user_will_watch " +
                    "on user.id = film_user_will_watch.user_id " +
                    "inner join filmood.film " +
                    "on film_user_will_watch.film_id = film.id " +
                    "where user.id = ? " +
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
        Connection con = connection.getNewConnection() ;
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Film> films = new ArrayList<>();
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
            String sql = "select username, password, email, birthdate, fullname, " +
                    "photo_path " +
                    "from filmood.user " +
                    "where user.id = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setInt(1, userId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String username = resultSet.getString(1);
                    String passwordHash = resultSet.getString(2);
                    String email = resultSet.getString(3);
                    Date birthdate = resultSet.getDate(4);
                    String fullname = resultSet.getString(5);
                    String photoPath = resultSet.getString(6);

                    return new User(userId, username, passwordHash, email,
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
    public int getRandomFriendIdByUserId(int userId) {
        try (Connection con = connection.getNewConnection()) {
            double rand = new Random().nextDouble();
            int res;
            if (rand < 0.5) {
                res = getRandomFriendTo(userId, con);
                if (res == -1) {
                    return getRandomFriendFrom(userId, con);
                }
            } else {
                res = getRandomFriendFrom(userId, con);
                if (res == -1) {
                    return getRandomFriendTo(userId, con);
                }
            }
            return res;
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return -1;
    }

    private int getRandomFriendTo(int userId, Connection con) throws SQLException {
        String sql = "select friend.user2_id " +
                "from filmood.user " +
                "inner join filmood.friend " +
                "on user.id = friend.user1_id " +
                "where user.id = ? and (status = 1 or status = 0) " +
                "order by rand() " +
                "limit 1";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return -1;
    }
    private int getRandomFriendFrom(int userId, Connection con) throws SQLException {
        String sql = "select friend.user1_id " +
                "from filmood.user " +
                "inner join filmood.friend " +
                "on user.id = friend.user2_id " +
                "where user.id = ? and (status = -1 or status = 0) " +
                "order by rand() " +
                "limit 1";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
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

    @Override
    public String getPasswordByUsername(String username) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select password " +
                    "from filmood.user " +
                    "where (username = ?)";

            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, username);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getString(1);
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
    public int getUserIdByUsername(String username) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id " +
                    "from filmood.user " +
                    "where (username = ?)";

            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, username);

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
    public boolean editUser(User user) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "update filmood.user " +
                    "set username = ?, password = ?, email = ?, birthdate = ?," +
                    "fullname = ? " +
                    "where id = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setDate(4, user.getBirthdate());
                preparedStatement.setString(5, user.getFullname());
                preparedStatement.setInt(6, user.getId());

                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addNewUser(User user) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "insert into filmood.user " +
                    "(username, password, email) " +
                    "values (?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());

                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return false;
    }
}
