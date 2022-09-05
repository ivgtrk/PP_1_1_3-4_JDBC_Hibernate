package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl uService = new UserServiceImpl();
        uService.createUsersTable();

        // Save Users in DB
        uService.saveUser("Ivan", "Ivanov", (byte) 20);
        uService.saveUser("Petr", "Petrov", (byte) 22);
        uService.saveUser("Victor", "Victorov", (byte) 24);
        uService.saveUser("Igor", "Lopatin", (byte) 26);

        // Read all User and print...
        List<User> uList = uService.getAllUsers();
        Print(uList);

        // Delete User with ID 3...
        uService.removeUserById(3);
        // Read all Users again...
        uList = uService.getAllUsers();
        Print(uList);

        // Clean table and drop it...
        uService.cleanUsersTable();
        uService.dropUsersTable();
    }

    private static void Print(List<User> users) {
        System.out.println("-----------------------------");
        for (User u: users) { System.out.println(u); }
        System.out.println("-----------------------------");
    }
}
