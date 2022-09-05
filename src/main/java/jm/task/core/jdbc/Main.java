package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();

        // Save Users in DB
        userDaoHibernate.saveUser("Ivan", "Ivanov", (byte) 20);
        userDaoHibernate.saveUser("Petr", "Petrov", (byte) 22);
        userDaoHibernate.saveUser("Victor", "Victorov", (byte) 24);
        userDaoHibernate.saveUser("Igor", "Lopatin", (byte) 26);

        // Read all User and print...
        List<User> uList = userDaoHibernate.getAllUsers();
        Print(uList);

        // Delete User with ID 3...
        userDaoHibernate.removeUserById(3);
        // Read all Users again...
        uList = userDaoHibernate.getAllUsers();
        Print(uList);

        // Clean table and drop it...
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }

    private static void Print(List<User> users) {
        System.out.println("-----------------------------");
        for (User u: users) { System.out.println(u); }
        System.out.println("-----------------------------");
    }
}
