package org.example.wonderful.db;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserDAO {
    static MysqlDataSource dataSource;

    static {
        dataSource = new MysqlDataSource();
        dataSource.setUser("admin");
        dataSource.setPassword("admin");
        dataSource.setServerName("wonderfulDB");
    }

    public boolean storeUser(UserDTO userDto) throws SQLException {
        String query = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?);";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userDto.getUsername());
            preparedStatement.setString(2, userDto.getEmail());
            preparedStatement.setString(3, userDto.getFirstName());
            preparedStatement.setString(4, userDto.getLastName());
            preparedStatement.setString(5, Arrays.toString(userDto.getHashedPassword()));
            preparedStatement.setString(6, Arrays.toString(userDto.getSalt()));
            int i = preparedStatement.executeUpdate();
            return i > 0;
        }
    }

    public UserDTO fetchUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM Users WHERE username=?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setEmail(rs.getString("email"));
                userDTO.setFirstName(rs.getString("firstName"));
                userDTO.setLastName(rs.getString("lastName"));
                userDTO.setUsername(username);
                userDTO.setHashedPassword(rs.getString("pass").getBytes());
                userDTO.setSalt(rs.getString("salt").getBytes());
                return userDTO;
            }
        }
        return null;
    }
}
