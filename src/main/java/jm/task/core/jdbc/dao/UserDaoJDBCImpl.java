package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(50), lastName VARCHAR(50), age INT)");
            conn.setAutoCommit(false);

            // Save changes...
            conn.commit();
            System.out.println("Table was created successfully!");

        } catch (SQLException ex) {
            System.out.println("Cannot create table!\n");
            ex.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            conn.setAutoCommit(false);
            conn.commit();  // Save changes...
            System.out.println("Table deleted successfully!");

        } catch (SQLException ex) {
            System.out.println("Cannot delete table!");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO users (name, lastName, age) VALUES (?,?,?);")) {

            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
            pstmt.executeUpdate();
            conn.setAutoCommit(false);
            // Save changes...
            conn.commit();
            System.out.println("User " + name + " " + lastName + " added successfully!");

        } catch (SQLException ex) {
            System.out.println("User " + name + " " + lastName + " cannot save in table!");
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE Id = ?")) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            conn.setAutoCommit(false);
            conn.commit();
            System.out.println("User by ID = " + id + " successfully deleted!");

        } catch (SQLException ex) {
            System.out.println("Cannot delete User!");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Connection conn = Util.getConnection();

        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM users");

            // Save changes...
            conn.commit();
            conn.setAutoCommit(true);

            while (result.next()) {
                User user = new User(
                        result.getString("name"),
                        result.getString("lastName"),
                        result.getByte("age")
                );
                user.setId(result.getLong("id"));
                userList.add(user);
            }
            System.out.println("The data has been read successfully!");

        } catch (SQLException ex) {
            System.out.println("Database reading error!");
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("DELETE FROM users");
            conn.setAutoCommit(false);
            conn.commit();
            System.out.println("Table clean successfully!");

        } catch (SQLException ex) {
            System.out.println("Cannot clean table!");
        }
    }
}
