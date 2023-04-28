package com.example.mylibrary.manager.impl;

import com.example.mylibrary.db.DBConnectionProvider;
import com.example.mylibrary.manager.AuthorStorage;
import com.example.mylibrary.model.Author;
import com.example.mylibrary.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorStorageImpl implements AuthorStorage {
    private static Connection connection = DBConnectionProvider.getInstance().getConnection();

    @Override
    public void saveAuthor(Author author) {
        String sql = "INSERT INTO my_library.author(name,surname,email,age) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.setString(3, author.getEmail());
            preparedStatement.setInt(4, author.getAge());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                author.setId(generatedKeys.getInt(1));
            }
            System.out.println("Author inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Author> getAllByAuthor() {
        List<Author> authorList = new ArrayList<>();
        String sql = "SELECT * FROM my_library.author";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                authorList.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;
    }
    @Override
    public List<Author> getAuthors(String name) {
        List<Author> authorList = new ArrayList<>();
        String sql = "SELECT * FROM my_library.author";
        boolean hasTitle = name != null && name != "";

        if (hasTitle) {
            sql += " WHERE name LIKE CONCAT( '%',?,'%')";
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if (hasTitle) {
                preparedStatement.setString(1, name);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                authorList.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;
    }
    @Override
    public Author getById(int id) {
        String sql = "SELECT * FROM my_library.author WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void editAuthor(Author author) {
        String sql = "UPDATE author SET `name`=?, `surname`=?, `email`=?, `age`=? WHERE `id`=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getSurname());
            preparedStatement.setString(3, author.getEmail());
            preparedStatement.setInt(4,author.getAge());
            preparedStatement.setInt(5,author.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(int id) {
        String sql = "DELETE FROM my_library.author WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) throws SQLException {
            return Author.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .surname(resultSet.getString("surname"))
                    .email(resultSet.getString("email"))
                    .age(resultSet.getInt("age"))
                    .build();
    }

}
