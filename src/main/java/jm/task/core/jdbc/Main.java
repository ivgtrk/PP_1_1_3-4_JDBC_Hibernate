package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();

        // Save Users in DB
        userDaoJDBC.saveUser("Ivan", "Ivanov", (byte) 20);
        userDaoJDBC.saveUser("Petr", "Petrov", (byte) 22);
        userDaoJDBC.saveUser("Victor", "Victorov", (byte) 24);
        userDaoJDBC.saveUser("Igor", "Lopatin", (byte) 26);

        // Read all User and print...
        List<User> uList = userDaoJDBC.getAllUsers();
        Print(uList);

        // Delete User with ID 3...
        userDaoJDBC.removeUserById(3);
        // Read all Users again...
        uList = userDaoJDBC.getAllUsers();
        Print(uList);

        // Clean table and drop it...
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }

    private static void Print(List<User> users) {
        System.out.println("-----------------------------");
        for (User u: users) { System.out.println(u); }
        System.out.println("-----------------------------");
    }
}
