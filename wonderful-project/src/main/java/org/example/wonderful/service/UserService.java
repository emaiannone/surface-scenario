package org.example.wonderful.service;

import org.example.wonderful.core.UserBean;
import org.example.wonderful.db.UserDAO;
import org.example.wonderful.db.UserDTO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Arrays;

public class UserService {
    public static final MessageDigest PASSWORD_HASHER;
    private static final UserDAO USER_DAO;
    static {
        try {
            PASSWORD_HASHER = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        USER_DAO = new UserDAO();
    }

    public static boolean register(UserBean user, String suppliedPwd) {
        byte[] salt = generateSalt();
        byte[] hashedPassword = hashPassword(suppliedPwd, salt);
        UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setHashedPassword(hashedPassword);
        userDto.setSalt(salt);
        try {
            return USER_DAO.storeUser(userDto);
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean login(String suppliedUser, String suppliedPwd) {
        try {
            UserDTO userDTO = USER_DAO.fetchUserByUsername(suppliedUser);
            byte[] hashedInputPass = hashPassword(suppliedPwd, userDTO.getSalt());
            return Arrays.equals(hashedInputPass, userDTO.getHashedPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] hashPassword(String pwd, byte[] salt) {
        PASSWORD_HASHER.update(salt);
        return PASSWORD_HASHER.digest(pwd.getBytes(StandardCharsets.UTF_8));
    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
