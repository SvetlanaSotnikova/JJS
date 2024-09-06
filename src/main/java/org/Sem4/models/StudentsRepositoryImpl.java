package org.Sem4.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class StudentsRepositoryImpl implements StudentsRepository {
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "147";

    @Override
    public void add(Student item) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String insertData = "INSERT INTO students (name, age) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertData)) {
                statement.setString(1, item.getName());
                statement.setInt(2, item.getAge());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student item) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String updateData = "UPDATE students SET name = ?, age = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(updateData)) {
                statement.setString(1, item.getName());
                statement.setInt(2, item.getAge());
                statement.setInt(3, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Student item) {

    }

    @Override
    public Student getById(Integer integer) {
        return null;
    }

    @Override
    public Collection<Student> getAll() {
        return List.of();
    }
}
