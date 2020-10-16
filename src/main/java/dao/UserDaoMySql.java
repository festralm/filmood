package dao;

import dto.User;

import java.sql.*;
import java.util.logging.*;

public class UserDaoMySql implements UserDao {
    private final MySqlConnection connection = new MySqlConnection();
    private final static Logger LOGGER = Logger.getLogger(UserDaoMySql.class.getName());

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserByUsernamePassword(String username, String passwordHash) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, username, password, email, birthdate, fullname " +
                    "from filmood.user " +
                    "where (username = ? and password = ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, passwordHash);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String email = resultSet.getString(4);
                    Date birthdate = resultSet.getDate(5);
                    String fullname = resultSet.getString(6);

                    return new User(id, username, passwordHash, email, birthdate, fullname);
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }

    public User getUserByUsername(String username) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, username, password, email, birthdate, fullname " +
                    "from filmood.user " +
                    "where (username = ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setString(1, username);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String passwordHash = resultSet.getString(3);
                    String email = resultSet.getString(4);
                    Date birthdate = resultSet.getDate(5);
                    String fullname = resultSet.getString(6);

                    return new User(id, username, passwordHash, email, birthdate, fullname);
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
    public boolean addUser(User user) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "insert into filmood.user " +
                    "(username, password, email, birthdate, fullname) " +
                    "values (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setDate(4, user.getBirthdate());
                preparedStatement.setString(5, user.getFullname());

                preparedStatement.executeUpdate();
                return true;
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public boolean isUserExist(String login, String passwordHash) {
        return getUserByUsernamePassword(login, passwordHash) != null;
    }

    @Override
    public boolean isUsernameExist(String username) {
        try (Connection con = connection.getNewConnection()) {
            String sql = "select id, username, password, email, birthdate, fullname " +
                    "from filmood.user " +
                    "where (username = ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(sql)){
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
}
