package dao;

import java.sql.*;

public class MySqlConnection {
    public Connection getNewConnection() throws SQLException {
        String url = "jdbc:mysql://localhost/filmood?" +
                "serverTimezone=Europe/Moscow&useUnicode=true";
        String user = "java_itis_fm";
        String password = "java_itis_fm";
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
        return DriverManager.getConnection(url, user, password);
    }
}
