package dao;

import dto.*;
import service.*;

import java.sql.*;

public class FriendDaoMySql implements FriendDao {
    private final MySqlConnection connection = new MySqlConnection();

    @Override
    public User getFriendByUserId(int userId) {
        try (Connection con = connection.getNewConnection()) {
            String sql1 = "select id, user1_id, user2_id " +
                    "from filmood.friend " +
                    "where user1_id = ? and (status = 1 or status = 0) " +
                    "order by rand() " +
                    "limit 1";
            try (PreparedStatement preparedStatement1 = con.prepareStatement(sql1)){
                preparedStatement1.setInt(1, userId);

                ResultSet resultSet1 = preparedStatement1.executeQuery();

                if (resultSet1.next()) {
                    int anotherUserId = resultSet1.getInt(3);

                    UserService userService = new UserService();

                    return userService.getUserById(anotherUserId);
                }
            }
            String sql2 = "select id, user1_id, user2_id " +
                    "from filmood.friend " +
                    "where user2_id = ? and (status = -1 or status = 0) " +
                    "order by rand() " +
                    "limit 1";
            try (PreparedStatement preparedStatement2 = con.prepareStatement(sql2)){
                preparedStatement2.setInt(1, userId);

                ResultSet resultSet2 = preparedStatement2.executeQuery();

                if (resultSet2.next()) {
                    int anotherUserId = resultSet2.getInt(2);

                    UserService userService = new UserService();

                    return userService.getUserById(anotherUserId);
                }
            }
        }
        catch (SQLException exception) {
            System.out.println("Something went wrong...");
            exception.printStackTrace();
        }
        return null;
    }
}
