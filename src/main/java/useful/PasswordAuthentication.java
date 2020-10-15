package useful;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Optional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordAuthentication {
    private static final SecureRandom RAND = new SecureRandom();
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    public static String hashPassword(char[] password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password, salt, 65536, 128);
        SecretKeyFactory factory;
        byte[] hash = new byte[0];
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return new String(hash, StandardCharsets.UTF_16);
    }

    public static boolean verifyPassword(char[] password, String key, byte[] salt) {
        String encrypted = hashPassword(password, salt);
        return encrypted.equals(key);
    }
}