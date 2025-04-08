package utils;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
public class PasswordUtils {

    private static final int SALT_LENGTH = 16; // Length of the salt in bytes
    private static final String HASH_ALGORITHM = "SHA-256"; // Hashing algorithm

    // Generates a random salt
    public static byte[] generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);
        return salt;
    }

    // Hashes the password with the given salt
    public static String hashPassword(String password) throws Exception {
        byte[] salt = generateSalt();
        MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        messageDigest.update(salt);
        byte[] hashedPassword = messageDigest.digest(password.getBytes());
        //append the salt to the hashed password for storage
        byte[] hashedPasswordWithSalt = new byte[hashedPassword.length + salt.length];
        System.arraycopy(hashedPassword, 0, hashedPasswordWithSalt, 0, hashedPassword.length);
        System.arraycopy(salt, 0, hashedPasswordWithSalt, hashedPassword.length, salt.length);
        return Base64.getEncoder().encodeToString(hashedPasswordWithSalt);
    }

    // Verifies if the provided password matches the stored hashed password
    public static boolean verifyPassword(String password, String storedHashedPassword) throws Exception {
        byte[] hashedPasswordWithSalt = Base64.getDecoder().decode(storedHashedPassword);
        byte[] salt = new byte[SALT_LENGTH];
        System.arraycopy(hashedPasswordWithSalt, hashedPasswordWithSalt.length - SALT_LENGTH, salt, 0, SALT_LENGTH);
        MessageDigest messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        messageDigest.update(salt);
        byte[] hashedPassword = messageDigest.digest(password.getBytes());
        //compare the hashed password with the stored hashed password
        for (int i = 0; i < hashedPassword.length; i++) {
            if (hashedPassword[i] != hashedPasswordWithSalt[i]) {
                return false;
            }
        }
        return true;
    }
}
