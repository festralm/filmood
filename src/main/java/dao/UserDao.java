package dao;

import dto.User;

public interface UserDao {
    User getUserById(int id);

    //  Role getRoleByLoginPassword(final String login, final String passwordHash);
    User getUserByUsernamePassword(final String login, final String passwordHash);

    User getUserByUsername(final String login);

    boolean addUser(User user);

    boolean deleteUser(User user);

    boolean isUserExist(final String login, final String passwordHash);

    boolean isUsernameExist(final String login);
}