package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    static final String URL = "jdbc:mysql://localhost:3306/dbusers";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Unable to find the driver!");
        }

        try {
            conn = DriverManager.getConnection(URL, "root", "root");
            System.out.println("Connection is established!");
        } catch (SQLException ex) {
            System.out.println("Connection opening error!");
        }
        return conn;
    }
}
