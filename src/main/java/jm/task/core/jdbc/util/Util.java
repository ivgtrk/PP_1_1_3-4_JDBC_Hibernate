package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    Connection connection;
    Driver driver;

    public void OpenConnection(String url, String username, String password) {
        try {
            driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException ex) {
            System.out.println("Error create or registered driver!");
            return;
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
            if(!connection.isClosed()) {
                System.out.println("Соединение с БД установлено!");
            }
        } catch (SQLException ex) {
            System.out.println("Error create connection!");
        }
    }

    public void CloseConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
                System.out.println("Соединение с БД закрыто!");
            }
        } catch (SQLException ex) {
            System.out.println("Cannot close connection!");
        }
    }
}
