package com.example.mylibrary.manager.impl;

import com.example.mylibrary.db.DBConnectionProvider;
import com.example.mylibrary.manager.AuthorStorage;
import com.example.mylibrary.manager.BookStorage;
import com.example.mylibrary.manager.UserManager;
import com.example.mylibrary.model.Author;
import com.example.mylibrary.model.Book;
import com.example.mylibrary.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookStorageImpl implements BookStorage {
    private static Connection connection = DBConnectionProvider.getInstance().getConnection();

    private AuthorStorage authorStorage = new AuthorStorageImpl();

    private UserManager userManager = new UserManagerImpl();

    @Override
    public void saveBook(Book book) {
        String sql = "INSERT INTO book(title,description,price,author_id,user_id, pic_name) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setInt(4, book.getAuthor().getId());
            preparedStatement.setInt(5, book.getUser().getId());
            preparedStatement.setString(6, book.getImage());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }
            System.out.println("Book inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAllByBook() {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookList.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public List<Book> getAllByUserBook(int user_id) {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE user_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookList.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public Book getById(int id) {
        String sql = "SELECT * FROM my_library.book WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Book> search(String keyword) {
        List<Book> bookList = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE title LIKE  ? OR  description LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            keyword = "%" + keyword + "%";
            preparedStatement.setString(1, keyword);
            preparedStatement.setString(2, keyword);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookList.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public void editBook(Book book) {
        String sql = "UPDATE my_library.book SET title=?,description=?,price=?,author_id=?, user_id=?, pic_name=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setDouble(3, book.getPrice());
            preparedStatement.setInt(4, book.getAuthor().getId());
            preparedStatement.setInt(5, book.getUser().getId());
            preparedStatement.setString(6, book.getImage());
            preparedStatement.setInt(7, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(int id) {
        String sql = "DELETE FROM book WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        int authorId = resultSet.getInt("author_id");
        int userId = resultSet.getInt("user_id");
        User user = userManager.getById(userId);
        Author author = authorStorage.getById(authorId);
        return Book.builder()
                .id(resultSet.getInt("id"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .price(resultSet.getDouble("price"))
                .author(author)
                .user(user)
                .image(resultSet.getString("pic_name"))
                .build();
    }

}
