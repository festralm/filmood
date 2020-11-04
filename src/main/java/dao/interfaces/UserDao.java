package dao.interfaces;

import dto.*;

public interface UserDao {
//    User getUserById(int id);

//    User getUserByUsernamePassword(final String username, final String passwordHash);

//
//    User getUserByUsername(final String username);
//
//    boolean addUser(User user);
//
//    boolean deleteUser(User user);
//
//    boolean isUserExist(final String username, final String passwordHash);
//
//    boolean isUsernameExist(final String username);
//
//    boolean editUser(int id, User newUser);

    Film[] getWatchedFilmsByUserId(int userId);

    Film[] getFavoriteFilmsByUserId(int userId);

    Film[] getWillWatchFilmsByUserId(int userId);

    User getUserByUserId(int userId);

    int getRandomFriendByUserId(int userId);

    boolean isUserExist(String username);
}