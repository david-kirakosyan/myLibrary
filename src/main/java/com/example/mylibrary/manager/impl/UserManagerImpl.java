package com.example.mylibrary.manager.impl;

import com.example.mylibrary.db.DBConnectionProvider;
import com.example.mylibrary.manager.UserManager;
import com.example.mylibrary.model.User;

import java.sql.*;


public class UserManagerImpl implements UserManager {

    private static Connection connection = DBConnectionProvider.getInstance().getConnection();

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO my_library.user(name,surname,email,password) VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
            System.out.println("User registered");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM user WHERE email=? AND password=? ";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public User getByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .build();
    }


}
