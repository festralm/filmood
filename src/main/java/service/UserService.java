package service;

import dao.implementation.UserDaoMySql;
import dao.interfaces.UserDao;
import dto.*;
import exception.*;
import lombok.SneakyThrows;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Date;
import java.util.Base64;
import java.util.regex.Pattern;

public class UserService {
    private UserDao userDao = new UserDaoMySql();


    @SneakyThrows
    public Film[] getWatchedFilmsByUserId(int userId) {
        if (userId < 1) {
            throw new IncorrectUserIdException();
        }
        Film[] films = userDao.getWatchedFilmsByUserId(userId);
        if (films == null) {
            throw new IncorrectFilmException();
        }
        return films;
    }

    @SneakyThrows
    public Film[] getFavoriteFilmsByUserId(int userId) {
        if (userId < 1) {
            throw new IncorrectUserIdException();
        }
        Film[] films = userDao.getFavoriteFilmsByUserId(userId);
        if (films == null) {
            throw new IncorrectFilmException();
        }
        return films;
    }

    @SneakyThrows
    public Film[] getWillWatchFilmsByUserId(int userId) {
        if (userId < 1) {
            throw new IncorrectUserIdException();
        }
        Film[] films = userDao.getWillWatchFilmsByUserId(userId);
        if (films == null) {
            throw new IncorrectFilmException();
        }
        return films;
    }

    @SneakyThrows
    public User getUserByUserId(int userId) {
        if (userId < 1) {
            throw new IncorrectUserIdException();
        }
        User user = userDao.getUserByUserId(userId);
        if (user == null) {
            throw new IncorrectUserIdException();
        }
        return user;
    }

    @SneakyThrows
    public int getRandomFriendIdByUserId(int userId) {
        if (userId < 1) {
            throw new IncorrectUserIdException();
        }
        int id = userDao.getRandomFriendIdByUserId(userId);
        return id;
    }

    @SneakyThrows
    public boolean isUserExist(String username) {
        if (username == null) {
            throw new IncorrectUsernameException();
        }
        return userDao.isUserExist(username);
    }

    @SneakyThrows
    public boolean authenticateUser(String username, char[] password) {
        if (username == null || password == null) {
            throw new IncorrectUserException();
        }

        String usersPassword = userDao.getPasswordByUsername(username);

        if (usersPassword == null) {
            throw new IncorrectUserException();
        }
        return PasswordAuthentication.authenticate(password, usersPassword);
    }

    @SneakyThrows
    public int getUserIdByUsername(String username) {
        if (username == null) {
            throw new IncorrectUserException();
        }

        int id = userDao.getUserIdByUsername(username);
        if (id == -1) {
            throw new IncorrectUserException();
        }

        return id;
    }

    @SneakyThrows
    public boolean editUser(int userId, String username, char[] password,
                            String email, String fullname, String birthdate) {
        if (username == null || password == null || email == null) {
            throw new IncorrectUserException();
        }

        boolean edited = userDao.editUser(
                new User(userId, username, PasswordAuthentication.hashPassword(password),
                        email, birthdate.equals("") ? null : Date.valueOf(birthdate), fullname)
        );

        if (!edited) {
            throw new CouldNotEditUserExceptionException();
        }
        return true;
    }

    @SneakyThrows
    public boolean addNewUser(String username, char[] password, String email) {
        if (username == null || password == null || email == null) {
            throw new IncorrectUserException();
        }

        boolean added = userDao.addNewUser(
                new User(username, PasswordAuthentication.hashPassword(password), email)
        );

        if (!added) {
            throw new CouldNotAddUserException();
        }
        return true;
    }

    private static final class PasswordAuthentication {
        public static final String ID = "$31$";
        public static final int cost = 16;
        private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
        private static final int SIZE = 128;
        private static final Pattern layout = Pattern.compile("\\$31\\$(\\d\\d?)\\$(.{43})");
        private static final byte[] salt = "fghjkhbjfdxgyjhugherflk;nkmmjhbnkljhnkml".getBytes();

        public static String hashPassword(char[] password)
        {
            byte[] dk = pbkdf2(password);
            byte[] hash = new byte[salt.length + dk.length];
            System.arraycopy(salt, 0, hash, 0, salt.length);
            System.arraycopy(dk, 0, hash, salt.length, dk.length);
            Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
            return ID + cost + '$' + enc.encodeToString(hash);
        }

        public static boolean authenticate(char[] password, String token)
        {
            return hashPassword(password).equals(token);
        }

        private static byte[] pbkdf2(char[] password)
        {
            KeySpec spec = new PBEKeySpec(password, PasswordAuthentication.salt, 65536, SIZE);
            try {
                SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
                return f.generateSecret(spec).getEncoded();
            }
            catch (NoSuchAlgorithmException ex) {
                throw new IllegalStateException("Missing algorithm: " + ALGORITHM, ex);
            }
            catch (InvalidKeySpecException ex) {
                throw new IllegalStateException("Invalid SecretKeyFactory", ex);
            }
        }
    }
}
