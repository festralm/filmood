package dao.interfaces;

import dto.*;

public interface UserDao {
    Film[] getWatchedFilmsByUserId(int userId);

    Film[] getFavoriteFilmsByUserId(int userId);

    Film[] getWillWatchFilmsByUserId(int userId);

    User getUserByUserId(int userId);

    int getRandomFriendIdByUserId(int userId);

    boolean isUserExist(String username);

    String getPasswordByUsername(String username);

    int getUserIdByUsername(String username);

    boolean editUser(User user);

    boolean addNewUser(User user);
}