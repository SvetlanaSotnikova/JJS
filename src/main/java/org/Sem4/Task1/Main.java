package org.Sem4.Task1;

import org.Sem4.models.Student;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Main {
    private static final Random rand = new Random();

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "147";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            createDatabase(connection);
            System.out.println("Database created");

            useDatabase(connection);
            System.out.println("Database connected");

            createTable(connection);
            System.out.println("Table created");

            int count = rand.nextInt(5, 11);
            for (int i = 0; i < count; i++) {
                insertData(connection, Student.create());
            }
            System.out.println("Inserted students into database");

            Collection<Student> students = readData(connection);
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println("Read data from database");

            for (var student : students) {
                student.updateName();
                student.updateAge();
                updateData(connection, student);
            }
            System.out.println("Update students into database");

            students = readData(connection);
            for (Student student : students) {
                System.out.println(student);
            }
            System.out.println("Read data from database");

//            for (var student : students) {
//                deleteData(connection, student.getId());
//            }
//            System.out.println("Delete students into database");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createDatabase(Connection connection) throws SQLException {
        String createDatabase = "CREATE DATABASE IF NOT EXISTS studentsDB";
        try (PreparedStatement statement = connection.prepareStatement(createDatabase)) {
            statement.execute();
        }
    }

    private static void useDatabase(Connection connection) throws SQLException {
        String useDatabase = "USE studentsDB";
        try (PreparedStatement statement = connection.prepareStatement(useDatabase)) {
            statement.execute();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS students (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), age INT)";
        try (PreparedStatement statement = connection.prepareStatement(createTable)) {
            statement.execute();
        }
    }

    private static void insertData(Connection connection, Student student) throws SQLException {
        String insertData = "INSERT INTO students (name, age) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertData)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.executeUpdate();
        }
    }

    private static Collection<Student> readData(Connection connection) throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        String readData = "SELECT * FROM students";
        try (PreparedStatement statement = connection.prepareStatement(readData)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                students.add(new Student(id, name, age));
            }
        }
        return students;
    }

    private static void updateData(Connection connection, Student student) throws SQLException {
        String updateData = "UPDATE students SET name = ?, age = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateData)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setInt(3, student.getId());
            statement.executeUpdate();
        }
    }

    private static void deleteData(Connection connection, int id) throws SQLException {
        String deleteData = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteData)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
