package service;

import dao.*;
import dto.*;
import exception.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Date;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Pattern;

public class UserService {
    private static final String SALT = "fghbjkl;op'iuytrfghbj,klolkyjtrhdsgfhcvghjoikp'.,njbmhvg";
    private UserDao userDao = new UserDaoMySql();
    private PasswordAuthentication passwordAuthentication = new PasswordAuthentication();

    public boolean enrollUser(String username, char[] password, String email) throws DataIsEmpty, CouldntAddData {
        if (username == null ) {
            throw new UsernameIsEmpty();
        }
        if (password == null ) {
            throw new PasswordIsEmpty();
        }

        String passwordHash = passwordAuthentication.hashPassword(password);
        User user = new User(username, passwordHash, email);

        if (!userDao.addUser(user)) {
            throw new CouldntAddUser();
        }

        return true;
    }

    public boolean isUserExist(String username) {
        return userDao.isUsernameExist(username);
    }

    public boolean authenticateUser(String username, char[] password) {
        User user = userDao.getUserByUsername(username);
        return user != null &&
                //passwordAuthentication.hashPassword(password).equals(user.getPassword());
        passwordAuthentication.authenticate(password, user.getPassword());
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public boolean editUser(String oldUsername, String username, char[] password, String email,
                            Date birthdate, String fullname) throws DataIsEmpty, CouldntAddData {
        User user = getUserByUsername(oldUsername);
        String oldPassword = user.getPassword();
        String oldEmail = user.getEmail();
        Date oldBirthdate = user.getBirthdate();
        String oldFullname = user.getFullname();

        User newUser = new User();

        if (!username.equals("") && !username.equals(oldUsername)) {
            newUser.setUsername(username);
        } else {
            newUser.setUsername(oldUsername);
        }

        if (!Arrays.equals(password, new char[]{}) && !Arrays.equals(password, "********".toCharArray()) && !passwordAuthentication.authenticate(password, oldPassword)) {
            String passwordHash = passwordAuthentication.hashPassword(password);
            newUser.setPassword(passwordHash);
        } else {
            newUser.setPassword(oldPassword);
        }

        if (!email.equals("") && !email.equals(oldEmail)) {
            newUser.setEmail(email);
        } else {
            newUser.setEmail(oldEmail);
        }

        if (birthdate != null && !birthdate.equals(oldBirthdate)) {
            newUser.setBirthdate(birthdate);
        } else {
            newUser.setBirthdate(oldBirthdate);
        }

        if (!fullname.equals("") && !fullname.equals(oldFullname)) {
            newUser.setFullname(fullname);
        } else {
            newUser.setFullname(oldFullname);
        }

        if (!userDao.editUser(user.getId(), newUser)) {
            throw new CouldntEditUser();
        }

        return true;
    }

    private static final class PasswordAuthentication {
        public static final String ID = "$31$";
        public static final int DEFAULT_COST = 16;
        private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
        private static final int SIZE = 128;
        private static final Pattern layout = Pattern.compile("\\$31\\$(\\d\\d?)\\$(.{43})");
        private final SecureRandom random;
        private final int cost;
        private static final byte[] salt = "fghjkhbjfdxgyjhugherflk;nkmmjhbnkljhnkml".getBytes();

        public PasswordAuthentication()
        {
            this(DEFAULT_COST);
        }

        public PasswordAuthentication(int cost)
        {
            iterations(cost); /* Validate cost */
            this.cost = cost;
            this.random = new SecureRandom();
        }

        private static int iterations(int cost)
        {
            if ((cost < 0) || (cost > 30))
                throw new IllegalArgumentException("cost: " + cost);
            return 1 << cost;
        }

        public String hashPassword(char[] password)
        {
            byte[] dk = pbkdf2(password, salt, 1 << cost);
            byte[] hash = new byte[salt.length + dk.length];
            System.arraycopy(salt, 0, hash, 0, salt.length);
            System.arraycopy(dk, 0, hash, salt.length, dk.length);
            Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
            return ID + cost + '$' + enc.encodeToString(hash);
        }

        public boolean authenticate(char[] password, String token)
        {
//            Matcher m = layout.matcher(token);
//            if (!m.matches())
//                throw new IllegalArgumentException("Invalid token format");
//            int iterations = iterations(Integer.parseInt(m.group(1)));
//            byte[] hash = Base64.getUrlDecoder().decode(m.group(2));
//            byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8);
//            byte[] check = pbkdf2(password, salt, iterations);
//            int zero = 0;
//            for (int idx = 0; idx < check.length; ++idx)
//                zero |= hash[salt.length + idx] ^ check[idx];
//            return zero == 0;
            return hashPassword(password).equals(token);
        }

        private static byte[] pbkdf2(char[] password, byte[] salt, int iterations)
        {
            KeySpec spec = new PBEKeySpec(password, salt, iterations, SIZE);
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
