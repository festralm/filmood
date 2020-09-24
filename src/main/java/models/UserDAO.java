package models;

import interfaces.DataAccessable;
import interfaces.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DataAccessable {
    private final List<UserInterface> store = new ArrayList<>();

    public UserInterface getById(int id) {
        for (UserInterface user : store) {
            if (user.getId() == id) {
                return user;
            }
        }

        return User.getNullUser();
    }

    public UserInterface getUserByLoginPassword(final String login, final int passwordHash) {
        for (UserInterface user : store) {
            if (user.getLogin().equals(login) && user.getPasswordHash() == passwordHash) {
                return user;
            }
        }

        return User.getNullUser();
    }
//
//    public Role getRoleByLoginPassword(final String login, final String password) {
//        for (UserInterface user : store) {
//            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
//                return user.getRole();
//            }
//        }
//
//        return Role.UNKNOWN;
//    }

    public boolean add(UserInterface user) {
        if (!isUserExist(user.getLogin(), user.getPasswordHash())) {
            store.add(user);
            return true;
        }
        return false;
    }

    public boolean isUserExist(final String login, final int passwordHash) {
        return getUserByLoginPassword(login, passwordHash).getId() != -1;
    }

    public boolean delete(UserInterface user) {
        return store.remove(user);
    }

    @Override
    public boolean isLoginExist(String login) {
        for (UserInterface user : store) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public int getStoreSize() {
        return store.size();
    }
}
