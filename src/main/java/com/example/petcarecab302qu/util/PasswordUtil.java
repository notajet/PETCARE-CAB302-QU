package com.example.petcarecab302qu.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for hashing passwords using the SHA-256 algorithm.
 * Provides a method to securely hash passwords before storing them in the database.
 */
public class PasswordUtil {

    /**
     * Hashes a given password using the SHA-256 algorithm.
     * Converts the resulting byte array into a hexadecimal string for storage.
     *
     * @param password The password to be hashed.
     * @return The hashed password as a hexadecimal string.
     * @throws RuntimeException if the SHA-256 algorithm is not available.
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}