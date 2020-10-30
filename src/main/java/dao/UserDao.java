package dao;

import dto.User;

public interface UserDao {
    User getUserById(int id);

    //  Role getRoleByLoginPassword(final String username, final String passwordHash);
    User getUserByUsernamePassword(final String username, final String passwordHash);

    User getUserByUsername(final String username);

    boolean addUser(User user);

    boolean deleteUser(User user);

    boolean isUserExist(final String username, final String passwordHash);

    boolean isUsernameExist(final String username);

    boolean editUser(int id, User newUser);
}